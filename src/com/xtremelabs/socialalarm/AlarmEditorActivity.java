package com.xtremelabs.socialalarm;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TimePicker;
import android.widget.Toast;

import com.xtremelabs.socialalarm.util.RingUtil;

public class AlarmEditorActivity extends Activity {
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_editor);
        
//        findViewById(R.id.time_picker)
        
//        findViewById(R.id.alarm_name)
        
        findViewById(R.id.choose_ringtone).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RingUtil.pickRing(AlarmEditorActivity.this);
			}
		});
        
        findViewById(R.id.done_button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
    }
}
