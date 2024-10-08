package com.example.clockapplication;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmPage extends AppCompatActivity {
    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ArrayList<String> arrayTime = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_page);

        // Call the createNotificationChannel method
        createNotificationChannel();

        // Request permission for notifications on Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1002);
            }
        }

        Button WorldClock = findViewById(R.id.buttonWorldClock);
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

    @SuppressLint("ScheduleExactAlarm")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(AlarmPage.this, AlarmReceiver.class);

            // Unique request code for each alarm
            int requestCodeUnique = (int) System.currentTimeMillis();
            PendingIntent pending = PendingIntent.getBroadcast(AlarmPage.this, requestCodeUnique, intent, PendingIntent.FLAG_IMMUTABLE);

            // Set the time in the Calendar object
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, data.getIntExtra("HOUR", 0));
            calendar.set(Calendar.MINUTE, data.getIntExtra("MIN", 0));
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            // Ensure the time is in the future
            if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);  // Move to the next day if time has passed
            }

            am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);

            // Display the correct time in the Toast message
            Toast.makeText(AlarmPage.this, String.format("Alarm set to %02d:%02d",
                    calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)), Toast.LENGTH_SHORT).show();
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "akchannel";
            String desc = "Channel for Alarm Manager";
            int imp = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("androidknowledge", name, imp);
            channel.setDescription(desc);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void SwitchToWorldClock() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void SwitchToTimer() {
        Intent intent = new Intent(this, TimerPage.class);
        startActivity(intent);
    }
}
