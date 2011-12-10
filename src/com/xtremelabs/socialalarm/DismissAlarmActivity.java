package com.xtremelabs.socialalarm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.xtremelabs.socialalarm.util.FacebookUtil;
import com.xtremelabs.socialalarm.util.FacebookUtil.FacebookTaskListener;

public class DismissAlarmActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dismiss_alarm);
    }

    public void onSnoozeButtonPress(View view) {
    	FacebookUtil.postAlarmMessage(DismissAlarmActivity.this, new FacebookTaskListener() {
			
			@Override
			public void onComplete() {
				finish();
			}
		});
    }
    
    public void onDismissButtonPress(View view) {
        finish();
    }
}
