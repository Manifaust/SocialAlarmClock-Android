package com.xtremelabs.socialalarm.util;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

public class FacebookUtil {

	public static void loginToFacebook(final Activity activity) {

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
			}

			@Override
			public void onCancel() {
			}
		};

		if (config.getFacebookAccessExpires() == 0L && config.getFacebookAccessToken() == null) {
			facebook.authorize(activity, Config.Facebook.PERMISSIONS, Facebook.FORCE_DIALOG_AUTH, loginDialogListener);
		}
	}

}
