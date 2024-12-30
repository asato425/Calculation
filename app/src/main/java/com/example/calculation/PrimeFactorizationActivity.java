package com.example.calculation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PrimeFactorizationActivity extends AppCompatActivity implements View.OnClickListener {

    int TEXTMAXLENGTH = 16;
    Spinner spinner;

    StringBuilder textResult;
    StringBuilder textInput;

    TextView tvResult;
    TextView tvInput;

    Calculator calculator = new Calculator();

    @Override
    //この画面が遷移時に実行
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prime_factorization);

        findViewById(R.id.btn00).setOnClickListener(this);
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btnDo).setOnClickListener(this);
        findViewById(R.id.btnClear).setOnClickListener(this);
        findViewById(R.id.btnBack).setOnClickListener(this);

        spinner = findViewById(R.id.spinnerOptions);
        // Spinnerに表示する選択肢を設定
        String[] options = {"","因数分解", "データの分析", "四則演算"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        // 初期選択時の呼び出しを防ぐフラグ
        final boolean[] isFirstSelection = {true};

        // リスナーを設定
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 初回呼び出しをスキップ
                if (isFirstSelection[0]) {
                    isFirstSelection[0] = false;
                    return;
                }

                // 選択されたアイテムを取得
                String selectedItem = (String) parent.getItemAtPosition(position);

                // 条件によって画面遷移
                if (selectedItem.equals("因数分解")) {
                    Intent intent = new Intent(getApplicationContext(), FactorizationActivity.class);
                    startActivity(intent);
                }
                if (selectedItem.equals("データの分析")) {
                    Intent intent = new Intent(getApplicationContext(), DataActivity.class);
                    startActivity(intent);
                }
                if (selectedItem.equals("四則演算")) {
                    Intent intent = new Intent(getApplicationContext(), AdditionActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 必要なら処理を追加
            }
        });

        textResult = new StringBuilder();
        textInput = new StringBuilder();

        tvResult = findViewById(R.id.tvResult);
        tvInput = findViewById(R.id.tvInput);
    }

    @Override
    public void onClick(View view) {
        //タップしたButtonのidをgetButtonに入れる
        int getButton = view.getId();

        if(textInput.length() <= TEXTMAXLENGTH){
            //数字入力の時
            if(getButton == R.id.btn00){
                textInput = calculator.numberProcess(textInput,0);
                textInput = calculator.numberProcess(textInput,0);
            }
            if(getButton == R.id.btn0){
                textInput = calculator.numberProcess(textInput,0);
            }
            if(getButton == R.id.btn1){
                textInput = calculator.numberProcess(textInput,1);
            }
            if(getButton == R.id.btn2){
                textInput = calculator.numberProcess(textInput,2);
            }
            if(getButton == R.id.btn3){
                textInput = calculator.numberProcess(textInput,3);
            }
            if(getButton == R.id.btn4){
                textInput = calculator.numberProcess(textInput,4);
            }
            if(getButton == R.id.btn5){
                textInput = calculator.numberProcess(textInput,5);
            }
            if(getButton == R.id.btn6){
                textInput = calculator.numberProcess(textInput,6);
            }
            if(getButton == R.id.btn7){
                textInput = calculator.numberProcess(textInput,7);
            }
            if(getButton == R.id.btn8){
                textInput = calculator.numberProcess(textInput,8);
            }
            if(getButton == R.id.btn9){
                textInput = calculator.numberProcess(textInput,9);
            }
        }
        //テキストクリアする時
        if(getButton == R.id.btnClear){
            textInput.delete(0,textInput.length());
        }
        //テキストから一文字消す時
        if(getButton == R.id.btnBack){
            if(textInput.length() > 0) textInput.delete(textInput.length()-1,textInput.length());
        }
        if(getButton == R.id.btnDo){
            tvResult.setText("計算中...");
            textResult = calculator.doProcess(textInput);
        }

        tvResult.setText(textResult);
        tvInput.setText(textInput);
    }
}