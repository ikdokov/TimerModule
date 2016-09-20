package com.example.ivan.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class ProjectInfoActivity extends AppCompatActivity implements ProjectInfoFragment.OnFragmentInteractionListener {

    public static final String PROJECT_ID_EXTRA = "PROJECT_ID_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            long projectId = extras.getLong(PROJECT_ID_EXTRA);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            ProjectInfoFragment fragment = ProjectInfoFragment.newInstance(projectId);
            fragmentTransaction.replace(R.id.project_info_content, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
