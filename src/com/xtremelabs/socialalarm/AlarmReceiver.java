package com.xtremelabs.socialalarm;

import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.xtremelabs.socialalarm.model.Alarm;
import com.xtremelabs.socialalarm.util.AlarmUtil;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = "AlarmReceiver";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent alarmIntent = new Intent(context, DismissAlarmActivity.class);
        alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(alarmIntent);
        Log.i(TAG, "alarm went off");
    }

    public static void setAlarm(Context context, int secondsInTheFuture) {
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(System.currentTimeMillis());
        time.add(Calendar.SECOND, secondsInTheFuture);
        
        Alarm alarm = new Alarm("Secs", secondsInTheFuture, true, -1);
        AlarmUtil.addAlarm(context, alarm);
        
        AlarmUtil.setAlarm(context, alarm);
    }
}
