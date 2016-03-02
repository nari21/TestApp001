package com.example.kazunari.testapp001;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class ColorResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_result);

        // インテントからデータ取得
        Intent intent = getIntent();
        int data[] = new int[3];
        data = intent.getIntArrayExtra("rgb");

        // 背景色の設定
        RelativeLayout baseLayout = (RelativeLayout)findViewById(R.id.color_result_layout);
        baseLayout.setBackgroundColor(Color.rgb(data[0], data[1], data[2]));

        Button back = (Button)findViewById(R.id.color_result_back);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // インテントの生成
                Intent intent = new Intent();
                intent.setClassName("com.example.kazunari.testapp001", "com.example.kazunari.testapp001.ColorSelectActivity");
                // Activity の起動
                startActivity(intent);
            }
        });
    }
}
