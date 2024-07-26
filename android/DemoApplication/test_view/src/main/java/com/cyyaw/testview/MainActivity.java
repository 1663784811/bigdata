package com.cyyaw.testview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cyyaw.testview.printer.PrinterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button nowPrintBtn = findViewById(R.id.nowPrintBtn);


        nowPrintBtn.setOnClickListener((View v) -> {
            startActivity(new Intent(MainActivity.this, PrinterActivity.class));
        });


        findViewById(R.id.goPop).setOnClickListener((View v) -> {
            Intent intent = new Intent(MainActivity.this, PopupActivity.class);
            startActivity(intent);
        });

    }
}