package com.xtremelabs.socialalarm.util;

import java.io.IOException;
import java.net.MalformedURLException;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
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
		facebook.setAccessExpires(config.getFacebookAccessExpires());
		facebook.setAccessToken(config.getFacebookAccessToken());

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

		if (!isFacebookLoggedIn(activity)) {
			facebook.authorize(activity, Config.Facebook.PERMISSIONS, Facebook.FORCE_DIALOG_AUTH, loginDialogListener);
		} else {
			loginListener.onComplete();
		}
	}

	public static void postAlarmMessage(Context context, FacebookTaskListener fbTaskListener, int secondsSnoozed) {

		if (!isFacebookLoggedIn(context)) {
			Toast.makeText(context, "Login to FB so we can ridicule you!", Toast.LENGTH_SHORT).show();
		} else {
			Bundle parameters = new Bundle();
			parameters.putString("name", "I just snoozed my alarm clock for " + secondsSnoozed + " seconds!");
			parameters.putString("caption", "I'm a lazy sonova who will never get anywhere in life.");
			parameters.putString("picture",
					"http://www.students-in-touch.com/S-I-THOME/lifestyle/wp-content/uploads/2010/09/20100908_couchpotato.jpg");

			new FacebookPostTask(context, parameters, fbTaskListener).execute((Void[]) null);
		}

	}

	public static boolean isFacebookLoggedIn(Context context) {
		final Config config = new Config(context);

		final Facebook facebook = new Facebook(Config.Facebook.APPLICATION_ID);
		facebook.setAccessExpires(config.getFacebookAccessExpires());
		facebook.setAccessToken(config.getFacebookAccessToken());
		return facebook.isSessionValid();
	}

	public static void logOutOfFacebook(final Context context) {
		final Config config = new Config(context);

		final Facebook facebook = new Facebook(Config.Facebook.APPLICATION_ID);
		facebook.setAccessExpires(config.getFacebookAccessExpires());
		facebook.setAccessToken(config.getFacebookAccessToken());

		config.removeFacebookToken();

		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				try {
					facebook.logout(context);
				} catch (final MalformedURLException e) {
					e.printStackTrace();
				} catch (final IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		}.execute((Void[])null);
	}

	public static interface FacebookTaskListener {
		/*
		 * Action to perform once task completes
		 */
		public void onComplete();
	}
}
