//package com.example.hlapplication;
//
//import android.accessibilityservice.AccessibilityService;
//import android.util.Log;
//import android.view.accessibility.AccessibilityEvent;
//import android.view.accessibility.AccessibilityNodeInfo;
//
//import java.util.List;
//
//public class MyAccessibilityService extends AccessibilityService {
//
//
//    @Override
//    public void onAccessibilityEvent(AccessibilityEvent event) {
//        // 在这里处理 AccessibilityEvent，获取界面元素信息
//
//        if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED) {
//            AccessibilityNodeInfo source = event.getSource();
//            if (source != null) {
//                List<AccessibilityNodeInfo> buttonNodes = source.findAccessibilityNodeInfosByViewId("com.example.app:id/button_id");
//                for (AccessibilityNodeInfo buttonNode : buttonNodes) {
//                    // 获取按钮的文本和其他信息
//                    String buttonText = buttonNode.getText().toString();
//                    // 打印按钮信息
//                    Log.d("============================", "按钮文本：" + buttonText);
//                }
//            }
//        }
//    }
//
//
//
//    @Override
//    public void onInterrupt() {
//        // 当服务被中断时的处理
//    }
//
//
//}
