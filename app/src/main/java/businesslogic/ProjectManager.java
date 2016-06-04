package businesslogic;

import java.util.ArrayList;

/**
 * Created by idokov on 18/05/2016.
 */
public class ProjectManager {

    private Session currentSession;

    private ArrayList<Project> mCachedProjects;

    private static ProjectManager ourInstance = new ProjectManager();

    public static ProjectManager getInstance() {
        return ourInstance;
    }

    private ProjectManager() {
    }

    // TODO: 18/05/2016 implement data saving
    public ArrayList<Project> getProjects() {
        ArrayList<Project> projects = new ArrayList<Project>();
        projects.add(new Project("Project1"));
        projects.add(new Project("Project2"));
        mCachedProjects = projects;
        return mCachedProjects;
    }

    public void createProject(String title) {
        Project project = new Project(title);
        mCachedProjects.add(project);
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
