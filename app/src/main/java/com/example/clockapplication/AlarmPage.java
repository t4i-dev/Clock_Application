package com.example.clockapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class AlarmPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_page);
        Button WorldClock = findViewById(R.id.butonWorldClock);
        Button Timer = findViewById(R.id.buttonTimer);
        Button Add = findViewById(R.id.add_btn);



        WorldClock.setOnClickListener(view -> SwitchToWorldClock());
        Timer.setOnClickListener(view -> SwitchToTimer());
        Add.setOnClickListener(view -> AddAlarm());


    }

    private void AddAlarm()
    {
        Intent intent = new Intent(this, AlarmSetUp.class);
        startActivity(intent);
        Button new_alarm = new Button(this);
        Intent result = new Intent();
        int hour = result.getIntExtra("HOUR", 0);
        int min = result.getIntExtra("MIN", 0);
        new_alarm.setText(String.format("%02d:%02d",hour ,min));
    }


    private void SwitchToWorldClock()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void SwitchToTimer()
    {
        Intent intent = new Intent(this, TimerPage.class);
        startActivity(intent);
    }



}