package com.cyyaw.mqtt;


import cn.hutool.json.JSONObject;
import com.cyyaw.equipment.service.EqEquipmentService;
import com.cyyaw.equipment.table.entity.EqEquipment;
import com.cyyaw.mqtt.entity.PwDev;
import com.cyyaw.mqtt.entity.PwJson;
import com.cyyaw.mqtt.handle.HandleUtils;
import com.cyyaw.mqtt.rabbit.RabbitMqMqtt;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PwdDevHandle {


    @Autowired
    private EqEquipmentService eqEquipmentService;

    @Autowired
    private AmqpTemplate amqpTemplate;


    /**
     * 1.申请账号
     */
    @RabbitListener(queues = RabbitMqMqtt.MQTT_PWD_DEV)
    public void receiveD(Message message, Channel channel, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
        String msg = new String(message.getBody());
        log.info("【 申请账号 】{}  {}", routingKey, msg);
        JSONObject entries = new JSONObject(msg);
        JSONObject request = entries.getJSONObject("Request");
        PwDev.PkType pkType = request.getJSONObject("PK_Type").toBean(PwDev.PkType.class);
        PwDev.Values values = request.getJSONObject("Info").getJSONObject("Values").toBean(PwDev.Values.class);
        String registerId = pkType.getRegisterId();
        String txnNo = pkType.getTxnNo();
        String deviceId = pkType.getDeviceId();

         EqEquipment equipment = eqEquipmentService.findByTid(registerId);
        PwJson.Info info = new PwJson.Info();
        if (null != equipment) {
            info.setResult("true");
            info.setAccountAck("");
            info.setPwdAck("");
        } else {
            info.setResult("false");
            info.setAccountAck("");
            info.setPwdAck("");
        }
        PwJson.PkType pt = new PwJson.PkType();
        pt.setType("GET_pwd_ACK");
        pt.setTxnNo(txnNo);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        // 回复
        String sendStr = HandleUtils.getSendStr(pt, info);
        amqpTemplate.convertAndSend(RabbitMqMqtt.MQTT_EXCHANGE, recoverTopic(routingKey), sendStr);
    }


    public String recoverTopic(String key) {
        int index = key.indexOf(".");
        if (index != -1) {
            return "topic_json_pw" + key.substring(index);
        }
        return "";
    }

}
