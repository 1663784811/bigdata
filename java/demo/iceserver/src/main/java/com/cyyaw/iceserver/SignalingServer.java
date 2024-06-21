package com.cyyaw.iceserver;

import lombok.SneakyThrows;
import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class SignalingServer extends WebSocketServer {

    private Map<String, WebSocket> clients = new HashMap<>();

    public SignalingServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("New connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        clients.values().remove(conn);
        System.out.println("Closed connection: " + conn.getRemoteSocketAddress());
    }

    @SneakyThrows
    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Received message: " + message);
        JSONObject json = new JSONObject(message);
        String type = json.getString("type");
        String userId = json.getString("userId");

        switch (type) {
            case "join":
                clients.put(userId, conn);
                broadcastUserJoined(userId, conn);
                break;
            case "offer":
            case "answer":
            case "candidate":
                forwardMessage(userId, json);
                break;
            case "leave":
                clients.remove(userId);
                broadcastUserLeft(userId);
                break;
            default:
                System.out.println("Unknown message type: " + type);
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("Server started successfully");
    }

    private void broadcastUserJoined(String userId, WebSocket conn) throws JSONException {
        JSONObject message = new JSONObject();
        message.put("type", "user-joined");
        message.put("userId", userId);
        broadcastMessage(message, conn);
    }

    private void broadcastUserLeft(String userId) throws JSONException {
        JSONObject message = new JSONObject();
        message.put("type", "user-left");
        message.put("userId", userId);
        broadcastMessage(message, null);
    }

    private void forwardMessage(String userId, JSONObject message) throws JSONException {
        String targetId = message.getString("targetId");
        WebSocket targetConn = clients.get(targetId);
        if (targetConn != null) {
            targetConn.send(message.toString());
        }
    }

    private void broadcastMessage(JSONObject message, WebSocket except) {
        for (WebSocket client : clients.values()) {
            if (client != except) {
                client.send(message.toString());
            }
        }
    }

    public static void main(String[] args) {
        int port = 8887;
        SignalingServer server = new SignalingServer(new InetSocketAddress(port));
        server.start();
        System.out.println("Signaling server started on port: " + port);
    }
}

