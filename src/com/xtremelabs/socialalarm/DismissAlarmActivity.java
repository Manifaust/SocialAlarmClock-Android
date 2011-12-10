package com.xtremelabs.socialalarm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class DismissAlarmActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dismiss_alarm);
    }

    public void onSnoozeButtonPress(View view) {
        finish();
    }
    
    public void onDismissButtonPress(View view) {
        finish();
    }
}
