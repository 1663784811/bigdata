package com.cyyaw.testwebrtc.temp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cyyaw.testwebrtc.R;

public class Temple2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temple2);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void OnCreateRoom(View view) {
        startActivity(new Intent(this, ConnectMultiActivity.class));
    }
}