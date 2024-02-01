package com.example.socialize;

import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.socialize.databinding.ActivityMainBinding;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    private SurfaceView surfaceView;

    private WangyiPlayer wangyiPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView =  findViewById(R.id.surfaceView);


        wangyiPlayer = new WangyiPlayer();
        wangyiPlayer.setSurfaceView(surfaceView);


    }


    public void open(View view){
        File file = new File(Environment.getExternalStorageDirectory(), "");

        wangyiPlayer.start(file.getAbsolutePath());
    }


}






