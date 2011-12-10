package com.xtremelabs.socialalarm;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class LaunchActivity extends Activity {
    private static final String TAG = "LaunchActivity";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onLaunchDismissAlarmActivityButtonPress(View view) {
        startActivity(new Intent(this, DismissAlarmActivity.class));
    }
    
    public void onSetAlarmButtonPress(View view) {
        Log.v(TAG, "set alarm button press");
        setAlarm(5);
    }

    private void setAlarm(int secondsInTheFuture) {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

//        mgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), PERIOD, pi);

        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(System.currentTimeMillis());
        time.add(Calendar.SECOND, secondsInTheFuture);
        manager.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pendingIntent);
    }
    
    
}