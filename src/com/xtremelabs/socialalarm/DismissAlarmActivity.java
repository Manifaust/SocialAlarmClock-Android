package com.xtremelabs.socialalarm;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.xtremelabs.socialalarm.util.FacebookUtil;
import com.xtremelabs.socialalarm.util.RingUtil;
import com.xtremelabs.socialalarm.util.FacebookUtil.FacebookTaskListener;

public class DismissAlarmActivity extends Activity {

	MediaPlayer mMediaPlayer;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dismiss_alarm);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        
        mMediaPlayer = new MediaPlayer();
        RingUtil.playRing(DismissAlarmActivity.this, mMediaPlayer);
    }

    public void onSnoozeButtonPress(View view) {
    	RingUtil.stopRing(mMediaPlayer);
    	FacebookUtil.postAlarmMessage(DismissAlarmActivity.this, new FacebookTaskListener() {
			
			@Override
			public void onComplete() {
				finish();
			}
		});
    }
    
    public void onDismissButtonPress(View view) {
    	RingUtil.stopRing(mMediaPlayer);
        finish();
    }
}
