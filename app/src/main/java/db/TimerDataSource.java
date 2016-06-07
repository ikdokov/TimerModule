package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

import businesslogic.Project;

/**
 * Created by idokov on 08/06/2016.
 */
public class TimerDataSource {

    private SQLiteDatabase mDatabase;
    private TimerDataBaseHelper mDBHelper;

    public TimerDataSource(Context context) {
        mDBHelper = new TimerDataBaseHelper(context);
    }

    public void open() throws SQLiteException {
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void close() {
        mDBHelper.close();
    }

    public void createProject(Project project) {

    }

    public void deleteProject(Project project) {

    }

    public ArrayList<Project> getActiveProjects() {
        String[] projection = {
                TimerContract.ProjectEntry._ID,
                TimerContract.ProjectEntry.COLUMN_NAME_PROJECT_NAME,
                TimerContract.ProjectEntry.COLUMN_NAME_IS_ARCHIVED };

        return null;
    }

    public ArrayList<Project> getArchivedProjects() {
        return null;
    }

    public void archiveProject(Project project) {

    }


}
