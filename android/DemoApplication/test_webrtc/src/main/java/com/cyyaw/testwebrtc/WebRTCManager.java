//package com.cyyaw.testwebrtc;
//
//import android.content.Context;
//
//import org.webrtc.EglBase;
//import org.webrtc.PeerConnectionFactory;
//
//public class WebRTCManager {
//
//    private PeerConnectionFactory peerConnectionFactory;
//    private EglBase eglBase;
//
//    public WebRTCManager(Context context) {
//        PeerConnectionFactory.initialize(
//                PeerConnectionFactory.InitializationOptions.builder(context)
//                        .setEnableInternalTracer(true)
//                        .createInitializationOptions()
//        );
//
//        eglBase = EglBase.create();
//
//        PeerConnectionFactory.Options options = new PeerConnectionFactory.Options();
//        peerConnectionFactory = PeerConnectionFactory.builder()
//                .setOptions(options)
//                .setVideoDecoderFactory(new DefaultVideoDecoderFactory(eglBase.getEglBaseContext()))
//                .setVideoEncoderFactory(new DefaultVideoEncoderFactory(eglBase.getEglBaseContext(), true, true))
//                .createPeerConnectionFactory();
//    }
//
//    public EglBase getEglBase() {
//        return eglBase;
//    }
//
//    public PeerConnectionFactory getPeerConnectionFactory() {
//        return peerConnectionFactory;
//    }
//
//    public void release() {
//        if (eglBase != null) {
//            eglBase.release();
//        }
//        if (peerConnectionFactory != null) {
//            peerConnectionFactory.dispose();
//        }
//    }
//}