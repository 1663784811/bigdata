package com.cyyaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Test2Activity extends AppCompatActivity {
    private static final String TAG = "Test2Activity ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        Intent intent = getIntent();
        Bundle date = intent.getExtras();
        Log.i(TAG, "数据:: " + date.getString(MainTestActivity.testKey));
        Bundle extras = intent.getExtras();
        Log.i(TAG, "数据2:: " + extras.getString("name"));
        View test_02 = findViewById(R.id.test_02);

        test_02.setOnClickListener((View v) -> {
            Intent i = new Intent();
            i.putExtra(MainTestActivity.testKey, "cccc");
            setResult(RESULT_OK, i);
            finish();
        });

    }
}
