package com.xtremelabs.socialalarm.util;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TimePicker;
import android.widget.Toast;

import com.xtremelabs.socialalarm.AlarmReceiver;
import com.xtremelabs.socialalarm.model.Alarm;

public class AlarmUtil {

	private static ArrayList<Alarm> mAlarms = new ArrayList<Alarm>();
	
	public static void onSetAlarmToTimePickerButtonPress(Context context, TimePicker timePicker, String name) {
	    int hour = timePicker.getCurrentHour();
	    int minute = timePicker.getCurrentMinute();
	    
	    Calendar currentTime = Calendar.getInstance();
	    Calendar setTime = Calendar.getInstance();
	    setTime.set(Calendar.HOUR_OF_DAY, hour);
	    setTime.set(Calendar.MINUTE, minute);
	    setTime.set(Calendar.SECOND, 0);
	    
	    if (setTime.before(currentTime)) {
	        setTime.add(Calendar.DAY_OF_YEAR, 1);
	    }

	    long timeLeft = (setTime.getTimeInMillis() - currentTime.getTimeInMillis()) / 1000;
	    if (timeLeft < 60) {
	        Toast.makeText(context, "There's " + timeLeft + " seconds left", Toast.LENGTH_SHORT).show();
	    } else {
	        timeLeft /= 60;
	        Toast.makeText(context, "There's " + timeLeft + " minutes left", Toast.LENGTH_SHORT).show();
	    }
	    
	    Alarm alarm = new Alarm(name, setTime.getTimeInMillis(), true, -1);
	    alarm = addAlarm(context, alarm);
	    
	    mAlarms.add(alarm);
	    Log.i("Xtreme", "Alarm " + alarm.getName() + " " + alarm.getId());
	    
	    AlarmReceiver.setAlarm(context, setTime);
	}
	
	private static Alarm addAlarm(Context context, final Alarm alarm)
    {
        final SQLiteOpenHelper databaseHelper = new DatabaseHelper(context);
        final SQLiteDatabase db = databaseHelper.getWritableDatabase();
        final long id = DatabaseHelper.addAlarm(db, alarm);
        db.close();
        
        return new Alarm(id, alarm);
    }
}
