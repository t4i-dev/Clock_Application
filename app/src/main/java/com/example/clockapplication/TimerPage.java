package com.example.clockapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class TimerPage extends AppCompatActivity {

    private TextView timerTextView;
    //private Button startButton, stopButton, resetButton;
    private final Handler timer_handler = new Handler();
    private long startTime = 0;
    private long timePausedAt = 0;
    private boolean ispaused = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_page);
        timerTextView = findViewById(R.id.timerTextView);
        Button startButton = findViewById(R.id.startButton);
        Button resetButton = findViewById(R.id.resetButton);
        Button stopButton  = findViewById(R.id.stopButton);
        Button buttonAlarm=findViewById(R.id.buttonAlarm);
        Button buttonWorldClock=findViewById(R.id.butonWorldClock);


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
            ispaused = true;
        }
    }

    private void resetTimer()
    {
        timer_handler.removeCallbacks(timerrunnable);
        timerTextView.setText("00:00:00");
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