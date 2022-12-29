package com.company.mathapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity {

    TextView score;
    TextView life;
    TextView time;

    TextView question;
    EditText answer;
    Button btn_ok;
    Button btn_next;

    Random random = new Random();
    int number1;
    int number2;
    int userAnswer;
    int realAnswer;
    int userScore = 0;
    int userLife = 3;

    CountDownTimer timer;

    private static final long START_TIMER_IN_MILIS = 60000;
    Boolean timer_running;
    Long time_left_in_milis = START_TIMER_IN_MILIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        score = findViewById(R.id.text_view_score);
        life = findViewById(R.id.text_view_life);
        time = findViewById(R.id.text_view_time);
        question = findViewById(R.id.text_view_question);
        answer = findViewById(R.id.edit_text_answer);
        btn_ok = findViewById(R.id.btn_ok);
        btn_next = findViewById(R.id.btn_next);

        gameContinue();

        btn_ok.setOnClickListener(v -> {
            userAnswer = Integer.valueOf(answer.getText().toString());
                pauseTimer();
            if (userAnswer == realAnswer) {
                userScore = userScore + 10;
                score.setText("" + userScore);
                question.setText("Gongrats, You've got the correct answer.");
            } else {
                userLife = userLife - 1;
                life.setText("" + userLife);
                question.setText("Wrong answer.... ");
            }

        });

        btn_next.setOnClickListener(v -> {

            answer.setText("");

            resetTimer();

            if (userLife <= 0)
            {
                Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Game.this, Result.class);
                intent.putExtra("score",userScore);
                startActivity(intent);
                finish();
            }
            else
            {
                gameContinue();
            }
        });

    }

    public void gameContinue() {
        number1 = random.nextInt(100);
        number2 = random.nextInt(100);

        realAnswer = number1 + number2;

        question.setText(number1 + "+" + number2);
        startTimer();

    }

    public void startTimer() {
        timer = new CountDownTimer(time_left_in_milis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_milis = millisUntilFinished;
                updateText();
            }

            @Override
            public void onFinish() {
                timer_running = false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife = userLife - 1;
                question.setText("Time is Up...");
                life.setText(""+userLife);

            }
        }.start();

        timer_running = true;
    }

    public void updateText()
    {
        int second = (int)(time_left_in_milis / 1000) %60;
        String time_left = String.format(Locale.getDefault(),"%02d", second);
        time.setText(time_left);
    }
    public void pauseTimer()
    {
        timer.cancel();
        timer_running = false;
    }
    public void resetTimer()
    {
        time_left_in_milis = START_TIMER_IN_MILIS;
        updateText();
    }

}