package com.example.ivan.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import businesslogic.Project;
import businesslogic.TimerBusinessLogic;

public class ProjectsListFragment extends Fragment implements ProjectsListRecyclerViewAdapter.OnStartButtonClicked {

    private static final String IS_ACTIVE = "IS_ACTIVE";

    private static final String DETAILS_FRAGMENT_TAG = "DETAILS_FRAGMENT_TAG";

    private boolean mIsActive;

    private OnListFragmentInteractionListener mListener;
    private TimerBusinessLogic mTimerBusinessLogic;
    private ProjectsListRecyclerViewAdapter mAdapter;

    private ArrayList<Project> mProjects = new ArrayList<>();

    public ProjectsListFragment() {
    }

    public static ProjectsListFragment newInstance(boolean isActive) {
        ProjectsListFragment fragment = new ProjectsListFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_ACTIVE, isActive);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTimerBusinessLogic = TimerBusinessLogic.getInstance(getContext());

        if (getArguments() != null) {
            mIsActive = getArguments().getBoolean(IS_ACTIVE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projects_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            mAdapter = new ProjectsListRecyclerViewAdapter(mProjects, this);
            recyclerView.setAdapter(mAdapter);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStartButtonClicked(int position) {
//        projectsManager.getActiveProjects().get(position).startStopProject();
        mAdapter.notifyItemChanged(position);
    }

    public interface OnListFragmentInteractionListener {
        void onProjectFragmentInteraction(Project project);
    }

    private void showCurrentProjectFragment() {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ProjectDetailsFragment fragment = new ProjectDetailsFragment();
        ft.replace(R.id.activity_content, fragment, DETAILS_FRAGMENT_TAG);
        ft.commit();
    }

    class GetProjectTask extends AsyncTask<Void, Void, ArrayList<Project>> {

        @Override
        protected ArrayList<Project> doInBackground(Void... voids) {
            return mTimerBusinessLogic.getAllProjects();
        }

        @Override
        protected void onPostExecute(ArrayList<Project> projects) {
            mProjects = projects;
            mAdapter.setProjects(mProjects);
        }
    }
}