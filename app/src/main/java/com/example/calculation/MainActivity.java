package com.example.calculation;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    final int INDEFINED = 0;
    final int ADD  = 1;
    final int SUB = 2;
    final int MUL   = 3;
    final int DIV   = 4;
    final int PER   = 5;

    private StringBuilder text = new StringBuilder("");
    private long textNum = 0;
    private long leftNum = 0;
    private long rightNum = 0;
    private int operator = INDEFINED;

    private TextView textView;

    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnAdd;
    private Button btnSub;
    private Button btnMul;
    private Button btnDiv;
    private Button btnEq;
    private Button btnC;
    private Button btnRoot;
    private Button btnPer;
    private Button btnPF;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        btnEq = findViewById(R.id.btnEq);
        btnC = findViewById(R.id.btnC);
        btnRoot = findViewById(R.id.btnRoot);
        btnPer = findViewById(R.id.btnPer);
        btnPF = findViewById(R.id.btnPF);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnEq.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnRoot.setOnClickListener(this);
        btnPer.setOnClickListener(this);
        btnPF.setOnClickListener(this);

        textView = findViewById(R.id.textView);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View view) {

        int getButton = view.getId();
        if(text.length() < 10) {
            if (getButton == R.id.btn0) {
                textNum = textNum*10;
            }
            if (getButton == R.id.btn1) {
                textNum = textNum*10 + 1;
            }
            if (getButton == R.id.btn2) {
                textNum = textNum*10 + 2;
            }
            if (getButton == R.id.btn3) {
                textNum = textNum*10 + 3;
            }
            if (getButton == R.id.btn4) {
                textNum = textNum*10 + 4;
            }
            if (getButton == R.id.btn5) {
                textNum = textNum*10 + 5;
            }
            if (getButton == R.id.btn6) {
                textNum = textNum*10 + 6;
            }
            if (getButton == R.id.btn7) {
                textNum = textNum*10 + 7;
            }
            if (getButton == R.id.btn8) {
                textNum = textNum*10 + 8;
            }
            if (getButton == R.id.btn9) {
                textNum = textNum*10 + 9;
            }
        }
        if(getButton == R.id.btnAdd) {
            if(operator == ADD){
                btnAdd.setBackgroundColor(Color.rgb(68,68,68));
                operator = INDEFINED;
                textNum = leftNum;
                leftNum = 0;
            }else if(operator == INDEFINED){
                btnAdd.setBackgroundColor(Color.rgb(200,200,200));
                operator = ADD;
                leftNum = textNum;
                textNum = 0;
            }
        }
        if(getButton == R.id.btnSub) {
            if(operator == SUB){
                btnSub.setBackgroundColor(Color.rgb(68,68,68));
                operator = INDEFINED;
                textNum = leftNum;
                leftNum = 0;
            }else if(operator == INDEFINED){
                btnSub.setBackgroundColor(Color.rgb(200,200,200));
                operator = SUB;
                leftNum = textNum;
                textNum = 0;
            }
        }
        if(getButton == R.id.btnMul) {
            if(operator == MUL){
                btnMul.setBackgroundColor(Color.rgb(68,68,68));
                operator = INDEFINED;
                textNum = leftNum;
                leftNum = 0;
            }else if(operator == INDEFINED){
                btnMul.setBackgroundColor(Color.rgb(200,200,200));
                operator = MUL;
                leftNum = textNum;
                textNum = 0;
            }
        }
        if(getButton == R.id.btnDiv) {
            if(operator == DIV){
                btnDiv.setBackgroundColor(Color.rgb(68,68,68));
                operator = INDEFINED;
                textNum = leftNum;
                leftNum = 0;
            }else if(operator == INDEFINED){
                btnDiv.setBackgroundColor(Color.rgb(200,200,200));
                operator = DIV;
                leftNum = textNum;
                textNum = 0;
            }
        }
        if(getButton == R.id.btnPer) {
            if(operator == PER){
                btnPer.setBackgroundColor(Color.rgb(68,68,68));
                operator = INDEFINED;
                textNum = leftNum;
                leftNum = 0;
            }else if(operator == INDEFINED){
                btnPer.setBackgroundColor(Color.rgb(200,200,200));
                operator = PER;
                leftNum = textNum;
                textNum = 0;
            }
        }
        if(getButton == R.id.btnRoot) {
            textNum = (long)Math.sqrt(textNum);
        }
        if(getButton == R.id.btnEq) {
            rightNum = textNum;
            switch (operator){
                case (ADD):
                    textNum = leftNum + rightNum;
                    break;
                case(SUB):
                    textNum = leftNum - rightNum;
                    break;
                case (MUL):
                    textNum = leftNum * rightNum;
                    break;
                case(DIV):
                    textNum = leftNum / rightNum;
                    break;
                case(PER):
                    textNum = leftNum % rightNum;
                    break;
                default:
                    break;
            }
            rightNum = 0;
            leftNum = 0;
            opratorRset();
        }
        if(getButton == R.id.btnC) {
            text.delete(0,text.length());
            textView.setText("0");
            textNum = 0;
            opratorRset();
        }
        if(getButton == R.id.btnPF) {
            text = new StringBuilder(primeFactorization(textNum));
            textView.setText(text);
        }else{
            text.replace(0, text.length(), String.format("%d", textNum));
            textView.setText(text);
        }


    }
    private void opratorRset(){
        operator = INDEFINED;
        btnAdd.setBackgroundColor(Color.rgb(68,68,68));
        btnSub.setBackgroundColor(Color.rgb(68,68,68));
        btnMul.setBackgroundColor(Color.rgb(68,68,68));
        btnDiv.setBackgroundColor(Color.rgb(68,68,68));
        btnPer.setBackgroundColor(Color.rgb(68,68,68));
    }

    private StringBuilder primeFactorization(long num){
        StringBuilder result = new StringBuilder("");
        int counter = 0;
        long div_num = 2;
        long copy = num;
        while(copy != 1){
            counter = 0;
            while(copy % div_num == 0){
                copy /= div_num;
                counter += 1;
            }
            if(counter > 0){
                if(result.length() != 0){
                    result.append("×");
                }
                result.append( String.format("%d", div_num));
                if(counter > 1){
                    result.append("^");
                    result.append( String.format("%d", counter));
                }
            }
            div_num += 1;
        }
        return result;
    }
}