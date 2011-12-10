package com.xtremelabs.socialalarm;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

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
        
        setAlarm(context, time);
    }
    
    public static void setAlarm(Context context, Calendar specificTime) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        // mgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
        // SystemClock.elapsedRealtime(), PERIOD, pi);
        
        manager.set(AlarmManager.RTC_WAKEUP, specificTime.getTimeInMillis(), pendingIntent);
    }

}
