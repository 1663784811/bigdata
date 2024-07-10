package com.cyyaw.mq1;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // ============================================      虚拟主机


    // ============================================      交换机

//    /**
//     * Direct 交换机
//     * 1. 直连交换机的路由算法非常简单：将消息准送到binding key与该消息的routing key相同的队列
//     */
//    @Bean
//    public DirectExchange directExchange() {
//        DirectExchange exchange = new DirectExchange("test_direct_exchange", true, false);
//        return exchange;
//    }
//
//    /**
//     * topic
//     * 主题交换机
//     * 发送到主题交换机的消息不能有任意的routing key,必须是由点号分开的一串单词，这些单词可以是任意的，但通常是与消息相关的一些特
//     * <p>
//     * *  表示匹配任意一个单词
//     * #  表示匹配任意一个或多个单词
//     */
//    @Bean
//    public TopicExchange topicExchange() {
//        return new TopicExchange("test_topic_exchange", true, false);
//    }
//
//    /**
//     * Fanout扇形交换机
//     * 是最基本的交换机类型，它所能做的事清非常简单广播消息
//     */
//    @Bean
//    public FanoutExchange fanoutExchange() {
//        return new FanoutExchange("test_fanout_exchange", true, false);
//    }
//
//    /**
//     * 根据消息头属性而不是路由键来路由消息。绑定时，可以指定一组键值对，
//     * 只有当消息的头属性与绑定中指定的键值对匹配时，消息才会被路由到队列。
//     */
//    @Bean
//    public HeadersExchange headersExchange() {
//        return new HeadersExchange("test_headers_exchange", true, false);
//    }


    @Bean
    public Exchange eventExchange() {
        return ExchangeBuilder.topicExchange("amq.rabbitmq.event").durable(true).internal().build();
    }

    // =========================================================================      队列

//    /**
//     *
//     */
//    @Bean
//    public Queue topicQueue1() {
//        return new Queue("test_topicQueue1");
//    }
//
//    /**
//     *
//     */
//    @Bean
//    public Queue topicQueue2() {
//        return new Queue("test_topicQueue2");
//    }


    /**
     * 事件队列
     */
    @Bean
    public Queue eventQueue() {
        return QueueBuilder.durable( "event_queue").build();
    }

    // ============================================      队列 绑定 交换机
//    @Bean
//    public Binding topicBinding1() {
//        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("ss007.id.*");
//    }
//
//    @Bean
//    public Binding topicBinding2() {
//        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("ss007.name.*");
//    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(eventQueue()).to(eventExchange()).with("connection.#").noargs();
    }

}
