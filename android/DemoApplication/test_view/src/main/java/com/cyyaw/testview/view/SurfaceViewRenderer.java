package com.cyyaw.testview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class SurfaceViewRenderer extends SurfaceView implements SurfaceHolder.Callback{

    private static final String TAG = "SurfaceViewRenderer";

    public SurfaceViewRenderer(Context context) {
        super(context);
    }

    public SurfaceViewRenderer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SurfaceViewRenderer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SurfaceViewRenderer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

        Log.d(TAG, "surfaceCreated: ");

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

        Log.d(TAG, "surfaceChanged: ");
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

        Log.d(TAG, "surfaceDestroyed: ");
    }
}
