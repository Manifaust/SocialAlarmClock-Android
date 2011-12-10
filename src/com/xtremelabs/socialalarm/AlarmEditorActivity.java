package com.xtremelabs.socialalarm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import com.xtremelabs.socialalarm.util.AlarmUtil;

public class AlarmEditorActivity extends Activity {
    
	private EditText mName;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_editor);
        
        mName = (EditText) findViewById(R.id.name);
    }
	
	public void onSetAlarmToTimePickerButtonPress(View view) {
	    TimePicker timePicker = (TimePicker) findViewById(R.id.time_picker);
	    
	    AlarmUtil.onSetAlarmToTimePickerButtonPress(this, timePicker, mName.getText().toString());
	}
}