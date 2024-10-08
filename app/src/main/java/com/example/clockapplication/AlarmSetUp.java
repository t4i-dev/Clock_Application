package com.example.clockapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class AlarmSetUp extends AppCompatActivity {

    private TimePicker time_picker;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        time_picker = findViewById(R.id.time_picker);
        Button set = findViewById(R.id.set_alarm_button);

        set.setOnClickListener(view -> SetAlarm());
        time_picker.setIs24HourView(true);
    }

    private void SetAlarm()
    {
        int hour = time_picker.getHour();
        int minute = time_picker.getMinute();
        Intent intent = new Intent();
        intent.putExtra("HOUR", hour);
        intent.putExtra("MIN", minute);
        setResult(RESULT_OK, intent);
        finish();
    }


}
