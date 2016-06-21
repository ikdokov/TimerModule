package db;

import android.provider.BaseColumns;

public class TimerContract {

    private TimerContract(){}

    /**
     * Created by idokov on 28/05/2016.
     */

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_PROJECT_TABLE =
            "CREATE TABLE " + ProjectEntry.TABLE_NAME + " (" +
                    ProjectEntry._ID + " INTEGER PRIMARY KEY, " +
                    ProjectEntry.COLUMN_NAME_PROJECT_TITLE + TEXT_TYPE + COMMA_SEP +
                    ProjectEntry.COLUMN_NAME_PROJECT_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    ProjectEntry.COLUMN_NAME_IS_ARCHIVED + INT_TYPE + COMMA_SEP +
                    ProjectEntry.COLUMN_NAME_PROJECT_NOTES + TEXT_TYPE + COMMA_SEP +
                    ProjectEntry.COLUMN_NAME_PROJECT_PAYMENT_PER_HOUR + TEXT_TYPE + COMMA_SEP +
                    ProjectEntry.COLUMN_NAME_TIME_MODIFIED + TEXT_TYPE + COMMA_SEP +
                    ProjectEntry.COLUMN_NAME_PROJECT_TOTAL_PAYMENT + TEXT_TYPE + ")";

    public static final String SQL_DELETE_PROJECT_TABLE =
            "DROP TABLE IF EXISTS " + ProjectEntry.TABLE_NAME;

    public static final String SQL_CREATE_SESSION_TABLE =
            "CREATE TABLE " + SessionEntry.TABLE_NAME + " (" +
                    SessionEntry._ID + " INTEGER PRIMARY KEY, " +
                    SessionEntry.COLUMN_NAME_SESSION_START_TIME + TEXT_TYPE + COMMA_SEP +
                    SessionEntry.COLUMN_NAME_SESSION_END_TIME + TEXT_TYPE + COMMA_SEP +
                    SessionEntry.COLUMN_NAME_SESSION_NOTE + TEXT_TYPE + COMMA_SEP +
                    SessionEntry.COLUMN_NAME_PROJECT_ID + TEXT_TYPE + COMMA_SEP +
                    "FOREIGN KEY (" + SessionEntry.COLUMN_NAME_PROJECT_ID + ") REFERENCES " +
                    ProjectEntry.TABLE_NAME + "(" + ProjectEntry.COLUMN_NAME_PROJECT_ID + "));";

    public static final String SQL_DELETE_SESSION_TABLE =
            "DROP TABLE IF EXISTS " + SessionEntry.TABLE_NAME;

    public static final String SQL_ALTER_TABLE_ADD_TIME_MODIFIED = "ALTER TABLE " + ProjectEntry.TABLE_NAME + " ADD COLUMN " + ProjectEntry.COLUMN_NAME_TIME_MODIFIED + " string;";

    public static abstract class ProjectEntry implements BaseColumns {
        public static final String TABLE_NAME = "project";
        public static final String COLUMN_NAME_PROJECT_ID = "poject_id";
        public static final String COLUMN_NAME_PROJECT_TITLE = "project_title";
        public static final String COLUMN_NAME_PROJECT_DESCRIPTION = "project_description";
        public static final String COLUMN_NAME_PROJECT_NOTES = "project_notes";
        public static final String COLUMN_NAME_IS_ARCHIVED = "is_archived";
        public static final String COLUMN_NAME_PROJECT_PAYMENT_PER_HOUR = "payment_per_hour";
        public static final String COLUMN_NAME_PROJECT_TOTAL_PAYMENT = "payment_total";
        public static final String COLUMN_NAME_TIME_MODIFIED = "time_modified";
    }

    public static abstract class SessionEntry implements BaseColumns {
        public static final String TABLE_NAME = "session";
        public static final String COLUMN_NAME_SESSION_ID = "session_id";
        public static final String COLUMN_NAME_SESSION_START_TIME = "session_start_time";
        public static final String COLUMN_NAME_SESSION_END_TIME = "session_end_time";
        public static final String COLUMN_NAME_SESSION_NOTE = "session_note";
        public static final String COLUMN_NAME_PROJECT_ID = "project_id";
    }
}