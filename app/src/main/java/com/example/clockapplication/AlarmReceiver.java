package com.example.clockapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Handle the alarm event here
        Toast.makeText(context, "Alarm triggered!", Toast.LENGTH_SHORT).show();
        // Perform the desired action (e.g., notification or any task)
    }
}
