package com.xtremelabs.socialalarm.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;

public class Config {
	
	private static final String RING_TONE = "ring_tone";
	
	private Context mContext;
	
	public interface Facebook
    {
        String APPLICATION_ID = "108984012550152";
        String APP_SECRET = "8616cc121111c56b67458c3f838b2425";
        
        String[] PERMISSIONS = { "publish_stream" };
        
        String PUBLISH_WALL_ACTION = "stream.publish";
        
        String ACCESS_EXPIRES = "accessExpires";
        String ACCESS_TOKEN = "accessToken";
    }
	
	public Config(final Context context)
    {
        super();
        mContext = context;
    }
	
	public long getFacebookAccessExpires()
    {
        return getLongPreference(Facebook.ACCESS_EXPIRES, 0);
    }
	
	private long getLongPreference(final String key, final long defValue)
    {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);

        return settings.getLong(key, defValue);
    }
	
	public String getFacebookAccessToken()
    {
        return getStringPreference(Facebook.ACCESS_TOKEN, null);
    }
	
	private String getStringPreference(final String key, final String defValue)
    {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);

        return settings.getString(key, defValue);
    }
	
	public void setFacebookAccessExpires(final long accessExpires)
    {
        putLongPreference(Facebook.ACCESS_EXPIRES, accessExpires);
    }
	
	private void putLongPreference(final String key, final long value)
    {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
        final SharedPreferences.Editor editor = settings.edit();

        editor.putLong(key, value);

        editor.commit();
    }
	
	public void setFacebookAccessToken(final String accessToken)
    {
        putStringPreference(Facebook.ACCESS_TOKEN, accessToken);
    }
	
	private void putStringPreference(final String key, final String value)
    {
        final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
        final SharedPreferences.Editor editor = settings.edit();

        editor.putString(key, value);

        editor.commit();
    }
	
	public void removeFacebookToken()
    {
    	final SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
        final SharedPreferences.Editor editor = settings.edit();
        
        editor.remove(Facebook.ACCESS_EXPIRES);
        editor.remove(Facebook.ACCESS_TOKEN);
        
        editor.commit();
    }
	
	public void putRingtonePreference(Uri ringTone){
		putStringPreference(RING_TONE, ringTone.toString());
	}
	
	public Uri getRingtonePreference(){
		String uriString = getStringPreference(RING_TONE, null);
		
		if(uriString != null){
			return Uri.parse(uriString);
		} else {
			return null;
		}
	}
}
