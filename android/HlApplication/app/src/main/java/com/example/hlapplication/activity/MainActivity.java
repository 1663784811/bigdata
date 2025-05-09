package com.example.hlapplication.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hlapplication.R;
import com.example.hlapplication.service.AccessibilityServiceMonitor;
import com.example.hlapplication.service.FloatWindowService;
import com.example.hlapplication.util.AccessibilitUtil;
import com.example.hlapplication.util.Config;
import com.example.hlapplication.util.ShareUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, TimePicker.OnTimeChangedListener {

    private ActivityResultLauncher<Intent> launcher;
    private ShareUtil mShareUtil;

    private TimePicker timepick;

    private Switch sw_keep;
    private Switch sw_liangtong;
    private Switch sw_alipay_forest;
    private Switch sw_wechart_motion;
    private Button btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initVaule();
        initListener();
        startService();
    }

    private void initView() {
        timepick = (TimePicker) findViewById(R.id.timepick);
        sw_keep = (Switch) findViewById(R.id.sw_keep);
        sw_liangtong = (Switch) findViewById(R.id.sw_liangtong);
        btnSettings = (Button) findViewById(R.id.btn_settings);
        sw_alipay_forest = (Switch) findViewById(R.id.sw_alipay_forest);
        sw_wechart_motion = (Switch) findViewById(R.id.sw_wechart_motion);
    }

    private void initVaule() {
        mShareUtil = new ShareUtil(this);
        timepick.setIs24HourView(true);
        timepick.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);
    }

    private void initListener() {
        btnSettings.setOnClickListener(this);
        sw_keep.setOnCheckedChangeListener(this);
        sw_liangtong.setOnCheckedChangeListener(this);
        sw_alipay_forest.setOnCheckedChangeListener(this);
        sw_wechart_motion.setOnCheckedChangeListener(this);
        timepick.setOnTimeChangedListener(this);
    }

    /**
     * 启动服务
     */
    private void startService() {
        Intent mIntent = new Intent(this, AccessibilityServiceMonitor.class);
        startService(mIntent);
    }

    private void updateUI() {
        if (AccessibilitUtil.isAccessibilitySettingsOn(this, AccessibilityServiceMonitor.class.getCanonicalName())) {
            btnSettings.setEnabled(false);
        } else {
            btnSettings.setEnabled(true);
        }
        sw_keep.setChecked(mShareUtil.getBoolean(Config.APP_KEEP, true));
        sw_alipay_forest.setChecked(mShareUtil.getBoolean(Config.APP_ALIPAY_FOREST, true));
        sw_liangtong.setChecked(mShareUtil.getBoolean(Config.APP_LIANG_TONG, true));
        sw_wechart_motion.setChecked(mShareUtil.getBoolean(Config.APP_WECHART_MOTHION, true));
        int hour = mShareUtil.getInt(Config.KEY_HOUR, -1);
        int minute = mShareUtil.getInt(Config.KEY_MINUTE, -1);
        if (hour == -1 && minute == -1) {
            // do nothing
        } else {
            timepick.setHour(hour);
            timepick.setMinute(minute);
        }
    }


    public void getApplicationList(View view) {
        Log.i("=======================================:::", "显示浮窗");
        Log.i("=======================================:::", "onCreate: 执行onCreate方法");
        // 获取应用程序包管理器
        PackageManager packageManager = getPackageManager();
        // 获取所有已安装应用的 PackageInfo 对象
        List<PackageInfo> packages = packageManager.getInstalledPackages(0);
        // 创建一个存储应用程序名称的列表
        List<String> appNames = new ArrayList<>();
        // 遍历已安装的应用程序列表
        for (PackageInfo packageInfo : packages) {
            // 获取应用程序的 ApplicationInfo 对象
            ApplicationInfo appInfo = packageInfo.applicationInfo;
            // 获取应用程序的名称
            String appName = appInfo.loadLabel(packageManager).toString();
            String packageName = appInfo.packageName;
            for (ActivityInfo activity : packageInfo.activities) {
                ApplicationInfo applicationInfo = activity.applicationInfo;

            }

            // 添加应用程序名称到列表
            appNames.add(appName);
            Log.i("=======================================:::", "应用名称：" + appName + "   包名：" + packageName.toString());
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_settings) {
            AccessibilitUtil.showSettingsUI(this);
        } else if (id == R.id.btn_floatWindow) {
            // 1.判断权限
            // 2.启动浮窗
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
//                // 如果没有权限，执行相应的操作，如请求权限
//                launcher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//
//                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//                        startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
//
//                        if (result.getResultCode() == RESULT_OK) {
//                            // 处理从启动的 Activity 返回的结果
//                            Intent data = result.getData();
//                            if (data != null) {
//                                // 在这里处理结果
//                                String resultString = data.getStringExtra("result_key");
//                                if (resultString != null) {
//                                    // 处理结果字符串
//                                }
//                            }
//                        }
//                    }
//                });
//            } else {
//                // 如果有权限，可以创建悬浮窗
//                Intent intent = new Intent(MainActivity.this, FloatWindowService.class);
//                startService(intent);
//            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
//            if (Build.VERSION.SDK_INT >= 23) {
//                if (!Settings.canDrawOverlays(this)) {
//                    Toast.makeText(this, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "权限授予成功！", Toast.LENGTH_SHORT).show();
//                    //有悬浮窗权限开启服务绑定 绑定权限
//                    Intent intent = new Intent(MainActivity.this, FloatWindowService.class);
//                    startService(intent);
//                }
//            }
//        }
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int id = compoundButton.getId();
        if (id == R.id.sw_keep) {
            mShareUtil.setShare(Config.APP_KEEP, b);
            Log.d(Config.TAG, "Keep is " + b);
        } else if (id == R.id.sw_alipay_forest) {
            mShareUtil.setShare(Config.APP_ALIPAY_FOREST, b);
            Log.d(Config.TAG, "AlipayForest is " + b);
        } else if (id == R.id.sw_liangtong) {
            mShareUtil.setShare(Config.APP_LIANG_TONG, b);
            Log.d(Config.TAG, "LiangTong is " + b);
        } else if (id == R.id.sw_wechart_motion) {
            mShareUtil.setShare(Config.APP_WECHART_MOTHION, b);
            Log.d(Config.TAG, "Wechat mothion is " + b);
        }
        Intent intent = new Intent(this, AccessibilityServiceMonitor.class);
        intent.setAction(AccessibilityServiceMonitor.ACTION_UPDATE_SWITCH);
        MainActivity.this.startService(intent);
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        if (mShareUtil != null) {
            mShareUtil.setShare(Config.KEY_HOUR, hourOfDay);
            mShareUtil.setShare(Config.KEY_MINUTE, minute);
            MyApplication.startAlarmTask(MainActivity.this);
        }
    }
}