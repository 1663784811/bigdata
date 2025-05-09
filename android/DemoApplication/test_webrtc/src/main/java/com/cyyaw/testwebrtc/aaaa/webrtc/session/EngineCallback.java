package com.cyyaw.testwebrtc.aaaa.webrtc.session;



import com.cyyaw.testwebrtc.aaaa.webrtc.EnumType;

import org.webrtc.IceCandidate;
import org.webrtc.SessionDescription;

/**
 * 框架回调
 */
public interface EngineCallback {


    /**
     * 加入房间成功
     */
    void joinRoomSucc();

    /**
     * 退出房间成功
     */
    void exitRoom();

    /**
     * 拒绝连接
     */
    void reject(int type);

    void disconnected(EnumType.CallEndReason reason);

    void onSendIceCandidate(String userId, IceCandidate candidate);

    void onSendOffer(String userId, SessionDescription description);

    void onSendAnswer(String userId, SessionDescription description);

    void onRemoteStream(String userId);

    void onDisconnected(String userId);

}
