package com.example.hlapplication;

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

        int btnSettings = R.id.btn_settings;


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
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

    }
}