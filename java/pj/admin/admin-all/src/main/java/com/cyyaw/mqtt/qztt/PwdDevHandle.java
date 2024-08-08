package com.cyyaw.mqtt.qztt;


import com.cyyaw.config.rabbit.RabbitMqMqtt;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class PwdDevHandle {


    @RabbitListener(queues = RabbitMqMqtt.MQTT_PWD_DEV)
    public void receiveD(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        String msg = new String(message.getBody());
        log.info("【 sssssssssssss 】{} {}", new Date().toString(), msg);
        channel.basicAck(tag, false);
    }


}
