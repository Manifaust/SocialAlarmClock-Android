package com.xtremelabs.socialalarm.adapter;

import java.util.List;

import android.app.Activity;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.xtremelabs.socialalarm.R;
import com.xtremelabs.socialalarm.model.Alarm;
import com.xtremelabs.socialalarm.util.TimeFormatter;

public class AlarmListAdapter extends ArrayAdapter<Alarm> {

	private class AlarmView {
		public TextView timeLabel;
		public TextView alarmName;
//		public CheckBox alarmEnabled;	
	}
	
	private Activity activity;
	
	public AlarmListAdapter(Activity activity, List<Alarm> alarmList) {
		super(activity, R.layout.alarm_list_item, alarmList);
		this.activity = activity;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		AlarmView alarmView = null;
		
		if (view == null) {
			LayoutInflater inflater = activity.getLayoutInflater(); 
			view = inflater.inflate(R.layout.alarm_list_item, null);
			
			alarmView = new AlarmView();
			alarmView.alarmName = (TextView) view.findViewById(R.id.alarm_name);
			alarmView.timeLabel = (TextView) view.findViewById(R.id.time_label);
//			alarmView.alarmEnabled = (CheckBox) view.findViewById(R.id.alarm_enabled);
			
			view.setTag(alarmView);
		} else {
			alarmView = (AlarmView) view.getTag(); 
		}
		
		Alarm alarm = getItem(position);
		
		if (alarm != null) {
			Time alarmTime = new Time();
			alarmTime.set( alarm.getTime() );
			alarmView.timeLabel.setText(TimeFormatter.formatTime(alarmTime));
			alarmView.alarmName.setText(alarm.getName());
//			alarmView.alarmEnabled.setChecked(alarm.isEnabled());
		}
		
		view.setBackgroundResource(R.drawable.selection_colors);
//		view.setBackgroundColor(Color.GRAY);
		
		return view;
	}
}
