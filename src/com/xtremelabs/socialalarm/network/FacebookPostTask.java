package com.xtremelabs.socialalarm.network;

import java.io.IOException;
import java.net.MalformedURLException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.android.Facebook;
import com.xtremelabs.socialalarm.R;
import com.xtremelabs.socialalarm.util.Config;

public class FacebookPostTask extends AsyncTask<Void, Void, String> {

	private ProgressDialog mProgressDialog;
	private Context mContext;
	private Bundle mParameters;

	public FacebookPostTask(Context context, Bundle parameters) {
		mContext = context;
		mParameters = parameters;

		mProgressDialog = new ProgressDialog(context);
		mProgressDialog.setMessage(context.getString(R.string.posting));
	}
	
	@Override
	protected void onPreExecute() {
		mProgressDialog.show();
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(Void... params) {
		String graphPath = "me/feed";
		String response = "error";

		Facebook facebook = new Facebook(Config.Facebook.APPLICATION_ID);
		
		final Config config = new Config(mContext);
        facebook.setAccessExpires(config.getFacebookAccessExpires());
        facebook.setAccessToken(config.getFacebookAccessToken());

		try {
			response = facebook.request(graphPath, mParameters, "POST");
		} catch (final MalformedURLException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}

		Log.i("Xtreme", response);
		return response;
	}
	
	@Override
	protected void onPostExecute(String result) {
		mProgressDialog.dismiss();
		Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
		
		super.onPostExecute(result);
	}
}
