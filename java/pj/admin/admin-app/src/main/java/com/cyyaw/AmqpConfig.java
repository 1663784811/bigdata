package com.cyyaw;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
class AmqpConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Lazy
    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory);
    }

    @Lazy
    @Bean
    public SimpleMessageListenerContainer listenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }

    public void initializeAmqpComponents() {
        // 手动初始化 AMQP 组件
        rabbitTemplate();
        SimpleMessageListenerContainer listenerContainer = listenerContainer();
        // 在此处启动 Listener 容器
        listenerContainer.start();
        System.out.println(" ===========================  AMQP Components Initialized");
    }
}
