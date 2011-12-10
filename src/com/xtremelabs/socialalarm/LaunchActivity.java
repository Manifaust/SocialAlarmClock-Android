package com.xtremelabs.socialalarm;

import com.xtremelabs.socialalarm.util.FacebookUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.util.Log;
import android.view.View;

import com.xtremelabs.socialalarm.R;

public class LaunchActivity extends Activity {
    private static final String TAG = "LaunchActivity";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        findViewById(R.id.fbLoginButton).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FacebookUtil.loginToFacebook(LaunchActivity.this);
			}
		});
    }
    
    public void onLaunchDismissAlarmActivityButtonPress(View view) {
        Log.i(TAG, "click launch!");
        startActivity(new Intent(this, DismissAlarmActivity.class));
    }
}