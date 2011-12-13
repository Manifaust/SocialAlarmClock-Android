package com.xtremelabs.socialalarm.model;

import java.io.Serializable;

public class Alarm implements Serializable {

	private static final long serialVersionUID = -7104155458991253488L;

	private String mName;
	private long mTime;
	private boolean mEnabled;
	private long mId;

	public Alarm() {
		mName = "";
		mTime = System.currentTimeMillis();
		mEnabled = false;
	}

	public Alarm(String name, long time, boolean enabled, long id) {
		mName = name;
		mTime = time;
		mEnabled = enabled;
		mId = id;
	}

	public Alarm(long id, Alarm alarm) {
		mName = alarm.getName();
		mTime = alarm.getTime();
		mEnabled = alarm.isEnabled();
		mId = id;
	}

	public void enable() {
		mEnabled = true;
	}

	public void disable() {
		mEnabled = false;
	}

	public String getName() {
		if (mName.trim().length() > 0) {
			return mName;
		} else{
			return "Morning Nuisance";
		}
	}

	public void setName(String name) {
		mName = name;
	}

	public long getTime() {
		return mTime;
	}

	public void setTime(long time) {
		mTime = time;
	}

	public boolean isEnabled() {
		return mEnabled;
	}

	public void setEnabled(boolean enabled) {
		mEnabled = enabled;
	}

	public long getId() {
		return mId;
	}

	public void setId(int mId) {
		this.mId = mId;
	}

	public int getIdAsInt() {
		// probably not the best idea
		return (int) mId;
	}
}
