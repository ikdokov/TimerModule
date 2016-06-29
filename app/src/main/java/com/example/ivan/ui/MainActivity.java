package com.example.ivan.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import businesslogic.Project;
import db.ProjectDataSource;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ProjectsListFragment.OnListFragmentInteractionListener, ProjectInfoFragment.OnFragmentInteractionListener {

    private static final String PROJECTS_FRAGMENT_TAG = "PROJECTS_FRAGMENT_TAG";
    private static final String NEW_PROJECT_FRAGMENT_TAG = "DIALOG_FRAGMENT";

    private static final int ADD_PROJECT_ACTIVITY_REQUEST = 2;

    private ProjectDataSource mProjectDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   startActivityForResult(new Intent(MainActivity.this, AddProjectActivity.class), ADD_PROJECT_ACTIVITY_REQUEST);
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        if (drawer != null) {
            drawer.setDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        showProjectListFragment(true);

        mProjectDataSource = new ProjectDataSource(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        ProjectsListFragment projectListFragment = (ProjectsListFragment) getSupportFragmentManager().findFragmentByTag(PROJECTS_FRAGMENT_TAG);
        if (projectListFragment != null) {
            projectListFragment.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.active_projects:
                showProjectListFragment(true);
                break;

            case R.id.archived_projects:
                showProjectListFragment(false);
                break;

            case R.id.nav_sessions:
                break;

            case R.id.nav_statistics:
                break;

            case R.id.nav_share:
                break;

            case R.id.nav_about:
                ProjectDataSource source = new ProjectDataSource(this);
                source.createDummyProjects();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    private void showProjectListFragment(boolean isActive) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ProjectsListFragment fragment = ProjectsListFragment.newInstance(isActive);
        ft.replace(R.id.activity_content, fragment, PROJECTS_FRAGMENT_TAG);
        ft.commit();
    }

    @Override
    public void onProjectFragmentInteraction(Project project) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == ADD_PROJECT_ACTIVITY_REQUEST) {
//            if (resultCode == RESULT_OK) {
//                Snackbar.make(get)
//            }
//        }
    }
}
