package com.xtremelabs.socialalarm.util;

import android.text.format.Time;

public class TimeFormatter {

	private static final String TIME_FORMAT = "%b %d, %l:%M";
	
	public static String formatTime(Time time) {
		return time.format(TIME_FORMAT);
	}
}
