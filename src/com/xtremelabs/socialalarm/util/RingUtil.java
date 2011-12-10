package com.xtremelabs.socialalarm.util;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;

public class RingUtil {

	public static final int RESULT_PICK_RING = 0;
	
	private static Uri mRingUri;

	public static void pickRing(Activity activity) {
		Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
		intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");

		if (mRingUri != null) {
			intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, mRingUri);
		} else {
			intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, (Uri) null);
		}
		activity.startActivityForResult(intent, RESULT_PICK_RING);
	}
	
	public static void setRingUri(Uri ringUri){
		mRingUri = ringUri;
	}

	public static void playRing(Context context, MediaPlayer mediaPlayer) {
		if (mRingUri == null) {
			mRingUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		}

		try {
			mediaPlayer.setDataSource(context, mRingUri);
			final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
			if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
				mediaPlayer.setLooping(true);
				mediaPlayer.prepare();
				mediaPlayer.start();
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void stopRing(MediaPlayer mediaPlayer) {
		mediaPlayer.stop();
	}
}
