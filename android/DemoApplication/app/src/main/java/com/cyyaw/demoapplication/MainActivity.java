package com.cyyaw.demoapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.Manifest;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cyyaw.demoapplication.data.LogInfoAdapter;
import com.cyyaw.demoapplication.service.FloatMarkWindowService;
import com.cyyaw.demoapplication.service.FloatWindowInfoService;
import com.cyyaw.demoapplication.service.FloatWindowService;
import com.cyyaw.demoapplication.service.window.FloatWindow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {





    private ActivityResultLauncher<String> getPermission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean isGranted) {
            if (Settings.canDrawOverlays(MainActivity.this)) {
                Toast.makeText(MainActivity.this, "权限申请-成功", Toast.LENGTH_SHORT).show();
//                showFloatWin();
            } else {
                Toast.makeText(MainActivity.this, "权限申请-失败", Toast.LENGTH_SHORT).show();
            }
        }
    });

    private ActivityResultLauncher<Intent> activityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (ActivityResult result) -> {
        // 申请权限
        int resultCode = result.getResultCode();
        getPermission.launch(Manifest.permission.SYSTEM_ALERT_WINDOW);
    });


    // ========================================================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_openFloatWin).setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //


    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_openFloatWin) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
                // 跳转受权页面
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                activityResult.launch(intent);
            } else {
                showFloatWin();
            }
        }
    }

    private void showFloatWin() {
        Intent intent = new Intent(MainActivity.this, FloatWindowService.class);
        ComponentName floatWindows = startService(intent);



        Intent in = new Intent(MainActivity.this, FloatWindowInfoService.class);
        ComponentName componentName = startService(in);


        // 创建操作跟踪红点
        Intent inx = new Intent(MainActivity.this, FloatMarkWindowService.class);
        ComponentName componentNamex = startService(inx);



    }

}