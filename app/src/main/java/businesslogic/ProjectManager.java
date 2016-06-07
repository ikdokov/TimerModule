package businesslogic;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import db.TimerDataBaseHelper;

/**
 * Created by idokov on 18/05/2016.
 */
public class ProjectManager {

    private static Context mContext;
    private Session currentSession;

    private ArrayList<Project> mCachedProjects;

    private static ProjectManager ourInstance = new ProjectManager();

    public static ProjectManager getInstance(Context context) {
        mContext = context;

        return ourInstance;
    }

    private ProjectManager() {
    }

    public ArrayList<Project> getActiveProjects() {
        TimerDataBaseHelper helper = new TimerDataBaseHelper(mContext);
        Cursor cursor = helper.getActiveProjectCursor();

        ArrayList<Project> projects = new ArrayList<Project>();
        projects.add(new Project("Project1"));
        projects.add(new Project("Project2"));
        mCachedProjects = projects;
        return mCachedProjects;
    }

    public ArrayList<Project> getArchivedProjects() {
        // TODO: 07/06/2016 get archived project from db
        return null;
    }

    public void createProject(String title) {
        Project project = new Project(title);
        mCachedProjects.add(project);
        // TODO: 07/06/2016 add project to db
    }

    public void deleteProject(int projectId) {

    }

    public Project getProjectById(int projectId) {
        return null;
    }

    public void startButtonClicked(Project project) {
    }

    public boolean startProject(int projectId) {
        return mCachedProjects.get(projectId).startProject();
    }

    public boolean stopProject(int projectId) {
        return mCachedProjects.get(projectId).stopProject();
    }




}
