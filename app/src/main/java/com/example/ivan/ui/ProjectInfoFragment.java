package com.example.ivan.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivan.ui.databinding.FragmentProjectInfoBinding;

import businesslogic.Project;
import db.ProjectDataSource;

public class ProjectInfoFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private Project mProject;

    public static ProjectInfoFragment newInstance(long projectId) {
        ProjectInfoFragment fragment = new ProjectInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(ProjectInfoActivity.PROJECT_ID_EXTRA, projectId);
        fragment.setArguments(bundle);
        return fragment;
    }

    public ProjectInfoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            long projectId = bundle.getLong(ProjectInfoActivity.PROJECT_ID_EXTRA);
            if (projectId > 0) {
                ProjectDataSource dataSource = new ProjectDataSource(getActivity());
                mProject = dataSource.getProject(projectId);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentProjectInfoBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_info, container, false);
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
