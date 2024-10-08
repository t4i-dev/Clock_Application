package com.example.clockapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class TimerPage extends AppCompatActivity {

    private TextView timerTextView;
    //private Button startButton, stopButton, resetButton;
    private final Handler timer_handler = new Handler();
    private long startTime = 0;
    private long timePausedAt = 0;
    private boolean ispaused = true;
    private Button startButton;
    private Button stopButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_page);
        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        Button resetButton = findViewById(R.id.resetButton);
        stopButton  = findViewById(R.id.stopButton);
        Button buttonAlarm=findViewById(R.id.buttonAlarm);
        Button buttonWorldClock=findViewById(R.id.buttonWorldClock);


        stopButton.setEnabled(false);
        stopButton.setOnClickListener(v-> stopTimer());
        startButton.setOnClickListener(v -> startTimer());
        resetButton.setOnClickListener(v -> resetTimer());


        //chuyen page
        buttonAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TimerPage.this,AlarmPage.class);
                startActivity(intent);
            }
        });

        buttonWorldClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TimerPage.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startTimer()
    {
        if (ispaused)
        {
            startTime += (System.currentTimeMillis() - timePausedAt);
            ispaused = false;
            stopButton.setEnabled(true);
            startButton.setEnabled(false);
        }
        else{
            startTime = System.currentTimeMillis();
        }
        timer_handler.post(timerrunnable);
    }

    private void stopTimer()
    {
        if (!ispaused)
        {
            timer_handler.removeCallbacks(timerrunnable);
            timePausedAt = System.currentTimeMillis();
            stopButton.setEnabled(false);
            startButton.setEnabled(true);
            ispaused = true;
        }
    }

    private void resetTimer()
    {
        timePausedAt = 0;
        startTime = 0;
        timer_handler.removeCallbacks(timerrunnable);
        timerTextView.setText("00:00:00");
        stopButton.setEnabled(false);
        startButton.setEnabled(true);
        ispaused = true;
    }

    private final Runnable timerrunnable = new Runnable() {
        @Override
        public void run() {
            long elapsedTime = System.currentTimeMillis() - startTime;
            int seconds = (int) (elapsedTime / 1000);
            int minutes = seconds / 60;
            int hours = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;

            String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            timerTextView.setText(time);

            timer_handler.postDelayed(this, 1000);
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer_handler.removeCallbacks(timerrunnable); // Stop handler when the activity is destroyed
    }
}