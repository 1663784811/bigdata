package com.cyyaw.demoapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.cyyaw.demoapplication.permission.PermissionsCode;


/**
 * 处理权限受权问题
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {


    /**
     * 异步受权
     */
    protected ActivityResultLauncher<String> getPermission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), (Boolean isGranted) -> {
        if (isGranted) {
            Toast.makeText(getActivity(), "权限申请-成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "权限申请-失败", Toast.LENGTH_SHORT).show();
        }
    });

    /**
     * 需要跳转其它Active才能受权的
     */
    protected ActivityResultLauncher<Intent> activityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (ActivityResult result) -> {
        // 申请权限
        int resultCode = result.getResultCode();
        getPermission.launch(Manifest.permission.SYSTEM_ALERT_WINDOW);
    });


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsCode permissionsCode = PermissionsCode.getPermissionsCode(permissions[0]);
        if (null != permissionsCode) {
            if (grantResults[0] != -1) {
                Toast.makeText(getActivity(), "受权成功::" + grantResults, Toast.LENGTH_SHORT).show();
                PermissionsCode.PermissionsSuccessCallback successCallback = permissionsCode.getSuccessCallback();
                if (null != successCallback) {
                    successCallback.run();
                }
            } else {
                Toast.makeText(getActivity(), "受权失败::" + grantResults, Toast.LENGTH_SHORT).show();
                PermissionsCode.PermissionsErrorCallback errorCallback = permissionsCode.getErrorCallback();
                if (null != errorCallback) {
                    errorCallback.run();
                }
            }
        }
    }


    /**
     * 受权处理
     *
     * @param permissions     成功回调
     * @param successCallback 失败回调
     */
    protected void requestPermissionsFn(String permissions, PermissionsCode.PermissionsSuccessCallback successCallback) {
        requestPermissionsFn(permissions, successCallback, () -> {
        });
    }

    protected void requestPermissionsFn(String permissions, PermissionsCode.PermissionsSuccessCallback successCallback, PermissionsCode.PermissionsErrorCallback errorCallback) {
        PermissionsCode permissionsCode = PermissionsCode.getPermissionsCode(permissions);
        if (null != permissionsCode) {
            permissionsCode.setSuccessCallback(successCallback);
            permissionsCode.setErrorCallback(errorCallback);
            ActivityCompat.requestPermissions(getActivity(), new String[]{permissionsCode.getPermissions()}, permissionsCode.getCode());
        } else {
            Toast.makeText(getActivity(), "错误:请求未定义权限", Toast.LENGTH_SHORT).show();
        }
    }

    abstract Activity getActivity();


}


