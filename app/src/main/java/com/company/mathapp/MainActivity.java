package com.company.mathapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_add;
    Button btn_subt;
    Button btn_multi;
    Button btn_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_subt = findViewById(R.id.btn_subt);
        btn_multi = findViewById(R.id.btn_multi);
        btn_play = findViewById(R.id.btn_play);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Game.class);
                startActivity(intent);
                finish();
            }
        });

        btn_multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameMultiplication.class);
                startActivity(intent);
                finish();

            }
        });

        btn_subt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameNegative.class);
                startActivity(intent);
                finish();

            }
        });

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_add.setVisibility(View.VISIBLE);
                btn_subt.setVisibility(View.VISIBLE);
                btn_multi.setVisibility(View.VISIBLE);
                btn_play.setVisibility(View.INVISIBLE);
            }
        });

    }
}