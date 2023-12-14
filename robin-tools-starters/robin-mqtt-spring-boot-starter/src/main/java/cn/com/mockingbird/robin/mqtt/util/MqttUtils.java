package cn.com.mockingbird.robin.mqtt.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * MQTT 工具类
 *
 * @author zhaopeng
 * @date 2023/12/12 14:55
 **/
@SuppressWarnings("unused")
@Slf4j
@UtilityClass
public class MqttUtils {
    public final int QOS_0 = 0;
    public final int QOS_1 = 1;
    public final int QOS_2 = 2;

    private final Map<String, MqttPahoMessageHandler> handlerCache = new HashMap<>(16);
    public final String HANDLER_KEY_SUFFIX = "_handler";

    /**
     * 缓存处理器
     * @param channelName 通道名
     * @param handler 处理器实例
     */
    public void put(String channelName, MqttPahoMessageHandler handler) {
        handlerCache.put(channelName + HANDLER_KEY_SUFFIX, handler);
    }

    /**
     * 发布消息
     * @param topic 消息主题
     * @param message 消息内容
     * @param qos Qos 级别
     * @param channelName 消息通道名称
     */
    public void publishMessage(String topic, String message, int qos, String channelName) {
        MqttPahoMessageHandler handler = getHandler(channelName);
        Message<String> mqttMessage = MessageBuilder
                .withPayload(message)
                .setHeader(MqttHeaders.TOPIC, topic)
                .setHeader(MqttHeaders.QOS, qos)
                .build();
        handler.handleMessage(mqttMessage);
    }

    /**
     * 发布消息（QoS1）
     * @param topic 消息主题
     * @param message 消息内容
     * @param channelName 消息通道名称
     */
    public void publishMessage(String topic, String message, String channelName) {
        publishMessage(topic, message, QOS_1, channelName);
    }

    /**
     * 发布消息，
     * 如果只有一个通道将使用该通道进行消息发布
     * @param topic 消息主题
     * @param message 消息内容
     * @param qos Qos 级别
     */
    public void publishMessage(String topic, String message, int qos) {
        MqttPahoMessageHandler handler = getDefaultHandler();
        Message<String> mqttMessage = MessageBuilder
                .withPayload(message)
                .setHeader(MqttHeaders.TOPIC, topic)
                .setHeader(MqttHeaders.QOS, qos)
                .build();
        handler.handleMessage(mqttMessage);
    }

    /**
     * 发布消息（QoS1），
     * 如果只有一个通道将使用该通道进行消息发布
     * @param topic 消息主题
     * @param message 消息内容
     */
    public void publishMessage(String topic, String message) {
        publishMessage(topic, message, QOS_1);
    }

    /**
     * 发布消息
     * @param message 消息
     */
    public void publishMessage(Message<String> message) {
        MqttPahoMessageHandler handler = getDefaultHandler();
        handler.handleMessage(message);
    }

    /**
     * 根据通道名称获取处理器
     * @param channelName 通道名
     * @return MqttPahoMessageHandler 实例
     */
    public MqttPahoMessageHandler getHandler(String channelName) {
        if (!handlerCache.containsKey(channelName)) {
            log.warn("消息通道 - [{}] 不存在", channelName);
            throw new IllegalArgumentException("不存在指定的消息通道");
        }
        MqttPahoMessageHandler handler = handlerCache.get(channelName + HANDLER_KEY_SUFFIX);
        if (handler == null) {
            log.warn("消息通道 - [{}] 对应的消息处理器不存在", channelName);
            throw new IllegalArgumentException("消息通道对应的消息处理器不存在");
        }
        return handler;
    }

    /**
     * 获取默认的消息处理器
     * @return 默认的消息处理器
     */
    public MqttPahoMessageHandler getDefaultHandler() {
        if (handlerCache.isEmpty()) {
            log.warn("不存在可用的消息通道");
            throw new UnsupportedOperationException("不存在可用的消息通道");
        }
        Set<Map.Entry<String, MqttPahoMessageHandler>> entries = handlerCache.entrySet();
        Iterator<Map.Entry<String, MqttPahoMessageHandler>> iterator = entries.iterator();
        if (iterator.hasNext()) {
            return iterator.next().getValue();
        } else {
            log.warn("不存在默认的消息处理器");
            throw new UnsupportedOperationException("不存在默认的消息处理器");
        }
    }
}
