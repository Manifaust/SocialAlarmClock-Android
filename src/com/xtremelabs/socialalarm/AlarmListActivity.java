package com.xtremelabs.socialalarm;

import java.util.List;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xtremelabs.socialalarm.model.Alarm;
import com.xtremelabs.socialalarm.util.DatabaseHelper;

public class AlarmListActivity extends Activity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.alarm_list);
        
        ListView alarmListView = (ListView) findViewById(R.id.alarm_list_view);
        
        final SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        final SQLiteDatabase db = databaseHelper.getWritableDatabase();
        
        List<Alarm> alarms = DatabaseHelper.getAllAlarms(db);
        
        ArrayAdapter<Alarm> alarmAdapter = new AlarmListAdapter(this, alarms); 
        
        alarmListView.setAdapter(alarmAdapter);
    }
}