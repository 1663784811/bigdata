package com.example.hlapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hlapplication.service.AccessibilityServiceMonitor;
import com.example.hlapplication.util.AccessibilitUtil;
import com.example.hlapplication.util.Config;
import com.example.hlapplication.util.ShareUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, TimePicker.OnTimeChangedListener {

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
        }
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