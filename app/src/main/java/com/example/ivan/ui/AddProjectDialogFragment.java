package com.example.ivan.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import businesslogic.Project;
import db.ProjectDataSource;

public class AddProjectDialogFragment extends DialogFragment {

    private EditText nameEditText;
    private EditText descriptionEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_add_project, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameEditText = (EditText) view.findViewById(R.id.newProjectTitle);
        descriptionEditText = (EditText) view.findViewById(R.id.newProjectDescription);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_project_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_done) {
            String title = nameEditText.getText().toString();

            if (title.isEmpty()) {
                Snackbar.make(getView(), R.string.empty_title_add_project, Snackbar.LENGTH_LONG).show();
                return true;
            }

            Project project = new Project();
            project.setTitle(title.toString());

            String description = descriptionEditText.getText().toString();
            if (!description.isEmpty()) {
                project.setDescription(description.toString());
            }

            ProjectDataSource dataSource = new ProjectDataSource(getActivity());
            dataSource.insertProject(project);

            getActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
