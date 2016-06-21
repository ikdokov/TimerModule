package com.example.ivan.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import businesslogic.Project;
import db.ProjectDataSource;

public class ProjectDetailsActivityFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private static final String PROJECT_ID_EXTRA = "PROJECT_ID_EXTRA";
    private Project mProject;

    public static ProjectDetailsFragment newInstance(int projectId) {
        ProjectDetailsFragment fragment = new ProjectDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PROJECT_ID_EXTRA, projectId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public ProjectDetailsActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            int projectId = bundle.getInt(PROJECT_ID_EXTRA);
            if (projectId > 0) {
                ProjectDataSource dataSource = new ProjectDataSource(getActivity());
                dataSource.open();
                mProject = dataSource.getProject(projectId);
                dataSource.close();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_project_details, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
