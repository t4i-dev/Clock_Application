package com.example.clockapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.os.Handler;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
private Button buttonAlarm;
private Button buttonTimer;
private Button getButton;
private Handler handler;
private Runnable runnable;
private TextView clockTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAlarm=findViewById(R.id.buttonAlarm);
        buttonTimer=findViewById(R.id.buttonTimer);

        clockTextView = findViewById(R.id.textViewClock);
        handler = new Handler();

        // Create a Runnable to update the time every second
        runnable = new Runnable() {
            @Override
            public void run() {
                updateClock();
                handler.postDelayed(this, 1000); // Repeat every second
            }
        };
        handler.post(runnable); // Start the runnable
        buttonAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AlarmPage.class);
                startActivity(intent);
            }
        });

        buttonTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,TimerPage.class);
                startActivity(intent);
            }
        });

    }
    private void updateClock() {
        // Get the current time
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        clockTextView.setText(currentTime);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable); // Stop the updates when the activity is destroyed
    }
}