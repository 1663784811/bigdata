package com.cyyaw.demoapplication.permission;


import android.Manifest;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.view.accessibility.AccessibilityManager;

import androidx.core.content.ContextCompat;

import com.cyyaw.demoapplication.service.FloatWindowService;

/**
 * 受权码
 */
public enum PermissionsCode {

    READ_CONTACTS(Manifest.permission.READ_CONTACTS, 111111, "读取联系人信息", null)
    ,SYSTEM_ALERT_WINDOW(Manifest.permission.SYSTEM_ALERT_WINDOW, 22222,"开启浮窗", Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
    ,BIND_ACCESSIBILITY_SERVICE(Manifest.permission.BIND_ACCESSIBILITY_SERVICE, 3333,"无障碍服务AccessibilityService", Settings.ACTION_ACCESSIBILITY_SETTINGS)
    ;

    /**
     * 系统权限
     */
    private String permissions;
    /**
     * 自定议受权码
     */
    private int code;

    /**
     * 说明
     */
    private String note;

    /**
     * 是否需要打开系统Activity 不需要为则NULL
     */
    private String sysActivity;

    /**
     * 受权成功回调
     */
    private PermissionsSuccessCallback successCallback;

    /**
     * 受权失败回调
     */
    private PermissionsErrorCallback errorCallback;

    /**
     * 其它
     */
//    private Object other;
    // ===========================================================================================

    PermissionsCode(String permissions, int code, String note, String sysActivity) {
        this.permissions = permissions;
        this.code = code;
        this.note = note;
        this.sysActivity = sysActivity;
    }


    // ===========================================================================================
    public static PermissionsCode getPermissionsCode(String permissions) {
        PermissionsCode[] values = PermissionsCode.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].getPermissions().equals(permissions)) {
                return values[i];
            }
        }
        return null;
    }
    // ===========================================================================================

    public String getSysActivity() {
        return sysActivity;
    }

    public String getPermissions() {
        return permissions;
    }

    public int getCode() {
        return code;
    }

    public String getNote() {
        return note;
    }

    public PermissionsSuccessCallback getSuccessCallback() {
        return successCallback;
    }

    public PermissionsErrorCallback getErrorCallback() {
        return errorCallback;
    }

    public void setSuccessCallback(PermissionsSuccessCallback successCallback) {
        this.successCallback = successCallback;
    }

    public void setErrorCallback(PermissionsErrorCallback errorCallback) {
        this.errorCallback = errorCallback;
    }

    /**
     * 受权成功
     */
    public static interface PermissionsSuccessCallback {
        void run();
    }

    /**
     * 受权失败
     */
    public static interface PermissionsErrorCallback {
        void run();
    }


    /**
     * 判断是否已经受权
     */
    public static boolean alreadyPermissions(Context context, PermissionsCode permissionsCode) {
        String permissions = permissionsCode.getPermissions();

        // 正常
        if (permissions.equals(Manifest.permission.SYSTEM_ALERT_WINDOW) && Settings.canDrawOverlays(context)) {
            // 浮窗
            return true;
        }if (permissions.equals(Manifest.permission.BIND_ACCESSIBILITY_SERVICE) && isAccessibilityServiceEnabled(context)) {
            // 无障碍服务AccessibilityService
            return true;
        } else {
            return ContextCompat.checkSelfPermission(context, permissions) == PackageManager.PERMISSION_GRANTED;
        }
    }


    private static boolean isAccessibilityServiceEnabled(Context context) {
        AccessibilityManager accessibilityManager = (AccessibilityManager)context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        if (accessibilityManager != null) {
            for (AccessibilityServiceInfo serviceInfo : accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK)) {
                String id = serviceInfo.getId();
                String packageName = context.getPackageName();
                if (id.indexOf(packageName) == 0) {
                    return true;
                }
            }
        }
        return false;
    }


}
