package com.cyyaw.mq1;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // ============================================      虚拟主机


    // ============================================      交换机

    /**
     * Direct 交换机
     * 1. 直连交换机的路由算法非常简单：将消息准送到binding key与该消息的routing key相同的队列
     * 2. ...
     */
    @Bean
    public DirectExchange directExchange() {
        DirectExchange exchange = new DirectExchange("ssssssssss", true, false);

        return exchange;
    }

    /**
     * topic
     * 主题交换机
     * 发送到主题交换机的消息不能有任意的routing key,必须是由点号分开的一串单词，这些单词可以是任意的，但通常是与消息相关的一些特
     * <p>
     * *  表示匹配任意一个单词
     * #  表示匹配任意一个或多个单词
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * Fanout扇形交换机
     * 是最基本的交换机类型，它所能做的事清非常简单广播消息
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * head Exchange
     *
     */
    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("headersExchange");
    }



    // ============================================      队列
    @Bean
    public Queue topicQueue1() {
        return new Queue("topicQueue1", true);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("topicQueue2", true);
    }

    // ============================================      队列 绑定 交换机
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("ss007.id.*");
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("ss007.name.*");
    }


}
