package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by idokov on 28/05/2016.
 */
public class TimerDataBaseHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Timer2.db";

    public TimerDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TimerContract.SQL_CREATE_PROJECT_TABLE);
        db.execSQL(TimerContract.SQL_CREATE_SESSION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 3) {
            db.execSQL(TimerContract.SQL_ALTER_TABLE_ADD_TIME_MODIFIED);
        }
    }
}
