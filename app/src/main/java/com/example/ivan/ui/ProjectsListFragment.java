package com.example.ivan.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import businesslogic.Project;
import businesslogic.ProjectManager;

public class ProjectsListFragment extends Fragment implements ProjectsListRecyclerViewAdapter.OnStartButtonClicked {

    private OnListFragmentInteractionListener mListener;
    private ProjectManager projectsManager;
    private ProjectsListRecyclerViewAdapter mAdapter;

    public ProjectsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        projectsManager = ProjectManager.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projects_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            mAdapter = new ProjectsListRecyclerViewAdapter(projectsManager.getProjects(), this);
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
//        projectsManager.getProjects().get(position).startStopProject();
        mAdapter.notifyItemChanged(position);
    }

    public interface OnListFragmentInteractionListener {
        void onProjectFragmentInteraction(Project project);
    }
}