package com.xtremelabs.socialalarm;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AlarmListActivity extends Activity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.alarm_list);
        
        ListView alarmListView = (ListView) findViewById(R.id.alarm_list_view);
        
        List<Alarm> alarms = new ArrayList<Alarm>();
        
        alarms.add(new Alarm("1", new Time(), true));
        alarms.add(new Alarm("2", new Time(), false));
        alarms.add(new Alarm("3", new Time(), true));
        alarms.add(new Alarm("4", new Time(), true));
        alarms.add(new Alarm("5", new Time(), true));
        alarms.add(new Alarm("6", new Time(), false));
        alarms.add(new Alarm("7", new Time(), true));
        
        ArrayAdapter<Alarm> alarmAdapter = new AlarmListAdapter(this, alarms); 
        
        alarmListView.setAdapter(alarmAdapter);
        
//        alarmAdapter.notifyDataSetChanged();       
        
    }
}