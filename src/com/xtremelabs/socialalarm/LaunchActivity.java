package com.xtremelabs.socialalarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.xtremelabs.socialalarm.R;

public class LaunchActivity extends Activity {
    private static final String TAG = "LaunchActivity";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onLaunchDismissAlarmActivityButtonPress(View view) {
        Log.i(TAG, "click launch!");
        startActivity(new Intent(this, DismissAlarmActivity.class));
    }
}