package com.cyyaw.testwebrtc.temp1.rtc;

import android.content.Context;
import android.util.Log;

import com.cyyaw.testwebrtc.aaaa.WebRtcDevice;
import com.cyyaw.testwebrtc.aaaa.webrtc.RtcConfig;
import com.cyyaw.testwebrtc.temp1.effect.RTCVideoEffector;
import com.cyyaw.testwebrtc.temp1.effect.VideoEffectProcessor;
import com.cyyaw.testwebrtc.temp1.effect.filter.GPUImageBeautyFilter;

import org.webrtc.AudioSource;
import org.webrtc.AudioTrack;
import org.webrtc.Camera1Enumerator;
import org.webrtc.CameraEnumerator;
import org.webrtc.CameraVideoCapturer;
import org.webrtc.EglBase;
import org.webrtc.IceCandidate;
import org.webrtc.MediaStreamTrack;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.RtpParameters;
import org.webrtc.RtpSender;
import org.webrtc.RtpTransceiver;
import org.webrtc.SessionDescription;
import org.webrtc.SurfaceTextureHelper;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoSink;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;

import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * RTC引擎
 */
public class RTCEngine {
    private static final String TAG = "RTCEngine";
    private final EglBase mRootEglBase;
    private PeerConnectionFactory mConnectionFactory;
    // video
    private VideoTrack mVideoTrack;
    private VideoSource mVideoSource;
    private VideoCapturer mVideoCapturer;
    private SurfaceTextureHelper mSurfaceTextureHelper;
    private VideoTrack mRemoteVideoTrack;
    private VideoEffectProcessor mVideoEffectProcessor;
    private RTCVideoEffector rtcVideoEffector;
    private GPUImageBeautyFilter gpuImageBeautyFilter;
    // audio
    private AudioTrack mAudioTrack;
    private AudioSource mAudioSource;

    private RTCPeer mPeer;
    // config

    private WebRtcDevice webRtcDevice;


    private static final int BPS_IN_KBPS = 1000;


    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public RTCEngine(Context context, EglBase eglBase, VideoSink localSink, WebRtcDevice webRtcDevice) {
        mRootEglBase = eglBase;
        this.webRtcDevice = webRtcDevice;
        // 开启线程
        executor.execute(() -> {
            // 创建连接工厂
            mConnectionFactory = RtcConfig.createConnectionFactory(context, mRootEglBase);
            // 创建视频轨道
            mVideoTrack = createVideoTrack(context, localSink);
            // 创建音频轨道
            mAudioTrack = createAudioTrack();
        });

    }

    /**
     * ===================================  创建视频轨道
     */
    private VideoTrack createVideoTrack(Context context, VideoSink localSink) {
        // 1. create video source  创建视频源
        mVideoSource = mConnectionFactory.createVideoSource(false);
        // 2. create video capture 创建视频捕获
        mVideoCapturer = createVideoCapture(context);
        // 3. start capture 开始捕获
        mSurfaceTextureHelper = SurfaceTextureHelper.create("CaptureThread", mRootEglBase.getEglBaseContext());
        // 初始化
        mVideoCapturer.initialize(mSurfaceTextureHelper, context, mVideoSource.getCapturerObserver());
        mVideoCapturer.startCapture(webRtcDevice.getVideoWidth(), webRtcDevice.getVideoHeight(), webRtcDevice.getVideoFps());
        // 4. create videoTrack 创建videoTrack
        VideoTrack videoTrack = mConnectionFactory.createVideoTrack(webRtcDevice.getVideoTrackId(), mVideoSource);
        videoTrack.setEnabled(true);
        videoTrack.addSink(localSink);
        // add video effects =============== 添加视频效果
        rtcVideoEffector = new RTCVideoEffector();
        gpuImageBeautyFilter = new GPUImageBeautyFilter();
        rtcVideoEffector.addGPUImageFilter(gpuImageBeautyFilter);
        mVideoEffectProcessor = new VideoEffectProcessor(mSurfaceTextureHelper, rtcVideoEffector);
        // 连接视频
        mVideoSource.setVideoProcessor(mVideoEffectProcessor);
        return videoTrack;
    }

    private VideoCapturer createVideoCapture(Context context) {
        VideoCapturer videoCapturer = createCameraCapture(new Camera1Enumerator(false));
        Log.d(TAG, "createVideoCapture: " + videoCapturer);
        // You can implement various captures here, such as screen recording and file recording
        // 您可以在这里实现各种捕获，例如屏幕录制和文件录制
        return videoCapturer;
    }

    private VideoCapturer createCameraCapture(CameraEnumerator enumerator) {
        final String[] deviceNames = enumerator.getDeviceNames();
        // First, try to find front facing camera
        // 首先，试着找到前置摄像头
        for (String deviceName : deviceNames) {
            if (enumerator.isFrontFacing(deviceName)) {
                VideoCapturer videoCapturer = enumerator.createCapturer(deviceName, null);
                if (videoCapturer != null) {
                    return videoCapturer;
                }
            }
        }
        // Front facing camera not found, try something else
        // 找不到前置摄像头，请尝试其他操作
        for (String deviceName : deviceNames) {
            if (!enumerator.isFrontFacing(deviceName)) {
                VideoCapturer videoCapturer = enumerator.createCapturer(deviceName, null);

                if (videoCapturer != null) {
                    return videoCapturer;
                }
            }
        }
        return null;
    }

    private AudioTrack createAudioTrack() {
        mAudioSource = mConnectionFactory.createAudioSource(RtcConfig.createAudioConstraints());
        mAudioTrack = mConnectionFactory.createAudioTrack(webRtcDevice.getAudioTrackId(), mAudioSource);
        mAudioTrack.setEnabled(true);
        return mAudioTrack;
    }


    public void createPeerConnection(RTCPeer.PeerConnectionEvents events, VideoSink remoteSink) {
        executor.execute(() -> {
            mPeer = new RTCPeer(mConnectionFactory, executor, events);
            List<String> mediaStreamLabels = Collections.singletonList("ARDAMS");
            if (mVideoTrack != null) {
                mPeer.addVideoTrack(mVideoTrack, mediaStreamLabels);
            }
            if (mAudioTrack != null) {
                mPeer.addAudioTrack(mAudioTrack, mediaStreamLabels);
            }
            mRemoteVideoTrack = getRemoteVideoTrack();
            if (mRemoteVideoTrack != null) {
                mRemoteVideoTrack.setEnabled(true);
                mRemoteVideoTrack.addSink(remoteSink);
            }
        });
    }

    private VideoTrack getRemoteVideoTrack() {
        for (RtpTransceiver transceiver : mPeer.getTransceivers()) {
            MediaStreamTrack track = transceiver.getReceiver().track();
            if (track instanceof VideoTrack) {
                return (VideoTrack) track;
            }
        }
        return null;
    }

    public void createOffer() {
        executor.execute(() -> {
            if (mPeer != null) {
                mPeer.createOffer();
            }
        });

    }

    public void createAnswer() {
        executor.execute(() -> {
            if (mPeer != null) {
                mPeer.createAnswer();
            }
        });

    }

    public void setRemoteDescription(SessionDescription sdp) {
        executor.execute(() -> {
            if (mPeer != null) {
                mPeer.setRemoteDescription(sdp);
            }
        });

    }

    public void addRemoteIceCandidate(IceCandidate candidate) {
        executor.execute(() -> {
            if (mPeer != null) {
                mPeer.addRemoteIceCandidate(candidate);
            }
        });

    }

    public void removeRemoteIceCandidates(IceCandidate[] candidates) {
        executor.execute(() -> {
            if (mPeer != null) {
                mPeer.removeRemoteIceCandidates(candidates);
            }
        });

    }

    public void close() {
        executor.execute(this::closeInternal);
    }

    private void closeInternal() {
        Log.d(TAG, "Closing peer connection.");
        if (mPeer != null) {
            mPeer.dispose();
            mPeer = null;
        }
        Log.d(TAG, "Closing audio source.");
        if (mAudioSource != null) {
            mAudioSource.dispose();
            mAudioSource = null;
        }
        Log.d(TAG, "Stopping capture.");
        if (mVideoCapturer != null) {
            try {
                mVideoCapturer.stopCapture();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mVideoCapturer.dispose();
            mVideoCapturer = null;
        }

        Log.d(TAG, "Closing video source.");
        if (mVideoSource != null) {
            mVideoSource.dispose();
            mVideoSource = null;
        }
        if (mVideoEffectProcessor != null) {
            mVideoEffectProcessor.dispose();
        }
        if (mSurfaceTextureHelper != null) {
            mSurfaceTextureHelper.dispose();
            mSurfaceTextureHelper = null;
        }

        Log.d(TAG, "Closing peer connection factory.");
        if (mConnectionFactory != null) {
            mConnectionFactory.dispose();
            mConnectionFactory = null;
        }
        mRootEglBase.release();

    }

    public void switchCamera() {
        executor.execute(this::switchCameraInternal);
    }

    private void switchCameraInternal() {
        Log.d(TAG, "Switch camera");
        CameraVideoCapturer cameraVideoCapturer = (CameraVideoCapturer) mVideoCapturer;
        cameraVideoCapturer.switchCamera(null);
    }

    public void toggleBeautyEffect() {
        executor.execute(() -> {
            if (rtcVideoEffector != null) {
                if (rtcVideoEffector.isEnabled()) {
                    rtcVideoEffector.disable();
                } else {
                    rtcVideoEffector.enable();
                }

            }
        });
    }

    public void setBitrateRange(int minBitrate, int maxBitrate) {
        executor.execute(() -> {
            if (mPeer != null) {
                if (minBitrate > maxBitrate) {
                    Log.w(TAG, "minBitrate must < maxBitrate.");
                    return;
                }
                RtpSender videoSender = mPeer.findVideoSender();
                if (videoSender == null) {
                    Log.w(TAG, "RtpSender are not ready.");
                    return;
                }
                RtpParameters parameters = videoSender.getParameters();
                if (parameters.encodings.size() == 0) {
                    Log.w(TAG, "RtpParameters are not ready.");
                    return;
                }
                for (RtpParameters.Encoding encoding : parameters.encodings) {
                    // Null value means no limit.
                    encoding.maxBitrateBps = maxBitrate == 0 ? null : maxBitrate * BPS_IN_KBPS;
                    encoding.minBitrateBps = Math.max(minBitrate, 300) * BPS_IN_KBPS;
                }
            }
        });


    }

    public void setVideoCodecType(@RTCPeer.VideoCodeType String videoCodecType) {
        executor.execute(() -> {
            if (mPeer != null) {
                mPeer.setVideoCodecType(videoCodecType);
            }
        });

    }

    private final Timer statsTimer = new Timer();

    public void enableStatsEvents(boolean enable, int periodMs) {
        if (enable) {
            try {
                statsTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        executor.execute(() -> getStats());
                    }
                }, 0, periodMs);
            } catch (Exception e) {
                Log.e(TAG, "Can not schedule statistics timer", e);
            }
        } else {
            statsTimer.cancel();
        }
    }

    private void getStats() {
        if (mPeer == null) {
            return;
        }
        mPeer.getStats();
    }


}
