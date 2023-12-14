package cn.com.mockingbird.robin.mqtt.config;

import cn.com.mockingbird.robin.mqtt.config.MqttProperties.Config;
import cn.com.mockingbird.robin.mqtt.enums.ClientRole;
import cn.com.mockingbird.robin.mqtt.util.MqttUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.messaging.MessageChannel;

/**
 * Mqtt 自动配置类
 *
 * @author zhaopeng
 * @date 2023/12/11 19:02
 **/
@Slf4j
@Configuration
@EnableConfigurationProperties(MqttProperties.class)
public class MqttAutoConfiguration implements ApplicationContextAware, BeanPostProcessor {

    private ConfigurableApplicationContext applicationContext;

    private final MqttProperties properties;

    private final MqttConnectOptionsCallback mqttConnectOptionsCallback;

    public MqttAutoConfiguration(MqttProperties properties, @Autowired(required = false) MqttConnectOptionsCallback mqttConnectOptionsCallback) {
        this.properties = properties;
        this.mqttConnectOptionsCallback = mqttConnectOptionsCallback;
    }

    @Bean
    @ConditionalOnMissingBean(MqttClientFactoryBuilder.class)
    public MqttClientFactoryBuilder mqttClientSettings() {
        MqttClientFactoryBuilder mqttClientFactoryBuilder = new MqttClientFactoryBuilder();
        if (mqttConnectOptionsCallback != null) {
            mqttClientFactoryBuilder.setMqttConnectOptionsCallback(mqttConnectOptionsCallback);
        }
        return mqttClientFactoryBuilder;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
        this.properties.getConfig().forEach(this::init);
    }

    private void init(String channelName, Config config) {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
        ClientRole clientRole = config.getClientRole();
        switch (clientRole) {
            case CONSUMER_AND_PRODUCER -> {
                buildConsumerClient(beanFactory, channelName, config);
                buildProducerClient(beanFactory, channelName, config);
            }
            case CONSUMER -> buildConsumerClient(beanFactory, channelName, config);
            case PRODUCER -> buildProducerClient(beanFactory, channelName, config);
        }
    }

    private void buildConsumerClient(DefaultListableBeanFactory beanFactory, String channelName, Config config) {
        // 注册 MQTT 消息输入通道，beanName 为 channelName + "_in"
        beanFactory.registerBeanDefinition(channelName + "In", createMessageChannel());
        MessageChannel messageChannel = beanFactory.getBean(channelName, MessageChannel.class);
        // 注册 MQTT 消息输入通道的适配器，beanName 为 channelName + "_adapter"
        beanFactory.registerBeanDefinition(channelName + "Adapter", createMessageChannelAdapter(channelName, config, messageChannel));
    }

    private void buildProducerClient(DefaultListableBeanFactory beanFactory, String channelName, Config config) {
        // 注册 MQTT 消息输出通道，beanName 为 channelName + "_out"
        beanFactory.registerBeanDefinition(channelName + "Out", createMessageChannel());
        // 注册 MqttPahoMessageHandler 用于发布消息，beanName 为 channelName + "_handler"
        beanFactory.registerBeanDefinition(channelName + "Handler", createPahoMessageHandler(channelName, config));
        MqttPahoMessageHandler pahoMessageHandler = beanFactory.getBean(channelName + "Handler", MqttPahoMessageHandler.class);
        // pahoMessageHandler 写入缓存
        MqttUtils.put(channelName, pahoMessageHandler);
    }


    /**
     * 创建消息通道的 bean 定义
     * @return 消息通道的 bean 定义
     */
    private AbstractBeanDefinition createMessageChannel() {
        BeanDefinitionBuilder channelBuilder = BeanDefinitionBuilder.genericBeanDefinition(DirectChannel.class);
        channelBuilder.setScope(BeanDefinition.SCOPE_SINGLETON);
        return channelBuilder.getBeanDefinition();
    }

    /**
     * 创建消息通道适配器的 bean 定义
     * @param channelName 通道名称
     * @param config MQTT 配置详情
     * @param mqttChannel 消息通道
     * @return 消息通道适配器的 bean 定义
     */
    private AbstractBeanDefinition createMessageChannelAdapter(String channelName, Config config, MessageChannel mqttChannel) {
        BeanDefinitionBuilder messageProducerBuilder = BeanDefinitionBuilder.genericBeanDefinition(MqttPahoMessageDrivenChannelAdapter.class);
        messageProducerBuilder.setScope(BeanDefinition.SCOPE_SINGLETON);
        messageProducerBuilder.addConstructorArgValue(config.getConsumerClientId());
        messageProducerBuilder.addConstructorArgValue(mqttClientSettings().buildConsumerClientFactory(channelName, config));
        messageProducerBuilder.addConstructorArgValue(config.getTopics());
        messageProducerBuilder.addPropertyValue("converter", new DefaultPahoMessageConverter());
        messageProducerBuilder.addPropertyValue("qos", config.getQos());
        messageProducerBuilder.addPropertyValue("outputChannel", mqttChannel);
        return messageProducerBuilder.getBeanDefinition();
    }

    /**
     * 创建 PahoMessageHandler 的 bean 定义
     * @param channelName 通道名称
     * @param config MQTT 配置详情
     * @return PahoMessageHandler 的 bean 定义
     */
    private AbstractBeanDefinition createPahoMessageHandler(String channelName, Config config) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MqttPahoMessageHandler.class);
        builder.addConstructorArgValue(config.getProducerClientId());
        builder.addConstructorArgValue(mqttClientSettings().buildProducerClientFactory(channelName, config));
        builder.addPropertyValue("async", config.getAsync());
        return builder.getBeanDefinition();
    }
}
