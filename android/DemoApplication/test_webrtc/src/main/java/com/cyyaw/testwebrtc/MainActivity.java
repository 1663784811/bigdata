package com.cyyaw.testwebrtc;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.cyyaw.common.BaseAppCompatActivity;
import com.cyyaw.common.permission.PermissionsCode;
import com.cyyaw.testwebrtc.rtc.ProxyVideoSink;
import com.cyyaw.testwebrtc.rtc.RTCEngine;
import com.cyyaw.testwebrtc.rtc.RTCPeer;
import com.cyyaw.testwebrtc.socket.AppRTCClient;
import com.cyyaw.testwebrtc.socket.DirectRTCClient;

import org.webrtc.EglBase;
import org.webrtc.IceCandidate;
import org.webrtc.RTCStatsReport;
import org.webrtc.RendererCommon;
import org.webrtc.SessionDescription;
import org.webrtc.SurfaceViewRenderer;

public class MainActivity extends BaseAppCompatActivity implements AppRTCClient.SignalingEvents, RTCPeer.PeerConnectionEvents {

    private static final String TAG = "MainActivity";
    private SurfaceViewRenderer mFullView;
    private SurfaceViewRenderer mPipView;
    private TextView callStatsView;

    private String mIpAddress;
    private DirectRTCClient mDirectRTCClient;
    private boolean mIsServer;

    private RTCEngine mRtcEngine;
    private final ProxyVideoSink remoteProxyRenderer = new ProxyVideoSink();
    private final ProxyVideoSink localProxyVideoSink = new ProxyVideoSink();
    private long callStartedTimeMs;

    public static final String ARG_ROLE_TYPE = "roleType";
    public static final String ARG_IP_ADDRESS = "ipAddress";
    public static final int TYPE_SERVER = 0;
    public static final int TYPE_CLIENT = 1;
    private final Handler mMainHandler = new Handler(Looper.getMainLooper());

//    private StatsReportUtil statsReportUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissionsFn(PermissionsCode.CAMERA, () -> {
            requestPermissionsFn(PermissionsCode.RECORD_AUDIO, () -> {
                init();
            });
        });
    }

    private void init() {
        mIpAddress = "0.0.0.0";
        mIsServer = true;
        // 大视窗
        mFullView = findViewById(R.id.full_surface_render);
        // 小视窗
        mPipView = findViewById(R.id.pip_surface_render);
        // 信息窗
        callStatsView = findViewById(R.id.callStats);

        // start init render
        // EglBase 是 WebRTC 中用于管理 OpenGL ES 环境的基础类
        final EglBase eglBase = EglBase.create();
        // full
        mFullView.init(eglBase.getEglBaseContext(), null);
        // 设置类型
        mFullView.setScalingType(RendererCommon.ScalingType.SCALE_ASPECT_FILL);

        // ============== pip
        mPipView.init(eglBase.getEglBaseContext(), new RendererCommon.RendererEvents() {
            @Override
            public void onFirstFrameRendered() {

            }

            @Override
            public void onFrameResolutionChanged(int videoWidth, int videoHeight, int rotation) {

            }
        });
        mPipView.setScalingType(RendererCommon.ScalingType.SCALE_ASPECT_FIT);
        // 设置事件
        mPipView.setOnClickListener(v -> setSwappedFeeds(!isSwappedFeeds));


        //
        localProxyVideoSink.setTarget(mPipView);
        remoteProxyRenderer.setTarget(mFullView);
        //
        mRtcEngine = new RTCEngine(getApplicationContext(), eglBase, localProxyVideoSink);
//        statsReportUtil = new StatsReportUtil();


    }


    private boolean isSwappedFeeds;

    private void setSwappedFeeds(boolean isSwappedFeeds) {
        this.isSwappedFeeds = isSwappedFeeds;
        localProxyVideoSink.setTarget(isSwappedFeeds ? mFullView : mPipView);
        remoteProxyRenderer.setTarget(isSwappedFeeds ? mPipView : mFullView);
        mFullView.setMirror(isSwappedFeeds);
        mPipView.setMirror(!isSwappedFeeds);
    }


    @Override
    public void onConnectedToRoom(AppRTCClient.SignalingParameters params) {

    }

    @Override
    public void onRemoteDescription(SessionDescription sdp) {

    }

    @Override
    public void onRemoteIceCandidate(IceCandidate candidate) {

    }

    @Override
    public void onRemoteIceCandidatesRemoved(IceCandidate[] candidates) {

    }

    @Override
    public void onChannelClose() {

    }

    @Override
    public void onChannelError(String description) {

    }

    @Override
    public void onLocalDescription(SessionDescription sdp) {

    }

    @Override
    public void onIceCandidate(IceCandidate candidate) {

    }

    @Override
    public void onIceCandidatesRemoved(IceCandidate[] candidates) {

    }

    @Override
    public void onIceConnected() {

    }

    @Override
    public void onIceDisconnected() {

    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onPeerConnectionError(String description) {

    }

    @Override
    public void onPeerConnectionStatsReady(RTCStatsReport report) {

    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
