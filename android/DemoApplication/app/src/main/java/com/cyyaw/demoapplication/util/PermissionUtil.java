package com.cyyaw.demoapplication.util;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.cyyaw.demoapplication.MainActivity;

public class PermissionUtil {

    private PermissionUtil() {
    }

    public static void applyAuthority(AppCompatActivity activity, String permission, SuccessCallBake success) {
        applyAuthority(activity, permission, success, () -> {
        });
    }

    public static void applyAuthority(AppCompatActivity activity, String permission, SuccessCallBake success, ErrorCallBake error) {

        switch (permission) {
            // 浮窗
            case Manifest.permission.SYSTEM_ALERT_WINDOW:
                if (!Settings.canDrawOverlays(activity)) {
                    // ==============================
                    ActivityResultLauncher<String> getPermission = activity.registerForActivityResult(new ActivityResultContracts.RequestPermission(), (Boolean isGranted) -> {
                        if (Settings.canDrawOverlays(activity)) {
                            Toast.makeText(activity, "权限申请-成功", Toast.LENGTH_SHORT).show();
                            success.run();
                        } else {
                            Toast.makeText(activity, "权限申请-失败", Toast.LENGTH_SHORT).show();
                            error.run();
                        }
                    });
                    ActivityResultLauncher<Intent> activityResult = activity.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (ActivityResult result) -> {
                        getPermission.launch(permission);    // 申请权限
                    });
                    // 跳转受权页面
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                    activityResult.launch(intent);
                } else {
                    success.run();
                }
                break;

            case "":

                break;
            default:
                error.run();
        }
    }


    public interface SuccessCallBake {
        void run();
    }

    public interface ErrorCallBake {
        void run();
    }
}
