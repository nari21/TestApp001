package com.example.kazunari.testapp001;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener,TextWatcher {
    private TextView mResult;
    Calculator mCalculator = new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        mResult = (TextView)findViewById(R.id.calculator_result);
        mResult.setText("0");

        Button back = (Button)findViewById(R.id.calculator_back);
        back.setOnClickListener(this);
        Button ac = (Button)findViewById(R.id.calculator_ac);
        ac.setOnClickListener(this);
        Button plus_minus = (Button)findViewById(R.id.calculator_plus_minus);
        plus_minus.setOnClickListener(this);
        Button percent = (Button)findViewById(R.id.calculator_percent);
        percent.setOnClickListener(this);
        Button division = (Button)findViewById(R.id.calculator_division);
        division.setOnClickListener(this);
        Button multi = (Button)findViewById(R.id.calculator_multi);
        multi.setOnClickListener(this);
        Button minus = (Button)findViewById(R.id.calculator_minus);
        minus.setOnClickListener(this);
        Button plus = (Button)findViewById(R.id.calculator_plus);
        plus.setOnClickListener(this);
        Button equal = (Button)findViewById(R.id.calculator_equal);
        equal.setOnClickListener(this);
        Button dot = (Button)findViewById(R.id.calculator_dot);
        dot.setOnClickListener(this);
        Button num0 = (Button)findViewById(R.id.calculator_0);
        num0.setOnClickListener(this);
        Button num1 = (Button)findViewById(R.id.calculator_1);
        num1.setOnClickListener(this);
        Button num2 = (Button)findViewById(R.id.calculator_2);
        num2.setOnClickListener(this);
        Button num3 = (Button)findViewById(R.id.calculator_3);
        num3.setOnClickListener(this);
        Button num4 = (Button)findViewById(R.id.calculator_4);
        num4.setOnClickListener(this);
        Button num5 = (Button)findViewById(R.id.calculator_5);
        num5.setOnClickListener(this);
        Button num6 = (Button)findViewById(R.id.calculator_6);
        num6.setOnClickListener(this);
        Button num7 = (Button)findViewById(R.id.calculator_7);
        num7.setOnClickListener(this);
        Button num8 = (Button)findViewById(R.id.calculator_8);
        num8.setOnClickListener(this);
        Button num9 = (Button)findViewById(R.id.calculator_9);
        num9.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        // 押下時の処理
        switch (btn.getId()) {
            case R.id.calculator_back:
                // インテントの生成
                Intent intent = new Intent();
                intent.setClassName("com.example.kazunari.testapp001", "com.example.kazunari.testapp001.MainActivity");
                // Activity の起動
                startActivity(intent);
                break;
            case R.id.calculator_ac:
                mCalculator.reset();
                mResult.setText("0");
                break;
            default:
                String input = btn.getText().toString();
                Log.d("CalculatorActivity", "input=" + input);

                String dispText = mCalculator.putInput(input);
                if(dispText != null) {
                    mResult.setText(dispText);
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String input = s.toString();
        Log.d("MainActivity", "input=" + input);
        if (input.length() > 0) {
            String dispText = mCalculator.putInput(input);
            if (dispText != null) {
                mResult.setText(dispText);
            }
            s.clear();
        }
    }

}
