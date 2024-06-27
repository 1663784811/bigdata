package com.cyyaw.testview.printer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cyyaw.testview.R;

public class PrinterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printer);




        PrintBitMapImageView paper = findViewById(R.id.printPager);
        Button nowPrintBtn = findViewById(R.id.nowPrintBtn);


        paper.setWordData("StaticLayout 类并未被废弃（deprecated），但在 Android P（API 28）及更高版本中，它引入了一个新的构建器 StaticLayout.Builder，可以更灵活地配置 StaticLayout。以下是如何使用 StaticLayout.Builder 来绘制文本并计算其高度。");
        paper.setWordData("Sbbbbbsssssssssssssssssssssssss");


        nowPrintBtn.setOnClickListener((View v) -> {



            paper.getPrintImageData();




        });


    }
}