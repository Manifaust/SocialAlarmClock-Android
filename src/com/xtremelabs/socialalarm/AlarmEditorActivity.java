package com.xtremelabs.socialalarm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TimePicker;

import com.xtremelabs.socialalarm.util.AlarmUtil;

public class AlarmEditorActivity extends Activity {
    
	private EditText mName;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_editor);
        
        mName = (EditText) findViewById(R.id.alarm_name);
        
//        findViewById(R.id.choose_ringtone).setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				RingUtil.pickRing(AlarmEditorActivity.this);
//			}
//		});
        
        findViewById(R.id.done_button).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onSetAlarmToTimePickerButtonPress();
				finish();
			}
		});
    }
	
	public void onSetAlarmToTimePickerButtonPress() {
	    TimePicker timePicker = (TimePicker) findViewById(R.id.time_picker);
	    
	    AlarmUtil.onSetAlarmToTimePickerButtonPress(this, timePicker, mName.getText().toString());
	}
}
