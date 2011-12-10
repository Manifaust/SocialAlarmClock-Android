package com.xtremelabs.socialalarm.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.xtremelabs.socialalarm.network.FacebookPostTask;

public class FacebookUtil {

	public static void loginToFacebook(final Activity activity, final FacebookTaskListener loginListener) {

		final Config config = new Config(activity);
		final Facebook facebook = new Facebook(Config.Facebook.APPLICATION_ID);

		DialogListener loginDialogListener = new DialogListener() {

			@Override
			public void onFacebookError(FacebookError e) {
				Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onError(DialogError e) {
				Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onComplete(Bundle values) {
				config.setFacebookAccessExpires(facebook.getAccessExpires());
				config.setFacebookAccessToken(facebook.getAccessToken());

				Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show();
				loginListener.onComplete();
			}

			@Override
			public void onCancel() {
			}
		};

		if (config.getFacebookAccessExpires() == 0L && config.getFacebookAccessToken() == null) {
			facebook.authorize(activity, Config.Facebook.PERMISSIONS, Facebook.FORCE_DIALOG_AUTH, loginDialogListener);
		} else {
			loginListener.onComplete();
		}
	}
	
	public static void postAlarmMessage(Context context, FacebookTaskListener fbTaskListener){
		final Config config = new Config(context);
		
		if (config.getFacebookAccessExpires() == 0L && config.getFacebookAccessToken() == null) {
			Toast.makeText(context, "Login to FB so we can ridicule you!", Toast.LENGTH_SHORT).show();
		} else {
			Bundle parameters = new Bundle();
			parameters.putString("name", "I just snoozed my alarm clock!");
			parameters.putString("caption", "I'm a lazy sonova who will never get anywhere in life.");
			parameters.putString("picture", "http://www.students-in-touch.com/S-I-THOME/lifestyle/wp-content/uploads/2010/09/20100908_couchpotato.jpg");
			
			new FacebookPostTask(context, parameters, fbTaskListener).execute((Void[]) null);
		}
		
	}

	public static interface FacebookTaskListener{
		/*
		 * Action to perform once task completes
		 */
		public void onComplete();
	}
}
