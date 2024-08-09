package com.cyyaw.mqtt;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.cyyaw.equipment.service.EqEquipmentService;
import com.cyyaw.equipment.table.entity.EqEquipment;
import com.cyyaw.mqtt.entity.PwDev;
import com.cyyaw.mqtt.entity.PwJson;
import com.cyyaw.mqtt.handle.HandleUtils;
import com.cyyaw.mqtt.rabbit.RabbitMqMqtt;
import com.cyyaw.util.tools.WhyStringUtil;
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
        JSONObject entries = new JSONObject(msg);
        log.info("【 申请账号 】{}  {}", routingKey, entries);
        JSONObject request = entries.getJSONObject("Request");
        PwDev.PkType pkType = request.getJSONObject("PK_Type").toBean(PwDev.PkType.class);
        PwDev.Values values = request.getJSONObject("Info").getJSONObject("Values").toBean(PwDev.Values.class);
        String registerId = pkType.getRegisterId();
        String txnNo = pkType.getTxnNo();
        String deviceId = pkType.getDeviceId();
        String pd = values.getPwd();
        String ac = values.getAccount();

        EqEquipment equipment = eqEquipmentService.findByTid(registerId);
        PwJson.Info info = new PwJson.Info();
        boolean ok = false;
        if (null != equipment) {
            String code = equipment.getCode();
            if (StrUtil.isBlank(code)) {
                // 验证默认账号密码
                String dfPwd = equipment.getDfPwd();
                String dfAccount = equipment.getDfAccount();
                if (pd.equals(dfPwd) && ac.equals(dfAccount)) {
                    // 生成账号、密码
                    String account = WhyStringUtil.getRandomString(8);
                    String pwd = WhyStringUtil.getRandomString(8);
                    equipment.setAccount(account);
                    equipment.setPwd(pwd);
                    eqEquipmentService.save(equipment);
                    ok = true;
                }
            }
        }
        if (ok) {
            info.setResult("true");
            info.setAccountAck(equipment.getAccount());
            info.setPwdAck(equipment.getPwd());
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
