package com.cyyaw.iot.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

}