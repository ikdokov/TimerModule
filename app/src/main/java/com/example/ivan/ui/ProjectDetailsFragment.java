package com.example.ivan.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivan.ui.databinding.FragmentProjectDetailsBinding;

import businesslogic.Project;
import db.ProjectDataSource;

public class ProjectDetailsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private Project mProject;

    public static ProjectDetailsFragment newInstance(long projectId) {
        ProjectDetailsFragment fragment = new ProjectDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(ProjectDetailsActivity.PROJECT_ID_EXTRA, projectId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public ProjectDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            long projectId = bundle.getLong(ProjectDetailsActivity.PROJECT_ID_EXTRA);
            if (projectId > 0) {
                ProjectDataSource dataSource = new ProjectDataSource(getActivity());
                mProject = dataSource.getProject(projectId);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentProjectDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_details, container, false);
        binding.setProject(mProject);
        return binding.getRoot();
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
