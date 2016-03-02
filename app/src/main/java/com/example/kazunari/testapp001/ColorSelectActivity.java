package com.example.kazunari.testapp001;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ColorSelectActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_r;
    private EditText edit_g;
    private EditText edit_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_select);

        edit_r = (EditText)findViewById(R.id.color_select_r_edit);
        edit_g = (EditText)findViewById(R.id.color_select_g_edit);
        edit_b = (EditText)findViewById(R.id.color_select_b_edit);

        Button dicision = (Button)findViewById(R.id.color_select_dicision);
        dicision.setOnClickListener(this);

        Button back = (Button)findViewById(R.id.color_select_back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 押下時の処理
        switch (v.getId()) {
            // 決定
            case R.id.color_select_dicision:
                String tmp_str[] = new String[3];

                tmp_str[0] = edit_r.getText().toString();
                tmp_str[1] = edit_g.getText().toString();
                tmp_str[2] = edit_b.getText().toString();

                // nullチェック
                if( !tmp_str[0].equals("") && !tmp_str[1].equals("") && !tmp_str[2].equals("") ) {
                    int input[] = new int[3];

                    try {
                        // 数値に変換
                        input[0] = Integer.parseInt(tmp_str[0]);
                        input[1] = Integer.parseInt(tmp_str[1]);
                        input[2] = Integer.parseInt(tmp_str[2]);
                    } catch(NumberFormatException e) {
                        showDialog("数値を入力してください。");
                        return;
                    }

                    // 入力値チェック
                    if( (0 <= input[0]) && (input[0] <= 255) &&
                            (0 <= input[1]) && (input[1] <= 255) &&
                            (0 <= input[2]) && (input[2] <= 255) ) {
                        // インテントの生成
                        Intent intent = new Intent();
                        intent.setClassName("com.example.kazunari.testapp001", "com.example.kazunari.testapp001.ColorResultActivity");
                        // インテントに値をセット
                        intent.putExtra("rgb",input);
                        // Activity の起動
                        startActivity(intent);
                    } else {
                        showDialog("0～255の値を入力してください。");
                    }
                } else {
                    showDialog("R,G,B全てを入力してください。");
                }


                break;
            // Back
            case R.id.color_select_back:
                // インテントの生成
                Intent intent2 = new Intent();
                intent2.setClassName("com.example.kazunari.testapp001", "com.example.kazunari.testapp001.MainActivity");
                // Activity の起動
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    private void showDialog(String msg) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setMessage(msg);  //内容(メッセージ)設定

        // OK(肯定的な)ボタンの設定
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // OKボタン押下時の処理

            }
        });
        alertDialog.show();
    }
}
