package com.cyyaw.testsqlite;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONArray;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ChatInfoDatabaseHelper.init(MainActivity.this);


        JSONArray objects = ChatInfoDatabaseHelper.queryData("", new String[]{"", ""});



    }
}