package com.cyyaw.config.rabbit;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqMqtt {

    // MQTT交换机
    private static final String MQTT_EXCHANGE = "amq.topic";
    public static final String MQTT_QUEUE = "mqtt_service";


    //===
    private static final String MQTT_SERVER_EXCHANGE = "mqtt.server.exchange";

    private static final String MQTT_SERVER_QUEUE = "mqtt.server.queue";


    // ============================================      虚拟主机


    // ====================================================================================================================================
    // ====================================================================================================================================
    // ====================================================================================================================================      交换机
    // ====================================================================================================================================
    // ====================================================================================================================================

    @Bean
    public Exchange mqttExchange() {
        return ExchangeBuilder.topicExchange(MQTT_EXCHANGE).durable(true).build();
    }



    @Bean
    public Exchange mqttServerExchange() {
        return ExchangeBuilder.topicExchange(MQTT_SERVER_EXCHANGE).durable(true).build();
    }


    // =====================================================================================================================================
    // =====================================================================================================================================
    // =====================================================================================================================================      队列
    // =====================================================================================================================================
    // =====================================================================================================================================

    /**
     * mqtt
     */
    @Bean
    public Queue mqttQueue() {
        Map<String, Object> arguments = new HashMap<>();
        //设置死信交换机
        arguments.put("x-dead-letter-exchange", RabbitMqDead.DEAD_EXCHANGE);
        //设置死信routingKey
        arguments.put("x-dead-letter-routing-key", "x.dead.letter.routing.key");
        // 设置死信时间 ( 10秒 )
        arguments.put("x-message-ttl", 10000);
        return QueueBuilder.durable(MQTT_QUEUE).withArguments(arguments).build();
    }



    @Bean
    public Queue mqttServerQueue() {
        Map<String, Object> arguments = new HashMap<>();
        //设置死信交换机
        arguments.put("x-dead-letter-exchange", RabbitMqDead.DEAD_EXCHANGE);
        //设置死信routingKey
        arguments.put("x-dead-letter-routing-key", "x.dead.letter.routing.key");
        // 设置死信时间 ( 10秒 )
        arguments.put("x-message-ttl", 10000);
        return QueueBuilder.durable(MQTT_SERVER_QUEUE).withArguments(arguments).build();
    }

    // =================================================================================================================================
    // =================================================================================================================================
    // =================================================================================================================================      队列 绑定 交换机
    // =================================================================================================================================
    // =================================================================================================================================

    @Bean
    public Binding bindingMqtt() {
        return BindingBuilder.bind(mqttQueue()).to(mqttExchange()).with("mqtt_service.#").noargs();
    }



    @Bean
    public Binding bindingMqttServer() {
        return BindingBuilder.bind(mqttServerQueue()).to(mqttServerExchange()).with("#").noargs();
    }


    @Bean
    public Binding bindingMqttToMqttServer() {
        return BindingBuilder.bind(mqttServerExchange()).to(mqttExchange()).with("#").noargs();
    }

}
