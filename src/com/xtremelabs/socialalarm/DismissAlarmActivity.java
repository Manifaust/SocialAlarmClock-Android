package com.xtremelabs.socialalarm;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.xtremelabs.socialalarm.util.FacebookUtil;
import com.xtremelabs.socialalarm.util.FacebookUtil.FacebookTaskListener;
import com.xtremelabs.socialalarm.util.RingUtil;

public class DismissAlarmActivity extends Activity {
	private static final int ONE_MINUTE = 60;

	MediaPlayer mMediaPlayer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dismiss_alarm);
		getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
						| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
						| WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
						| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

		mMediaPlayer = new MediaPlayer();
		RingUtil.playRing(DismissAlarmActivity.this, mMediaPlayer);
	}

	public void onSnoozeButtonPress(View view) {
		RingUtil.stopRing(mMediaPlayer);

		int timeSnoozed = 0;
		switch (view.getId()) {
		case R.id.snooze_5sec:
			timeSnoozed = 5;
			break;
		case R.id.snooze_5min:
			timeSnoozed = 5 * ONE_MINUTE;
			break;
		case R.id.snooze_15min:
			timeSnoozed = 15 * ONE_MINUTE;
			break;
		}

		if (timeSnoozed > 0) {
			Log.i("Xtreme", "calling post");
			AlarmReceiver.setAlarm(this, timeSnoozed);
			postSnoozeToFB(timeSnoozed);
		} else {
			finish();
		}
	}

	private void postSnoozeToFB(int timeSnoozed) {
		FacebookUtil.postAlarmMessage(DismissAlarmActivity.this,
				new FacebookTaskListener() {
					@Override
					public void onComplete() {
						Toast.makeText(DismissAlarmActivity.this,
								"Everyone now knows you suck at waking up",
								Toast.LENGTH_SHORT).show();
						finish();
					}
				}, timeSnoozed);
	}

	public void onDismissButtonPress(View view) {
		RingUtil.stopRing(mMediaPlayer);
		finish();
	}
}
