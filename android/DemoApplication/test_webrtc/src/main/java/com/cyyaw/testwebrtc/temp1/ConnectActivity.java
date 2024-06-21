package com.cyyaw.testwebrtc.temp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cyyaw.testwebrtc.R;
import com.cyyaw.testwebrtc.rtc.engine.webrtc.RtcConfig;
import com.cyyaw.testwebrtc.rtc.render.ProxyVideoSink;
import com.cyyaw.testwebrtc.temp1.rtc.RTCEngine;
import com.cyyaw.testwebrtc.temp1.rtc.RTCPeer;
import com.cyyaw.testwebrtc.temp1.socket.AppRTCClient;
import com.cyyaw.testwebrtc.temp1.socket.DirectRTCClient;
import com.cyyaw.testwebrtc.temp1.utils.StatsReportUtil;

import org.webrtc.EglBase;
import org.webrtc.IceCandidate;
import org.webrtc.RTCStatsReport;
import org.webrtc.RendererCommon;
import org.webrtc.SessionDescription;
import org.webrtc.SurfaceViewRenderer;


/**
 *
 */
public class ConnectActivity extends AppCompatActivity implements AppRTCClient.SignalingEvents, RTCPeer.PeerConnectionEvents {
    private static final String TAG = "ConnectActivity";
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

    private StatsReportUtil statsReportUtil;

    /**
     * 参数
     */
    public static void launchActivity(Activity activity, int roleType, String ip) {
        Intent intent = new Intent(activity, ConnectActivity.class);
        intent.putExtra(ARG_ROLE_TYPE, roleType);
        intent.putExtra(ARG_IP_ADDRESS, ip);
        activity.startActivity(intent);
    }


    /**
     * SurfaceViewRenderer 是 WebRTC 提供的一个用于显示视频的视图，
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 加载布局
        setContentView(R.layout.activity_connect2);
        // 接收参数
        Intent intent = getIntent();
        int mRoleType = intent.getIntExtra(ARG_ROLE_TYPE, 0);
        mIpAddress = intent.getStringExtra(ARG_IP_ADDRESS);
        mIsServer = mRoleType == TYPE_SERVER;
        // ======================================================= 大视窗
        mFullView = findViewById(R.id.full_surface_render);
        // 小视窗
        mPipView = findViewById(R.id.pip_surface_render);
        // 信息窗
        callStatsView = findViewById(R.id.callStats);
        // 初始化RTC
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
        mRtcEngine = new RTCEngine(getApplicationContext(), eglBase, localProxyVideoSink, RtcConfig.getDifaulWebRtcDevice());


        statsReportUtil = new StatsReportUtil();

        //=============================================================== 初始化socket 网络
        // 时间
        callStartedTimeMs = System.currentTimeMillis();
        //
        mDirectRTCClient = new DirectRTCClient(this);
        // 连接房间
        mDirectRTCClient.connectToRoom(new AppRTCClient.RoomConnectionParameters(mIpAddress));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        disconnect();
        if (logToast != null) {
            logToast.cancel();
        }
        super.onDestroy();
    }


    public void onHungUp(View view) {
        disconnect();
    }

    public void OnMicrophone(View view) {
        Log.d(TAG, "OnMicrophone: no impl");
    }

    public void OnSwitchCamera(View view) {
        mRtcEngine.switchCamera();
    }

    public void OnToggleBeauty(View view) {
        mRtcEngine.toggleBeautyEffect();
    }

    private void disconnect() {
        remoteProxyRenderer.setTarget(null);
        localProxyVideoSink.setTarget(null);
        if (mDirectRTCClient != null) {
            mDirectRTCClient.disconnectFromRoom();
            mDirectRTCClient = null;
        }
        if (mPipView != null) {
            mPipView.release();
            mPipView = null;
        }
        if (mFullView != null) {
            mFullView.release();
            mFullView = null;
        }
        if (mRtcEngine != null) {
            mRtcEngine.close();
            mRtcEngine = null;
        }
        finish();
    }

    private boolean isSwappedFeeds;


    /**
     * 切换显示
     */
    private void setSwappedFeeds(boolean isSwappedFeeds) {
        this.isSwappedFeeds = isSwappedFeeds;
        // 本地
        localProxyVideoSink.setTarget(isSwappedFeeds ? mFullView : mPipView);
        // 远程
        remoteProxyRenderer.setTarget(isSwappedFeeds ? mPipView : mFullView);
        mFullView.setMirror(isSwappedFeeds);
        mPipView.setMirror(!isSwappedFeeds);
    }


    // region -------------------------------socket event-------------------------------------------
    //  连接进入房间
    @Override
    public void onConnectedToRoom(AppRTCClient.SignalingParameters params) {
        // runOnUiThread 运行ui 线程
        runOnUiThread(() -> {
            boolean initiator = params.initiator;
            mRtcEngine.createPeerConnection(this, remoteProxyRenderer);
            mRtcEngine.setVideoCodecType(RTCPeer.VIDEO_CODEC_H264);
            if (initiator) {
                // create offer
                mMainHandler.post(() -> logAndToast("create offer"));
                mRtcEngine.createOffer();
            } else {
                if (params.offerSdp != null) {
                    mRtcEngine.setRemoteDescription(params.offerSdp);
                    // create answer
                    mMainHandler.post(() -> logAndToast("create answer"));
                    mRtcEngine.createAnswer();
                }
            }
        });

    }

    @Override
    public void onRemoteDescription(SessionDescription sdp) {
        final long delta = System.currentTimeMillis() - callStartedTimeMs;
        runOnUiThread(() -> {
            if (mRtcEngine == null) {
                Log.e(TAG, "Received remote SDP for non-initialized peer connection.");
                return;
            }
            mRtcEngine.setRemoteDescription(sdp);
            if (!mIsServer) {
                logAndToast("Creating ANSWER...");
                mRtcEngine.createAnswer();
            }
        });

    }

    @Override
    public void onRemoteIceCandidate(IceCandidate candidate) {
        runOnUiThread(() -> {
            if (mRtcEngine == null) {
                Log.e(TAG, "Received ICE candidate for a non-initialized peer connection.");
                return;
            }
            mRtcEngine.addRemoteIceCandidate(candidate);
        });
    }

    @Override
    public void onRemoteIceCandidatesRemoved(IceCandidate[] candidates) {
        runOnUiThread(() -> {
            if (mRtcEngine == null) {
                Log.e(TAG, "Received ICE candidate removals for a non-initialized peer connection.");
                return;
            }
            mRtcEngine.removeRemoteIceCandidates(candidates);
        });
    }

    @Override
    public void onChannelClose() {
        runOnUiThread(() -> {
            logAndToast("Remote end hung up; dropping PeerConnection");
            disconnect();
        });
    }

    @Override
    public void onChannelError(String description) {
        runOnUiThread(() -> logAndToast(description));

    }

    // endregion

    // region -------------------------------connection event   连接事件---------------------------------------

    @Override
    public void onLocalDescription(SessionDescription desc) {
        final long delta = System.currentTimeMillis() - callStartedTimeMs;
        runOnUiThread(() -> {
            logAndToast("Sending " + desc.type + ", delay=" + delta + "ms");
            if (mIsServer) {
                mDirectRTCClient.sendOfferSdp(desc);
            } else {
                mDirectRTCClient.sendAnswerSdp(desc);
            }
        });

    }

    @Override
    public void onIceCandidate(IceCandidate candidate) {
        runOnUiThread(() -> {
            if (mDirectRTCClient != null) {
                mDirectRTCClient.sendLocalIceCandidate(candidate);
            }

        });

    }

    @Override
    public void onIceCandidatesRemoved(IceCandidate[] candidates) {
        runOnUiThread(() -> {
            if (mDirectRTCClient != null) {
                mDirectRTCClient.sendLocalIceCandidateRemovals(candidates);
            }
        });
    }

    @Override
    public void onIceConnected() {
        final long delta = System.currentTimeMillis() - callStartedTimeMs;
        runOnUiThread(() -> logAndToast("ICE connected, delay=" + delta + "ms"));
    }

    @Override
    public void onIceDisconnected() {
        runOnUiThread(() -> logAndToast("ICE disconnected"));
    }

    @Override
    public void onConnected() {
        final long delta = System.currentTimeMillis() - callStartedTimeMs;
        runOnUiThread(() -> {
            logAndToast("DTLS connected, delay=" + delta + "ms");
            mRtcEngine.enableStatsEvents(true, 1000);
            mRtcEngine.setBitrateRange(500, 2000);
        });

    }

    @Override
    public void onDisconnected() {
        runOnUiThread(() -> {
            logAndToast("DTLS disconnected");
            disconnect();
        });
    }

    @Override
    public void onPeerConnectionError(String description) {
        logAndToast(description);
    }

    @Override
    public void onPeerConnectionStatsReady(RTCStatsReport report) {
        Log.d(TAG, "onPeerConnectionStatsReady: " + report);
        String statsReport = statsReportUtil.getStatsReport(report);

        // 信息
        runOnUiThread(() -> callStatsView.setText(statsReport));
    }

    //endregion

    private Toast logToast;

    private void logAndToast(String msg) {
        Log.d(TAG, msg);
        if (logToast != null) {
            logToast.cancel();
        }
        logToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        logToast.show();
    }

}