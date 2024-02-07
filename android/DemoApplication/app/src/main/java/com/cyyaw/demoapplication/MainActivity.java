package com.cyyaw.demoapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
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

import com.cyyaw.demoapplication.service.FloatMarkWindowService;
import com.cyyaw.demoapplication.service.FloatWindowLogService;
import com.cyyaw.demoapplication.service.FloatWindowService;
import com.cyyaw.demoapplication.service.FloatWindowTaskService;

import java.io.File;

public class MainActivity extends BaseAppCompatActivity implements View.OnClickListener {


    public static final String TAG = "MainActivity";

    // 浮窗
    private ComponentName floatWindows;

    // 日志信息
    private ComponentName windowLog;


    //
    private ComponentName componentNamex;

    // ========================================================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_read_file).setOnClickListener(this);
        findViewById(R.id.btn_write_file).setOnClickListener(this);
        findViewById(R.id.btn_openFloatWin).setOnClickListener(this);
        findViewById(R.id.btn_openAccessibilityService).setOnClickListener(this);

    }


    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_openAccessibilityService) {
            requestPermissionsFn(Manifest.permission.BIND_ACCESSIBILITY_SERVICE, ()->{
                Toast.makeText(MainActivity.this, "成功" , Toast.LENGTH_SHORT).show();
            });

        } else if (id == R.id.btn_openFloatWin) {
            requestPermissionsFn(Manifest.permission.SYSTEM_ALERT_WINDOW, this::showFloatWin);

        } else if (id == R.id.btn_read_file) {
            Log.i(TAG, "  =================   onClick: btn_read_file  ");

        } else if (id == R.id.btn_write_file) {
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


    private synchronized void showFloatWin() {
        Intent intent = new Intent(MainActivity.this, FloatWindowService.class);
        floatWindows = startService(intent);
        Intent in = new Intent(MainActivity.this, FloatWindowLogService.class);
        windowLog = startService(in);
        Intent ins = new Intent(MainActivity.this, FloatWindowTaskService.class);
        startService(ins);
        // 创建操作跟踪红点
        Intent inx = new Intent(MainActivity.this, FloatMarkWindowService.class);
        componentNamex = startService(inx);
    }


    @Override
    Activity getActivity() {
        return this;
    }
}