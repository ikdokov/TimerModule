package com.example.ivan.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import businesslogic.Project;
import businesslogic.TimerBusinessLogic;
import db.ProjectDataSource;
import listener.OnListItemClickListener;

public class ProjectsListFragment extends Fragment implements OnListItemClickListener {

    private static final String IS_ACTIVE = "IS_ACTIVE";

    private static final String DETAILS_FRAGMENT_TAG = "DETAILS_FRAGMENT_TAG";

    private boolean mIsActive;

    private OnListFragmentInteractionListener mListener;
    private TimerBusinessLogic mTimerBusinessLogic;
    private ProjectsListRecyclerViewAdapter mAdapter;

    private ArrayList<Project> mProjects = new ArrayList<>();
    private ProjectDataSource mProjectDataSource;

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

        if (getArguments() != null) {
            mIsActive = getArguments().getBoolean(IS_ACTIVE);
        }

        mProjectDataSource = new ProjectDataSource(getActivity());

        if (mIsActive) {
            mProjects = mProjectDataSource.getActiveProjects();
            Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            if (toolbar != null) {
                toolbar.setTitle(R.string.active_projects);
            }
        } else {
            mProjects = mProjectDataSource.getArchivedProjects();
            Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            if (toolbar != null) {
                toolbar.setTitle(R.string.archived_projects);
            }
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
    public void onItemClicked(int position) {
        showDetailsProjectFragment(mProjects.get(position).getId());
    }

    public void notifyDataSetChanged() {
        if (mAdapter != null) {
            if (mIsActive) {
                mProjects = mProjectDataSource.getActiveProjects();
            } else {
                mProjects = mProjectDataSource.getArchivedProjects();
            }

            mAdapter.setProjects(mProjects);
            mAdapter.notifyDataSetChanged();
        }
    }

    public interface OnListFragmentInteractionListener {
        void onProjectFragmentInteraction(Project project);
    }

    private void showDetailsProjectFragment(long projectId) {
        Intent intent = new Intent(getActivity(), ProjectDetailsActivity.class);
        intent.putExtra(ProjectDetailsActivity.PROJECT_ID_EXTRA, projectId);
        getActivity().startActivity(intent);
    }

//    class GetProjectTask extends AsyncTask<Void, Void, ArrayList<Project>> {
//
//        @Override
//        protected ArrayList<Project> doInBackground(Void... voids) {
//            ProjectDataSource source = new ProjectDataSource(getContext());
//            source.open();
////            source.createDummyProjects();
//            ArrayList<Project> projects = source.getAllProjects();
//            source.close();
//            return projects;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<Project> projects) {
//            mProjects = projects;
//            mAdapter.setProjects(mProjects);
//        }
//    }

}