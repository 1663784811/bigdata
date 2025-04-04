package com.cyyaw.common;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttHelper {
    private static final String TAG = "MqttHelper";
    private MqttAndroidClient mqttAndroidClient;
    private final String serverUri = "tcp://broker.hivemq.com:1883"; // 替换为你的 MQTT 服务器 URI
    private final String clientId = MqttClient.generateClientId();

    // 主题
    private final String subscriptionTopic = "example/topic";

    public MqttHelper(Context context) {
        mqttAndroidClient = new MqttAndroidClient(context, serverUri, clientId);
        mqttAndroidClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                Log.d(TAG, "Connection lost: " + cause.getMessage());
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.d(TAG, "Message arrived: " + new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Log.d(TAG, "Delivery complete");
            }
        });
        connect();
    }

    /**
     * 连接
     */
    private void connect() {
        try {
            mqttAndroidClient.connect(null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "Connected successfully");
                    subscribeToTopic();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d(TAG, "Connection failed: " + exception.getMessage());
                }
            });
        } catch (MqttException e) {
            Log.d(TAG, "Exception while connecting: " + e.getMessage());
        }
    }

    /**
     * 订阅主题
     */
    private void subscribeToTopic() {
        try {
            mqttAndroidClient.subscribe(subscriptionTopic, 0, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "Subscribed to topic");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d(TAG, "Subscription failed: " + exception.getMessage());
                }
            });
        } catch (MqttException e) {
            Log.d(TAG, "Exception while subscribing: " + e.getMessage());
        }
    }

    /**
     * 发布消息
     */
    public void publishMessage(String message) {
        try {
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setPayload(message.getBytes());
            mqttAndroidClient.publish(subscriptionTopic, mqttMessage);
        } catch (MqttException e) {
            Log.d(TAG, "Exception while publishing: " + e.getMessage());
        }
    }

    /**
     * 关闭连接
     */
    public void disconnect() {
        try {
            mqttAndroidClient.disconnect();
        } catch (MqttException e) {
            Log.d(TAG, "Exception while disconnecting: " + e.getMessage());
        }
    }
}
