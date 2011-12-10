package com.xtremelabs.socialalarm;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.xtremelabs.socialalarm.util.FacebookUtil;
import com.xtremelabs.socialalarm.util.FacebookUtil.FacebookTaskListener;
import com.xtremelabs.socialalarm.util.RingUtil;

public class LaunchActivity extends Activity {
	private static final String TAG = "LaunchActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		findViewById(R.id.fbLoginButton).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FacebookUtil.loginToFacebook(LaunchActivity.this, mLoginListener);
			}
		});

		findViewById(R.id.pickRing).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RingUtil.pickRing(LaunchActivity.this);
			}
		});
	}

	private FacebookUtil.FacebookTaskListener mLoginListener = new FacebookTaskListener() {

		@Override
		public void onComplete() {
			Toast.makeText(LaunchActivity.this, "Login complete - posting", Toast.LENGTH_SHORT).show();
			// Next screen
		}
	};

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

		// mgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
		// SystemClock.elapsedRealtime(), PERIOD, pi);

		Calendar time = Calendar.getInstance();
		time.setTimeInMillis(System.currentTimeMillis());
		time.add(Calendar.SECOND, secondsInTheFuture);
		manager.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pendingIntent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null) {
			switch (requestCode) {
			case RingUtil.RESULT_PICK_RING:
				if (data.hasExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI)) {
					RingUtil.setRingUri((Uri) data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI));
				}
				break;

			default:
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}