package com.xtremelabs.socialalarm;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TimePicker;
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
		AlarmReceiver.setAlarm(this, 5);
	}
	
	public void onSetAlarmToTimePickerButtonPress(View view) {
	    TimePicker timePicker = (TimePicker) findViewById(R.id.time_picker);
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

	    long timeLeft = (setTime.getTimeInMillis() - currentTime.getTimeInMillis()) / (1000 * 60);
	    Toast.makeText(this, "There's " + timeLeft + " minutes left", Toast.LENGTH_SHORT).show();
	    
	    AlarmReceiver.setAlarm(this, setTime);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case RingUtil.RESULT_PICK_RING:
			if(data.hasExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI)){
				RingUtil.setRingUri((Uri) data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI));
			}
			break;

		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}