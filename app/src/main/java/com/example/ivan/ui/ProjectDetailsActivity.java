package com.example.ivan.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class ProjectDetailsActivity extends AppCompatActivity implements ProjectDetailsFragment.OnFragmentInteractionListener {

    public static final String PROJECT_ID_EXTRA = "PROJECT_ID_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            long projectId = extras.getLong(PROJECT_ID_EXTRA);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            ProjectDetailsFragment fragment = ProjectDetailsFragment.newInstance(projectId);
            fragmentTransaction.replace(R.id.content_project_details, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
