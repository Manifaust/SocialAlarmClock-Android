package com.xtremelabs.socialalarm;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.xtremelabs.socialalarm.adapter.AlarmListAdapter;
import com.xtremelabs.socialalarm.model.Alarm;
import com.xtremelabs.socialalarm.util.AlarmUtil;
import com.xtremelabs.socialalarm.util.RingUtil;

public class AlarmListActivity extends Activity {

	private ArrayAdapter<Alarm> mAlarmAdapter;
	private ListView mListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.alarm_list);

		findViewById(R.id.addAlarmButton).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(AlarmListActivity.this,
								AlarmEditorActivity.class);
						startActivity(intent);
					}

				});

		findViewById(R.id.choose_ringtone).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						RingUtil.pickRing(AlarmListActivity.this);
					}
				});

		mListView = (ListView) findViewById(R.id.alarm_list_view);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO edit alarms
			}
		});

		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				Builder alertBuilder = new Builder(AlarmListActivity.this);
				alertBuilder.setTitle("Delete?");
				alertBuilder.setPositiveButton("Yes", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						AlarmUtil.removeAlarm(AlarmListActivity.this,
								mAlarmAdapter.getItem(arg2));
						
						mAlarmAdapter = new AlarmListAdapter(
								AlarmListActivity.this, AlarmUtil.getCurrentAlarms(AlarmListActivity.this));
						mListView.setAdapter(mAlarmAdapter);
					}
				});
				alertBuilder.create().show();
				return false;
			}
		});
	}

	@Override
	protected void onResume() {
		mAlarmAdapter = new AlarmListAdapter(AlarmListActivity.this,
				AlarmUtil.getCurrentAlarms(this));
		mListView.setAdapter(mAlarmAdapter);
		super.onResume();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null) {
			switch (requestCode) {
			case RingUtil.RESULT_PICK_RING:
				if (data.hasExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI)) {
					RingUtil.setRingUri(
							this,
							(Uri) data
									.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI));
				}
				break;

			default:
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}