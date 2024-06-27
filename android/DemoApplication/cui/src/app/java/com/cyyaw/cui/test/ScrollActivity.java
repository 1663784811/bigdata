package com.cyyaw.cui.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;

import com.cyyaw.cui.R;


public class ScrollActivity extends Activity {
    private ScrollView scroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        scroll = findViewById(R.id.scroll);
    }
}
