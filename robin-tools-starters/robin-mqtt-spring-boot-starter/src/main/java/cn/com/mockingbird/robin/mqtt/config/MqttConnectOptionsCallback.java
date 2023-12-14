package cn.com.mockingbird.robin.mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

/**
 * MqttConnectOptions 配置完后的回调接口
 *
 * @author zhaopeng
 * @date 2023/12/12 0:23
 **/
public interface MqttConnectOptionsCallback {

    /**
     * 配置 MqttConnectOptions 后的回调，用于自定义进行配置，例如可以自定义实现ssl配置
     * @param mqttConnectOptions MQTT 连接选项
     * @param channelName 通道名
     */
    void callback(MqttConnectOptions mqttConnectOptions, String channelName);

}
