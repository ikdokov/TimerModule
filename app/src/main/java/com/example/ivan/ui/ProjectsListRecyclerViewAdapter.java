package com.example.ivan.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivan.ui.databinding.ProjectListItemBinding;

import java.util.ArrayList;

import businesslogic.Project;

public class ProjectsListRecyclerViewAdapter extends RecyclerView.Adapter<ProjectsListRecyclerViewAdapter.CustomViewHolder> {

    private ArrayList<Project> mValues;
    private OnStartButtonClicked mListener;

    public ProjectsListRecyclerViewAdapter(ArrayList<Project> items, OnStartButtonClicked listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProjectListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.project_list_item, parent, false);
        return new CustomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        ProjectListItemBinding binding = holder.getProjectItemBinding();
        binding.setVariable(com.example.ivan.ui.BR.project, mValues.get(position));
        binding.setVariable(com.example.ivan.ui.BR.handler, this);
        binding.setVariable(com.example.ivan.ui.BR.position, position);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ProjectListItemBinding projectItemBinding;

        public CustomViewHolder(ProjectListItemBinding projectItemBinding) {
            super(projectItemBinding.getRoot());
            this.projectItemBinding = projectItemBinding;
        }

        public ProjectListItemBinding getProjectItemBinding() {
            return projectItemBinding;
        }
    }

    public void onStartButtonClick(View view) {
        if (mListener != null) {
            int position = (int) view.getTag();
            mListener.onStartButtonClicked(position);
        }
    }

    public interface OnStartButtonClicked {
        public void onStartButtonClicked(int position);
    }

    public void setProjects(ArrayList<Project> projects) {
        mValues = projects;
        notifyDataSetChanged();
    }
}