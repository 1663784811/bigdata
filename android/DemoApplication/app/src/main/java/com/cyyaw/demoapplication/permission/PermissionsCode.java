package com.cyyaw.demoapplication.permission;


import android.Manifest;
import android.provider.Settings;

import com.cyyaw.demoapplication.BaseAppCompatActivity;

/**
 * 受权码
 */
public enum PermissionsCode {

    READ_CONTACTS(Manifest.permission.READ_CONTACTS, 111111, "读取联系人信息", null)
    ,SYSTEM_ALERT_WINDOW(Manifest.permission.SYSTEM_ALERT_WINDOW, 22222,"开启浮窗", Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
    ,BIND_ACCESSIBILITY_SERVICE(Manifest.permission.SYSTEM_ALERT_WINDOW, 3333,"无障碍服务AccessibilityService", Settings.ACTION_ACCESSIBILITY_SETTINGS)
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

}
