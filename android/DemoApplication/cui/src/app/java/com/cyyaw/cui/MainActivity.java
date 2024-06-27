package com.cyyaw.cui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cyyaw.cui.test.ListViewActivity;
import com.cyyaw.cui.test.NestScrollActivity;
import com.cyyaw.cui.test.RecyleActivity;
import com.cyyaw.cui.test.ScrollActivity;
import com.cyyaw.cui.test.ViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_main);
    }


    public void funScroll(View view) {
        startActivity(new Intent(this, ScrollActivity.class));
    }

    public void funNestScroll(View view) {
        startActivity(new Intent(this, NestScrollActivity.class));
    }

    public void funRecyleview(View view) {
        startActivity(new Intent(this, RecyleActivity.class));
    }

    public void funListView(View view) {
        startActivity(new Intent(this, ListViewActivity.class));
    }

    public void funView(View view) {
        startActivity(new Intent(this, ViewActivity.class));
    }


}

