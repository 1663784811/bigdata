package com.cyyaw.cui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.cyyaw.cui.R;


public class CuiChatMsgFromFragment extends Fragment {

    private CuiChatMsgFromCallBack callBack;


    public CuiChatMsgFromFragment() {
    }

    public CuiChatMsgFromFragment(CuiChatMsgFromCallBack callBack) {
        this.callBack = callBack;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cui_chat_msg_from, container, false);

        return view;
    }


    /**
     * 方法回调
     */
    public interface CuiChatMsgFromCallBack {


    }


}