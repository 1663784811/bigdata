package com.cyyaw.mq1;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("ssssssssss", message);
    }

    public String receiveMessage() {
        return (String) rabbitTemplate.receiveAndConvert("myQueue");
    }


    //    @RabbitListener(queues = "mqtt_test_service")
//    public void listenSimpleQueueMessage(String msg) throws InterruptedException {
//        System.out.println("spring 消费者接收到消息 ：【" + msg + "】");
//    }
//
//
    @RabbitListener(queues = "event_queue")
    public void handleDeviceConnectedEvent(Message message) {
        MessageProperties messageProperties = message.getMessageProperties();
        Map<String, Object> headers = messageProperties.getHeaders();
        String receivedRoutingKey = messageProperties.getReceivedRoutingKey();
        if ("connection.created".equals(receivedRoutingKey)) {
            messageProperties.getHeader("queue");

        } else if ("connection.closed".equals(receivedRoutingKey)) {
            messageProperties.getHeader("queue");

        }
        System.out.println("" + headers.get("client_properties"));
        System.out.println("消费者接收到消息:" + receivedRoutingKey + "   " + headers);
    }

}
