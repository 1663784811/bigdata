package com.cyyaw.mq1;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @RabbitListener(queues = "mqtt_test_service")
    public void listenSimpleQueueMessage(String msg) throws InterruptedException {
        System.out.println("spring 消费者接收到消息 ：【" + msg + "】");
    }

}
