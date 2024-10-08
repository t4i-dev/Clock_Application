package com.example.clockapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AlarmPage extends AppCompatActivity {
    private Button buttonWorldClock;
    private Button buttonTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_page);
        Button WorldClock = findViewById(R.id.butonWorldClock);
        Button Timer = findViewById(R.id.buttonTimer);
        Button Add = findViewById(R.id.add_btn);



        WorldClock.setOnClickListener(view -> SwitchToWorldClock());
        Timer.setOnClickListener(view -> SwitchtoTimer());
        Add.setOnClickListener(view -> AddAlarm());


    }

    private void AddAlarm()
    {

    }


    private void SwitchToWorldClock()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void SwitchtoTimer()
    {
        Intent intent = new Intent(this, TimerPage.class);
        startActivity(intent);
    }



}