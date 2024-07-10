package com.cyyaw.mqtt;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // ============================================      虚拟主机


    // ============================================      交换机
    @Bean
    public Exchange eventExchange() {
        return ExchangeBuilder.topicExchange("amq.rabbitmq.event").durable(true).internal().build();
    }

    @Bean
    public Exchange mqttExchange() {
        return ExchangeBuilder.topicExchange("amq.topic").durable(true).build();
    }

    // =========================================================================      队列
    /**
     * 事件队列
     */
    @Bean
    public Queue eventQueue() {
        return QueueBuilder.durable("event_queue").build();
    }
    /**
     * mqtt
     */
    @Bean
    public Queue mqttQueue() {
        return QueueBuilder.durable("mqtt_service").build();
    }

    // ============================================      队列 绑定 交换机
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(eventQueue()).to(eventExchange()).with("connection.#").noargs();
    }

    @Bean
    public Binding bindingMqtt() {
        return BindingBuilder.bind(mqttQueue()).to(mqttExchange()).with("#").noargs();
    }

}
