package com.example.pomodoroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button startBtn;
    private Button pauseBtn;
    private Button finishBtn;
    private Button resetBtn;
    private int seconds = 1500;
    private boolean running;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }

        runTimer();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }
    public void runTimer(){
        final TextView textView = (TextView) findViewById(R.id.textView);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                //int hours = seconds/3600;
                int minutes = (seconds%3600) /60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(),
                        "%02d:%02d", minutes, secs);
                textView.setText(time);
                if (running){
                    seconds--;
            }
                handler.postDelayed(this, 1000);
        }

        });
    }
    public void startClick (View view) {
        startBtn = findViewById(R.id.startBtn);
        running = true;

    }

    public void pauseClick (View view) {
        pauseBtn = findViewById(R.id.pauseBtn);
        running = false;
    }

    public void resetClick (View view){
        resetBtn = findViewById(R.id.resetBtn);
        running = false;
        seconds = 1500;
    }
    public void finishClick (View view){
        finishBtn = findViewById(R.id.finishBtn);
        running = false;
        finishBtn.setText("Done");
        seconds = 0;
    }


}