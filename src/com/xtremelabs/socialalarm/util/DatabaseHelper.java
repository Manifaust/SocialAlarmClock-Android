package com.xtremelabs.socialalarm.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.xtremelabs.socialalarm.model.Alarm;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "Snoozey";

    /* This version number needs to be incremented whenever the structure changes,
     * along with appropriate modifications to onCreate() and onUpgrade().
     */
    private static final int DB_VERSION = 1;

    private static final String MY_ALARM_TABLE = "alarms";

    public DatabaseHelper(final Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db)
    {
        synchronized (getClass())
        {
            
            db.execSQL("CREATE TABLE " + MY_ALARM_TABLE + '('
                    + AlarmColumns._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + AlarmColumns.NAME + " TEXT NOT NULL, "
                    + AlarmColumns.TIME + " LONG NOT NULL, "
                    + AlarmColumns.ENABLED + " TEXT NOT NULL);");
        }
    }

    public static synchronized List<Alarm> getAllAlarms(final SQLiteDatabase db)
    {
        final String[] columns = { AlarmColumns._ID, AlarmColumns.NAME, AlarmColumns.TIME, AlarmColumns.ENABLED };

        final Cursor cursor = db.query(MY_ALARM_TABLE, columns, null, null, null, null, null);

        final List<Alarm> tickets = new ArrayList<Alarm>();

        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                tickets.add(getAlarmFromCursor(cursor));
            } while (cursor.moveToNext());
        }

        if (cursor != null)
        {
            cursor.close();
        }

        return tickets;
    }
    
    public static synchronized Alarm getAlarm(final SQLiteDatabase db, final long ticketId)
    {
        final String[] columns = { AlarmColumns._ID, AlarmColumns.NAME, AlarmColumns.TIME, AlarmColumns.ENABLED };

        final Cursor cursor = db.query(MY_ALARM_TABLE, columns, AlarmColumns._ID + " = ?",
                new String[] { String.valueOf(ticketId) }, null, null, null);

        if (cursor != null && cursor.moveToFirst())
        {
            return getAlarmFromCursor(cursor);
        }

        return null;
    }

	private static Alarm getAlarmFromCursor(final Cursor cursor) {
		final long id = cursor.getLong(cursor.getColumnIndex(AlarmColumns._ID));
		final String name = cursor.getString(cursor.getColumnIndex(AlarmColumns.NAME));
		final long time = cursor.getLong(cursor.getColumnIndex(AlarmColumns.TIME));
		final String enabled = cursor.getString(cursor.getColumnIndex(AlarmColumns.TIME));
		
		return new Alarm(name, time, Boolean.valueOf(enabled), id);
	}
    
    public static synchronized long addAlarm(final SQLiteDatabase db, final Alarm alarm)
    {
        final ContentValues values = new ContentValues();
        values.put(AlarmColumns.NAME, alarm.getName());
        values.put(AlarmColumns.TIME, alarm.getTime());
        values.put(AlarmColumns.ENABLED, String.valueOf(alarm.isEnabled()));

        return db.insert(MY_ALARM_TABLE, null, values);
    }
    

    public static synchronized void removeAllAlarms(final SQLiteDatabase db)
    {
        db.delete(MY_ALARM_TABLE, null, null);
    }

    
    private interface AlarmColumns extends BaseColumns
    {
    	String _ID = "id";
        String NAME = "name";
        String TIME = "time";
        String ENABLED = "enabled";
    }


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
