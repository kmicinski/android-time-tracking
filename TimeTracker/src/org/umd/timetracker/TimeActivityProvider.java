package org.umd.timetracker;

import org.umd.timetracker.TimeTracker.ActivityColumns;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

/**
 * Provides access to a database of time indexed data, the actual set of columns
 * is provided in {@link TimeTracker.ActivityColumns}
 */
public class TimeActivityProvider extends ContentProvider {

    private static final String TAG = "TimeActivityProvider";

    private static final String DATABASE_NAME = "activities.db";
    private static final int DATABASE_VERSION = 1;
    private static final String ACTIVITIES_TABLE_NAME = "activities";

    private static final UriMatcher sUriMatcher;

    private DatabaseHelper mOpenHelper;

    private static final int ACTIVITIES = 1;
    private static final int ACTIVITY_ID = 2;
	
    private static class DatabaseHelper extends SQLiteOpenHelper {
	DatabaseHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	    db.execSQL("CREATE TABLE " + ACTIVITIES_TABLE_NAME + " ("
		       + ActivityColumns._ID + " INTEGER PRIMARY KEY,"
		       + ActivityColumns.ACTIVITY_END_TIME
		       + " INTEGER," + ActivityColumns.ACTIVITY_NAME
		       + " VARCHAR(200),"
		       + ActivityColumns.ACTIVITY_NOTE + " TEXT,"
		       + ActivityColumns.ACTIVITY_START_TIME
		       + " INTEGER" + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(TAG, "Upgrading database...");
	    db.execSQL("DROP TABLE IF EXISTS notes");
	    onCreate(db);
	}
    }

    @Override
    public int delete(Uri arg0, String arg1, String[] arg2) {
	
	return 0;
    }

    @Override
    public String getType(Uri uri) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public boolean onCreate() {
	mOpenHelper = new DatabaseHelper(getContext());

	return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
		      String[] selectionArgs) {
	// TODO Auto-generated method stub
	return 0;
    }

    static {
	sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	sUriMatcher.addURI(TimeTracker.AUTHORITY, "activities", ACTIVITIES);
	sUriMatcher.addURI(TimeTracker.AUTHORITY, "activities/#", ACTIVITY_ID);
    }
    
}
