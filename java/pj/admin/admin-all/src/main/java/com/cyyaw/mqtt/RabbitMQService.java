package com.cyyaw.mqtt;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.cyyaw.config.RabbitConfig;
import com.cyyaw.mqtt.handle.MsgHandle;
import com.cyyaw.mqtt.handle.UserBean;
import com.cyyaw.mqtt.handle.WebRtcMsgHandle;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private Map<String, MsgHandle> msgHandleMap;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("ssssssssss", message);
    }

    public String receiveMessage() {
        return (String) rabbitTemplate.receiveAndConvert("myQueue");
    }


    @RabbitListener(queues = RabbitConfig.MQTT_QUEUE)
    public void listenSimpleQueueMessage(Message message, Channel channel) throws IOException {
        long tag = message.getMessageProperties().getDeliveryTag();
        String data = new String(message.getBody());
        System.out.println("spring 消费者接收到消息 ：【" + data + "】");
        if (data.length() > 0) {
            JSONObject json = new JSONObject(data);
            MsgData msgData = json.toBean(MsgData.class);
            String type = msgData.getType();
            for (String key : msgHandleMap.keySet()) {
                MsgHandle msgHandle = msgHandleMap.get(key);
                if (msgHandle.getHandleCode().equals(type)) {
                    msgHandle.handle(msgData.getFrom(), msgData.getTo(), msgData.getData());
                }
            }
        }

        try {
            channel.basicAck(tag, false);
        } catch (IOException e) {
            channel.basicNack(tag, false, true);
        }
    }

    @RabbitListener(queues = RabbitConfig.EVENT_QUEUE)
    public void handleDeviceConnectedEvent(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        MessageProperties messageProperties = message.getMessageProperties();
        Map<String, Object> headers = messageProperties.getHeaders();
        String receivedRoutingKey = messageProperties.getReceivedRoutingKey();

        String id = null;
        JSONArray array = new JSONArray(headers.get("client_properties"));
        if (null != array && array.size() > 0) {
            String str = array.getStr(0);
            str.toString();
            String[] split = str.split(",");
            if (split.length >= 3) {
                id = split[2].replace("<<\"", "").replace("\">>}", "").replace("chat_application_", "");
            }
        }

        if ("connection.created".equals(receivedRoutingKey)) {
            messageProperties.getHeader("queue");
            // 通知 我的好友 在线
            UserBean userBean = new UserBean();
            userBean.setUserId(id);
            WebRtcMsgHandle.userBeans.put(id, userBean);
            System.out.println("设备上线 event: " + receivedRoutingKey + "   " + headers);

        } else if ("connection.closed".equals(receivedRoutingKey)) {
            messageProperties.getHeader("queue");
            // 通知我的好友 离线
            System.out.println("设备下线 event: " + receivedRoutingKey + "   " + headers);
            WebRtcMsgHandle.userBeans.remove(id);
        }

        System.out.println("ID : " + id);
        if ("".equals("")) {
            System.out.println("业务正常处理，确认消息");
            channel.basicAck(tag, false);
        } else {
            System.out.println("业务处理失败，拒绝接收消息");
            channel.basicNack(tag, false, true);
        }
    }


    //接收消息
    @RabbitListener(queues = RabbitConfig.DEAD_QUEUE)
    public void receiveD(Message message, Channel channel) throws Exception {
        String msg = new String(message.getBody());
        log.info("当前时间：{}，发送一条消息给两个TTL队列：{}", new Date().toString(), msg);
    }


    @RabbitListener(queues = RabbitConfig.DELAY_QUEUE)
    public void receiveDelayedQueue(Message message, Channel channel) {
        String msg = new String(message.getBody());
        log.info("当前时间：{},收到延时队列的消息：{}", new Date().toString(), msg);
    }

}
