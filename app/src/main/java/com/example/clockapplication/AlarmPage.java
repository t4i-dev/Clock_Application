package com.example.clockapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;


public class AlarmPage extends AppCompatActivity {

    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_page);
        Button WorldClock = findViewById(R.id.butonWorldClock);
        Button Timer = findViewById(R.id.buttonTimer);
        Button Add = findViewById(R.id.add_btn);
        layout = findViewById(R.id.linearLayout);


        WorldClock.setOnClickListener(view -> SwitchToWorldClock());
        Timer.setOnClickListener(view -> SwitchToTimer());
        Add.setOnClickListener(view -> {
            Intent intent = new Intent(this, AlarmSetUp.class);
            startActivityForResult(intent, 100);
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            if (resultCode == RESULT_OK)
            {
                Button new_alarm = new Button(this);
                int hour = data.getIntExtra("HOUR", 0);
                int min = data.getIntExtra("MIN", 0);

                new_alarm.setText(String.format("Alarm:      %02d:%02d ", hour, min));
                new_alarm.setLayoutDirection(R.layout.card_design);

                layout.addView(new_alarm);
            }
        }
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