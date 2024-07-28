package com.cyyaw.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MqttConfig {

    String broker = "tcp://192.168.0.158:1883";
    private String clientId = "1111111111111111111111111111111111111111111111111111111111";

    @Bean
    public MqttClient client() throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttClient client = new MqttClient(broker, clientId, persistence);
        // MQTT 连接选项
        MqttConnectOptions connOpts = new MqttConnectOptions();
        // 用户名
//            connOpts.setUserName("mqtt_test:mqtt_test_user");
        connOpts.setUserName("admin");
        // 密码
        connOpts.setPassword("123456".toCharArray());
        // 保留会话
        connOpts.setCleanSession(true);
        // 建立连接
        client.connect(connOpts);

        System.out.println("====================       mqtt 连接成功   ===========================");
        return client;
    }


}
