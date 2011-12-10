package com.xtremelabs.socialalarm;

import android.text.format.Time;

public class Alarm {
	
	private String		mName;
	private Time		mTime;
	private boolean		mEnabled;
	
	public Alarm() {
		mName = "";
		mTime = new Time();
		mTime.setToNow();
		mEnabled = false;
	}
	
	public Alarm(String name, Time time, boolean enabled) {
		mName = name;
		mTime = time;
		mEnabled = enabled;
	}
	
	public void enable() {
		mEnabled = true;
	}
	
	public void disable() {
		mEnabled = false;
	}
	
	public String getName() {
		return mName;
	}
	
	public void setName(String name) {
		mName = name;
	}
	
	public Time getTime() {
		return mTime;
	}
	
	public void setTime(Time time) {
		mTime = time;
	}

	public boolean isEnabled() {
		return mEnabled;
	}
	
	public void setEnabled(boolean enabled) {
		mEnabled = enabled;
	}
}
