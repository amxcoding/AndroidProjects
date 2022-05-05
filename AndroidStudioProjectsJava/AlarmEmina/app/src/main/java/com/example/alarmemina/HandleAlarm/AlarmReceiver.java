package com.example.alarmemina.HandleAlarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    Intent alarmIntent;
    int state;

    @Override
    public void onReceive(final Context context, Intent intent) {
        state = intent.getExtras().getInt("state");

        alarmIntent = new Intent(context, HandleAlarm.class);
        String sound = intent.getExtras().getString("sound");
        alarmIntent.putExtra("sound", sound);

        if(state == 1) {
            context.startActivity(alarmIntent);
        }
    }

}
