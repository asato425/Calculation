package com.example.calculation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculation.AdditionActivity;
import com.example.calculation.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddition = findViewById(R.id.btnAddition);
        Button btnFactorization = findViewById(R.id.btnFactorization);
        Button btnPrimeFactorization = findViewById(R.id.btnPrimeFactorization);
        Button btnData = findViewById(R.id.btnData);
        Button btnOtherCalculations = findViewById(R.id.btnOther);

        // 四則演算ボタンのクリックリスナー
        btnAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 四則演算の画面に遷移（新しいアクティビティを作成して遷移）
                Intent intent = new Intent(MainActivity.this, AdditionActivity.class);
                startActivity(intent);
            }
        });

        // 因数分解ボタンのクリックリスナー
        btnFactorization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 因数分解の画面に遷移（新しいアクティビティを作成して遷移）
                Intent intent = new Intent(MainActivity.this, FactorizationActivity.class);
                startActivity(intent);
            }
        });

        // データの分析ボタンのクリックリスナー
        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 因数分解の画面に遷移（新しいアクティビティを作成して遷移）
                Intent intent = new Intent(MainActivity.this, DataActivity.class);
                startActivity(intent);
            }
        });

        // 素因数分解ボタンのクリックリスナー
        btnPrimeFactorization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 素因数分解の画面に遷移（新しいアクティビティを作成して遷移）
                Intent intent = new Intent(MainActivity.this, PrimeFactorizationActivity.class);
                startActivity(intent);
            }
        });

        // その他の計算ボタンのクリックリスナー
        btnOtherCalculations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // その他の計算の画面に遷移（新しいアクティビティを作成して遷移）
                //Intent intent = new Intent(MainActivity.this, OtherCalculationsActivity.class);
                //startActivity(intent);
            }
        });
    }
}
