package com.cyyaw.iot.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cyyaw.iot.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvTitle;

    private ListView lvIndexList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        simulateClick();
    }


    private void initView() {
        tvTitle = findViewById(R.id.tv_title);
        lvIndexList = findViewById(R.id.lv_index_list);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("---" + i);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, list.toArray());
        lvIndexList.setAdapter(arrayAdapter);
    }


    private void simulateClick() {
        // 创建一个新的线程执行模拟点击操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        // 创建 Instrumentation 实例
                        Instrumentation inst = new Instrumentation();

                        // 模拟触摸事件，ACTION_DOWN表示按下
                        MotionEvent downEvent = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 200, 200, 0);
                        inst.sendPointerSync(downEvent);

                        // 延迟一段时间，模拟用户操作的持续时间
                        SystemClock.sleep(100);

                        // 模拟触摸事件，ACTION_UP表示抬起
                        MotionEvent upEvent = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 200,200, 0);
                        inst.sendPointerSync(upEvent);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}