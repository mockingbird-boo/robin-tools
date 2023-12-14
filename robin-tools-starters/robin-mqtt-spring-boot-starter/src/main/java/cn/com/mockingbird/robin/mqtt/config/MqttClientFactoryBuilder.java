package cn.com.mockingbird.robin.mqtt.config;

import cn.com.mockingbird.robin.mqtt.config.MqttProperties.Config;
import cn.com.mockingbird.robin.mqtt.config.MqttProperties.Will;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

import java.nio.charset.StandardCharsets;

/**
 * 客户端工厂构建器
 *
 * @author zhaopeng
 * @date 2023/12/12 0:22
 **/
@Getter
@Setter
public class MqttClientFactoryBuilder {

    private MqttConnectOptionsCallback mqttConnectOptionsCallback;

    public MqttClientFactoryBuilder() {

    }

    /**
     * 构建消费者客户端工厂
     * @param channelName 消息通道名
     * @param config 配置详情
     * @return 消费者客户端工厂
     */
    public MqttPahoClientFactory buildConsumerClientFactory(String channelName, Config config) {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = getMqttConnectOptions(config);
        if (config.getConsumerWill() != null) {
            Will will = config.getConsumerWill();
            options.setWill(will.getTopic(), will.getPayload().getBytes(StandardCharsets.UTF_8),
                    will.getQos(), will.getRetained());
        }
        callback(options, channelName);
        factory.setConnectionOptions(options);
        return factory;
    }

    /**
     * 构建生产者客户端工厂
     * @param channelName 消息通道名
     * @param config 配置详情
     * @return 消费者客户端工厂
     */
    public MqttPahoClientFactory buildProducerClientFactory(String channelName, Config config) {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = getMqttConnectOptions(config);
        if (config.getProducerWill() != null) {
            Will will = config.getProducerWill();
            options.setWill(will.getTopic(), will.getPayload().getBytes(StandardCharsets.UTF_8),
                    will.getQos(), will.getRetained());
        }
        callback(options, channelName);
        factory.setConnectionOptions(options);
        return factory;
    }

    private void callback(MqttConnectOptions mqttConnectOptions, String channelName) {
        if (mqttConnectOptionsCallback != null) {
            mqttConnectOptionsCallback.callback(mqttConnectOptions, channelName);
        }
    }

    private MqttConnectOptions getMqttConnectOptions(Config config) {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(config.getServerUris());
        options.setCleanSession(config.getCleanSession());
        options.setKeepAliveInterval(config.getKeepAliveInterval());
        options.setUserName(config.getUsername());
        options.setPassword(config.getPassword().toCharArray());
        options.setConnectionTimeout(config.getConnectTimeout());
        options.setAutomaticReconnect(config.getAutoReconnect());
        options.setMqttVersion(config.getMqttVersion());
        options.setMaxInflight(config.getMaxInflight());
        return options;
    }
}
