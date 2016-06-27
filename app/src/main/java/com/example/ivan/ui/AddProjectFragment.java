package com.example.ivan.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import businesslogic.Project;
import db.ProjectDataSource;

public class AddProjectFragment extends Fragment {

    private EditText nameEditText;
    private EditText descriptionEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_project_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameEditText = (EditText) view.findViewById(R.id.newProjectTitle);
        descriptionEditText = (EditText) view.findViewById(R.id.newProjectDescription);
        Button addButton = (Button) view.findViewById(R.id.addButton);
        addButton.setOnClickListener(addButtonClickListener);
    }

    View.OnClickListener addButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String title = nameEditText.getText().toString();

            if (title.isEmpty()) {
                Toast.makeText(getActivity(), "Empty title!", Toast.LENGTH_SHORT).show();
                return;
            }

            Project project = new Project(title.toString());

            String description = descriptionEditText.getText().toString();
            if (!description.isEmpty()) {
                project.setDescription(description.toString());
            }

            ProjectDataSource dataSource = new ProjectDataSource(getActivity());
            dataSource.insertProject(project);

            getActivity().onBackPressed();
        }
    };
}
