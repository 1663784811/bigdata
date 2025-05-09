package com.cyyaw.txapplication.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.cyyaw.ncalendar.enumeration.CheckModel;


public abstract class BaseActivity extends AppCompatActivity {

    protected final static String TAG = "NECER";
    protected String title;
    protected CheckModel checkModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkModel = (CheckModel) getIntent().getSerializableExtra("selectedModel");
        title = getIntent().getStringExtra("title");

        ActionBar supportActionBar = getSupportActionBar();

        if (supportActionBar != null) {
            supportActionBar.setTitle(title);
        }
    }


}

