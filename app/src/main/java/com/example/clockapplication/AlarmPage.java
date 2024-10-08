package com.example.clockapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AlarmPage extends AppCompatActivity {
    private Button buttonWorldClock;
    private Button buttonTimer;
    private Button buttonAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buttonWorldClock=findViewById(R.id.butonWorldClock);
        buttonTimer=findViewById(R.id.buttonTimer);
        //chuyen page
        buttonTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AlarmPage.this,TimerPage.class);
                startActivity(intent);
            }
        });

        buttonWorldClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AlarmPage.this,MainActivity.class);
                startActivity(intent);
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}