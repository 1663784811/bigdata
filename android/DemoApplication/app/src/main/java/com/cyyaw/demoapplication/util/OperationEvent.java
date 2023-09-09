package com.cyyaw.demoapplication.util;


import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;

/**
 * 操作事件
 */
public class OperationEvent {


    /**
     * 点击Home键
     */
    public static void clickHomeKey() {

    }


    /**
     * 模拟点击
     *
     * @param X
     * @param Y
     */
    public static boolean onClick(AccessibilityService service, int X, int Y) {
        Path path = new Path();
        path.moveTo(X, Y);
        GestureDescription.Builder builder = new GestureDescription.Builder().addStroke(new GestureDescription.StrokeDescription(path, 0, 20));
        return service.dispatchGesture(builder.build(), null, null);
    }


}
