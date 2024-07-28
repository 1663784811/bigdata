package com.cyyaw.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    // 事件交换机
    private static final String EVENT_EXCHANGE = "amq.rabbitmq.event";
    // MQTT交换机
    private static final String MQTT_EXCHANGE = "amq.topic";
    public static final String MQTT_QUEUE = "mqtt_service";


    //死信交换机名称
    private static final String DEAD_EXCHANGE = "dead.exchange";

    //死信队列名称
    public static final String DEAD_QUEUE = "dead_queue";


    //延时交换机
    private static final String DELAY_EXCHANGE = "delay.exchange";

    //延时队列
    public static final String DELAY_QUEUE = "delay_queue";



    public static final String EVENT_QUEUE = "event_queue";

    // ============================================      虚拟主机


    // ============================================      交换机
    @Bean
    public Exchange eventExchange() {
        return ExchangeBuilder.topicExchange(EVENT_EXCHANGE).durable(true).internal().build();
    }

    @Bean
    public Exchange mqttExchange() {
        return ExchangeBuilder.topicExchange(MQTT_EXCHANGE).durable(true).build();
    }

    @Bean
    public Exchange deadExchange() {
        return ExchangeBuilder.directExchange(DEAD_EXCHANGE).build();
    }


    @Bean
    public Exchange delayedMessageExchange() {
        Map<String, Object> args = new HashMap<>();
        // 自定义交换机的类型
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAY_EXCHANGE, "x-delayed-message", true, false, args);
    }

    // =========================================================================      队列

    /**
     * 事件队列
     */
    @Bean
    public Queue eventQueue() {
        return QueueBuilder.durable(EVENT_QUEUE).build();
    }

    /**
     * mqtt
     */
    @Bean
    public Queue mqttQueue() {
        Map<String, Object> arguments = new HashMap<>();
        //设置死信交换机
        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        //设置死信routingKey
        arguments.put("x-dead-letter-routing-key", "x.dead.letter.routing.key");
        // 设置死信时间 ( 10秒 )
        arguments.put("x-message-ttl", 10000);
        return QueueBuilder.durable(MQTT_QUEUE).withArguments(arguments).build();
    }

    @Bean
    public Queue deadQueue() {
        return QueueBuilder.durable(DEAD_QUEUE).build();
    }


    @Bean
    public Queue delayedMessageQueue() {
        return QueueBuilder.durable(DELAY_QUEUE).build();
    }
    // ============================================      队列 绑定 交换机
    @Bean
    public Binding bindingEvent() {
        return BindingBuilder.bind(eventQueue()).to(eventExchange()).with("connection.#").noargs();
    }

    @Bean
    public Binding bindingMqtt() {
        return BindingBuilder.bind(mqttQueue()).to(mqttExchange()).with("#").noargs();
    }

    @Bean
    public Binding bindingDead() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("#").noargs();
    }

    @Bean
    public Binding bindingDelayedMessage() {
        return BindingBuilder.bind(delayedMessageQueue()).to(delayedMessageExchange()).with("#").noargs();
    }


}
