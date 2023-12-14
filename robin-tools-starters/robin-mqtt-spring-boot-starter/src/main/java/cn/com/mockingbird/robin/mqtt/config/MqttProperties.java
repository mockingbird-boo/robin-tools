package cn.com.mockingbird.robin.mqtt.config;

import cn.com.mockingbird.robin.mqtt.enums.ClientRole;
import lombok.Data;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * mqtt 配置属性
 *
 * @author zhaopeng
 * @date 2023/12/11 21:10
 **/
@Data
@ConfigurationProperties("spring.mqtt")
public class MqttProperties {

    /**
     * clientId 的后缀
     */
    private static String clientIdSuffix;

    /**
     * 详细配置
     */
    private final Map<String, Config> config;

    static {
        try {
            // 初始化本地 IP 作为客户端 ID 的后缀
            InetAddress localHost = InetAddress.getLocalHost();
            clientIdSuffix = "_ip_" + localHost.getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    public static class Config {

        /**
         * 客户端角色，默认既是消费者也是生产者
         */
        private ClientRole clientRole = ClientRole.CONSUMER_AND_PRODUCER;

        /**
         * 服务器地址
         */
        private String[] serverUris;

        /**
         * 连接超时时间，单位为秒
         */
        private Integer connectTimeout;

        /**
         * 设置会话心跳时间，单位为秒，默认 30 秒
         */
        private int keepAliveInterval = 30;

        /**
         * MQTT 服务质量，与 topic 一一对应
         */
        private Integer[] qos;

        /**
         * 主题，与 qos 一一对应
         */
        private String[] topics;

        /**
         * 用户名
         */
        private String username;

        /**
         * 密码
         */
        private String password;

        /**
         * 是否启用客户端 ID 后缀，默认启用
         */
        private Boolean enableClientIdSuffix = true;

        /**
         * 消费者客户端 ID
         */
        private String consumerClientId;

        /**
         * 生产者客户端 ID
         */
        private String producerClientId;

        /**
         * 是否异步发布消息，默认否
         */
        private Boolean async = false;

        /**
         * 是否支持断线后自动重连，默认支持
         */
        private Boolean autoReconnect = true;

        /**
         * 是否清空 Session，false 表示服务器会保留客户端的连接记录，
         * true 表示每次连接到服务器都以新身份进行连接，默认为 true
         */
        private Boolean cleanSession = true;

        /**
         * “最大飞行时间”，在高流量环境下可以增大该值，默认值为 10
         */
        private Integer maxInflight = 10;

        /**
         * mqtt 协议版本
         */
        private Integer mqttVersion = MqttConnectOptions.MQTT_VERSION_3_1_1;

        /**
         * consumer 遗嘱配置
         */
        private Will consumerWill;
        /**
         * producer 遗嘱配置
         */
        private Will producerWill;

        /**
         * 重写获取消费者客户端 ID
         * @return 消费者客户端 ID
         */
        public String getConsumerClientId() {
            return !enableClientIdSuffix ? consumerClientId : consumerClientId + clientIdSuffix;
        }

        /**
         * 重写获生产者客户端 ID
         * @return 生产者客户端 ID
         */
        public String getProducerClientId() {
            return !enableClientIdSuffix ? producerClientId : producerClientId + clientIdSuffix;
        }

    }

    @Data
    public static class Will {
        /**
         * 遗嘱消息 qos
         */
        private int qos = 1;

        /**
         * 遗嘱主题
         */
        private String topic;

        /**
         * 遗嘱内容
         */
        private String payload;

        /**
         * 是否发送保留消息，默认不发送
         */
        private Boolean retained = false;
    }
}
