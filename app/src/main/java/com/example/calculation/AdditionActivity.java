package com.example.calculation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AdditionActivity extends AppCompatActivity implements View.OnClickListener {

    int TEXTMAXLENGTH = 10;
    Spinner spinner;

    StringBuilder textResult;
    StringBuilder textExp;

    TextView tvResult;
    TextView tvExp;

    Calculator calculator = new Calculator();
    @Override
    //この画面が遷移時に実行
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addition);

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
        findViewById(R.id.btnPlus).setOnClickListener(this);
        findViewById(R.id.btnMinus).setOnClickListener(this);
        findViewById(R.id.btnMul).setOnClickListener(this);
        findViewById(R.id.btnDiv).setOnClickListener(this);
        findViewById(R.id.btnEqual).setOnClickListener(this);
        findViewById(R.id.btnClear).setOnClickListener(this);
        findViewById(R.id.btnPlusMinus).setOnClickListener(this);
        findViewById(R.id.btnPoint).setOnClickListener(this);
        findViewById(R.id.btnPer).setOnClickListener(this);


        spinner = findViewById(R.id.spinnerOptions);
        // Spinnerに表示する選択肢を設定
        String[] options = {"因数分解", "データの分析", "素因数分解"};
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

                //⚠️最初の因数分解だけ押しても反応しない
                // 条件によって画面遷移
                if (selectedItem.equals("因数分解")) {
                    Intent intent = new Intent(getApplicationContext(), FactorizationActivity.class);
                    startActivity(intent);
                }
                if (selectedItem.equals("データの分析")) {
                    Intent intent = new Intent(getApplicationContext(), DataActivity.class);
                    startActivity(intent);
                }
                if (selectedItem.equals("素因数分解")) {
                    Intent intent = new Intent(getApplicationContext(), PrimeFactorizationActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 必要なら処理を追加
            }
        });

        textResult = new StringBuilder("0");
        textExp = new StringBuilder("0");

        tvResult = findViewById(R.id.tvResult);
        tvExp = findViewById(R.id.tvExpression);
    }

    @Override
    public void onClick(View view) {
        //タップしたButtonのidをgetButtonに入れる
        int getButton = view.getId();

        if(textResult.length() <= TEXTMAXLENGTH){
            if(getButton == R.id.btn0){
                textResult = calculator.numberProcess(textResult,0);
            }
            if(getButton == R.id.btn1){
                textResult = calculator.numberProcess(textResult,1);
            }
            if(getButton == R.id.btn2){
                textResult = calculator.numberProcess(textResult,2);
            }
            if(getButton == R.id.btn3){
                textResult = calculator.numberProcess(textResult,3);
            }
            if(getButton == R.id.btn4){
                textResult = calculator.numberProcess(textResult,4);
            }
            if(getButton == R.id.btn5){
                textResult = calculator.numberProcess(textResult,5);
            }
            if(getButton == R.id.btn6){
                textResult = calculator.numberProcess(textResult,6);
            }
            if(getButton == R.id.btn7){
                textResult = calculator.numberProcess(textResult,7);
            }
            if(getButton == R.id.btn8){
                textResult = calculator.numberProcess(textResult,8);
            }
            if(getButton == R.id.btn9){
                textResult = calculator.numberProcess(textResult,9);
            }
            if(getButton == R.id.btnPlusMinus){
                //テキスト内容の最後が数字の際に符号反転させるメソッド、入力,出力がStringBuilderのメソッド
            }
            if(getButton == R.id.btnPer){
                textResult = calculator.operatorProcess(textResult,'%');
            }
            if(getButton == R.id.btnPlus){
                textResult = calculator.operatorProcess(textResult,'+');
            }
            if(getButton == R.id.btnMinus){
                textResult = calculator.operatorProcess(textResult,'-');
            }
            if(getButton == R.id.btnMul){
                textResult = calculator.operatorProcess(textResult,'×');
            }
            if(getButton == R.id.btnDiv){
                textResult = calculator.operatorProcess(textResult,'÷');
            }
            //数字の後の時はそのまま、演算子の時は0.を追加
            if(getButton == R.id.btnPoint){
                textResult.append(".");
            }
        }
        if(getButton == R.id.btnClear){
            textResult.delete(0,textResult.length());
            textResult.append("0");
            textExp.delete(0,textExp.length());
        }
        //テキスト内容を受け取り、演算。元の内容をtextExpに入れて、結果をtextResultに入れる
        if(getButton == R.id.btnEqual){

        }

        tvResult.setText(textResult);
        tvExp.setText(textExp);
    }
}