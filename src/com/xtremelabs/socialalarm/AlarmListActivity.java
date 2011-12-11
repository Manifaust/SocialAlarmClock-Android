package com.xtremelabs.socialalarm;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xtremelabs.socialalarm.model.Alarm;
import com.xtremelabs.socialalarm.util.AlarmUtil;
import com.xtremelabs.socialalarm.util.DatabaseHelper;

public class AlarmListActivity extends Activity {
    
	private ArrayAdapter<Alarm> mAlarmAdapter;
	private ListView mListView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.alarm_list);
        
        mListView = (ListView) findViewById(R.id.alarm_list_view);
        
        final SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        final SQLiteDatabase db = databaseHelper.getWritableDatabase();
        
        mAlarmAdapter = new AlarmListAdapter(this, DatabaseHelper.getAllAlarms(db)); 
        
        mListView.setAdapter(mAlarmAdapter);
        mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
				Builder alertBuilder = new Builder(AlarmListActivity.this);
		        alertBuilder.setTitle("Delete?");
		        alertBuilder.setPositiveButton("Yes", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						AlarmUtil.removeAlarm(AlarmListActivity.this, mAlarmAdapter.getItem(arg2));
						mAlarmAdapter = new AlarmListAdapter(AlarmListActivity.this, DatabaseHelper.getAllAlarms(db)); 
						mListView.setAdapter(mAlarmAdapter);
					}
				});
		        alertBuilder.create().show();
				return false;
			}
		});
    }
	
}