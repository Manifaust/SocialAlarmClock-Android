package com.xtremelabs.socialalarm;

import com.xtremelabs.socialalarm.util.FacebookUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class LaunchActivity extends Activity {
    /** Called when the activity is first created. */
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
}