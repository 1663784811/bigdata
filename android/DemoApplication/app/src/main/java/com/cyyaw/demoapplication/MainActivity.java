package com.cyyaw.demoapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.Manifest;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cyyaw.demoapplication.data.LogInfoAdapter;
import com.cyyaw.demoapplication.service.FloatMarkWindowService;
import com.cyyaw.demoapplication.service.FloatWindowInfoService;
import com.cyyaw.demoapplication.service.FloatWindowService;
import com.cyyaw.demoapplication.service.window.FloatWindow;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";


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

        findViewById(R.id.btn_read_file).setOnClickListener(this);
        findViewById(R.id.btn_write_file).setOnClickListener(this);
        findViewById(R.id.btn_openFloatWin).setOnClickListener(this);
    }


    // ==========================================================================================================================================================

    @Override
    protected void onStop() {
        super.onStop();
        //


    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_openFloatWin) {
            // ========================================================================================================================================================
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
                // 跳转受权页面
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                activityResult.launch(intent);
            } else {
                showFloatWin();
            }
        } else if (id == R.id.btn_read_file) {
            // ========================================================================================================================================================
            Log.i(TAG, "  =================   onClick: btn_read_file  ");

        } else if (id == R.id.btn_write_file) {
            // ========================================================================================================================================================
            Log.i(TAG, "  =================   onClick: btn_write_file  ");
            // 检查权限是否已经被授予
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    Toast.makeText(MainActivity.this, "有写文件权限" + Build.VERSION.SDK_INT, Toast.LENGTH_SHORT).show();
                    File mVideoFile = new File(getApplication().getFilesDir().getPath());
                    File[] files = mVideoFile.getParentFile().listFiles();
                    StringBuffer sb = new StringBuffer("大小:" + files.length + "\r\n");
                    for (int i = 0; i < files.length; i++) {
                        String name = files[i].getName();
                        sb.append(name + "\r\n");
                    }
                    TextView pageInfo = findViewById(R.id.pageInfo);
                    pageInfo.setText("路径:" + sb);
                } else {
                    // 如果权限未被授予，请求权限
                    Toast.makeText(MainActivity.this, "没有写文件权限" + Build.VERSION.SDK_INT, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                    intent.setData(Uri.parse("package:" + this.getPackageName()));
                    startActivity(intent);
                }
            } else {
                // TODO 小于 11 没有适配 权限不一样


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