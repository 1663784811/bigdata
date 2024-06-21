package com.cyyaw.testwebrtc.rtc.engine.webrtc;

import android.content.Context;

import org.webrtc.DefaultVideoDecoderFactory;
import org.webrtc.DefaultVideoEncoderFactory;
import org.webrtc.EglBase;
import org.webrtc.MediaConstraints;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.VideoDecoderFactory;
import org.webrtc.VideoEncoderFactory;
import org.webrtc.audio.AudioDeviceModule;
import org.webrtc.audio.JavaAudioDeviceModule;

import java.util.ArrayList;
import java.util.List;

public class RtcConfig {

    private static final String VIDEO_FLEXFEC_FIELDTRIAL = "WebRTC-FlexFEC-03-Advertised/Enabled/WebRTC-FlexFEC-03/Enabled/";
    private static final String DISABLE_WEBRTC_AGC_FIELDTRIAL = "WebRTC-Audio-MinimizeResamplingOnMobile/Enabled/";


    private RtcConfig() {
    }

    public static List<PeerConnection.IceServer> getIceServer() {
        List<PeerConnection.IceServer> rest = new ArrayList<>();
        PeerConnection.IceServer var1 = PeerConnection.IceServer.builder("stun:stun.l.google.com:19302").createIceServer();
        rest.add(var1);
        PeerConnection.IceServer var11 = PeerConnection.IceServer.builder("stun:42.192.40.58:3478").createIceServer();
        PeerConnection.IceServer var12 = PeerConnection.IceServer.builder("turn:42.192.40.58:3478").setUsername("ddssingsong").setPassword("123456").createIceServer();
        rest.add(var11);
        rest.add(var12);
        return rest;
    }


    /**
     * 配置音频参数
     */
    public static MediaConstraints createAudioConstraints() {
        MediaConstraints audioConstraints = new MediaConstraints();
        audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair("googEchoCancellation", "true"));
        audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair("googAutoGainControl", "false"));
        audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair("googHighpassFilter", "false"));
        audioConstraints.mandatory.add(new MediaConstraints.KeyValuePair("googNoiseSuppression", "true"));
        return audioConstraints;
    }


    /**
     * 创建连接工厂
     */
    public static PeerConnectionFactory createConnectionFactory(Context mContext, EglBase eglBase) {
        // 1. 初始化的方法，必须在开始之前调用
        PeerConnectionFactory.InitializationOptions initializationOptions = PeerConnectionFactory.InitializationOptions.builder(mContext).createInitializationOptions();
        PeerConnectionFactory.initialize(initializationOptions);
        // 2. 设置编解码方式：默认方法
        final VideoEncoderFactory encoderFactory = new DefaultVideoEncoderFactory(eglBase.getEglBaseContext(), true, true);
        final VideoDecoderFactory decoderFactory = new DefaultVideoDecoderFactory(eglBase.getEglBaseContext());
        // 3. 构造Factory
        AudioDeviceModule audioDeviceModule = JavaAudioDeviceModule.builder(mContext).createAudioDeviceModule();
        // 4. create connectFactory
        PeerConnectionFactory.Options options = new PeerConnectionFactory.Options();
        PeerConnectionFactory factory = PeerConnectionFactory.builder()
                .setOptions(options)
                .setAudioDeviceModule(audioDeviceModule)
                .setVideoEncoderFactory(encoderFactory)
                .setVideoDecoderFactory(decoderFactory)
                .createPeerConnectionFactory();
        // 释放
        audioDeviceModule.release();
        return factory;
    }


    public static String getFieldTrials() {
        return VIDEO_FLEXFEC_FIELDTRIAL + DISABLE_WEBRTC_AGC_FIELDTRIAL;
    }


}
