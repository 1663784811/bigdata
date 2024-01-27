package com.cyyaw.iot.activity.fragment;

import android.graphics.Bitmap;
import android.os.Environment;
import android.view.SurfaceView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cyyaw.iot.R;
import com.cyyaw.iot.activity.WangyiPlayer;

import java.io.File;


/**
 * 首页
 */
public class HomeFragment extends BaseFragment {

    WangyiPlayer wangyiPlayer;

    SurfaceView surfaceView;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        WebView webView = mRootView.findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setBuiltInZoomControls(true);
        webView.loadUrl("http://www.baidu.com");
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Aut (view, url);
            }

        });

        surfaceView = webView.findViewById(R.id.surfaceView);
        wangyiPlayer = new WangyiPlayer();
        wangyiPlayer.setSurfaceView(surfaceView);

    }

    @Override
    protected void initData() {

    }

}