package com.cyyaw.testbroadcasts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String broadcastKey = "com.cyyaw.testbroadcasts.MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendBroadcastButton = findViewById(R.id.sendBroadcastButton);
        sendBroadcastButton.setOnClickListener((View v) -> {
            Intent intent = new Intent();
            intent.setAction(broadcastKey);
            intent.putExtra("data", "Nothing to see here, move along.");
            sendBroadcast(intent);
        });


        startService(new Intent(this, MyService.class));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
