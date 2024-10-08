package com.example.clockapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;



public class AlarmPage extends AppCompatActivity {

    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ArrayList<String>arrayTime = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_page);
        Button WorldClock = findViewById(R.id.butonWorldClock);
        Button Timer = findViewById(R.id.buttonTimer);
        Button Add = findViewById(R.id.add_btn);
        linearLayout = findViewById(R.id.linearLayout);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(AlarmPage.this));

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
                LinearLayout new_alarm = (LinearLayout) getLayoutInflater().inflate(R.layout.item, null);
                int hour = data.getIntExtra("HOUR", 0);
                int min = data.getIntExtra("MIN", 0);

                arrayTime.add(String.format("%02d:%02d:00 ", hour, min));

                recyclerAdapter =new RecyclerAdapter(arrayTime,AlarmPage.this);
                recyclerView.setAdapter(recyclerAdapter);
                TextView alarmTime = new_alarm.findViewById(R.id.alarm_time);
                alarmTime.setText(String.format("Alarm: %02d:%02d", hour, min));
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