package com.example.project_samsung.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_samsung.FirbaseClass.Courses;
import com.example.project_samsung.FirbaseClass.Forums;
import com.example.project_samsung.R;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder> {

    private List<Courses> coursesList;

    public CoursesAdapter(List<Courses> coursesList) {
        this.coursesList = coursesList;
    }

    @NonNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_content, parent, false);
        return new CoursesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesViewHolder holder, int position) {
        Courses course = coursesList.get(position);
        holder.textViewCourseName.setText(course.name);
        holder.textViewPriceCourse.setText(course.price);

    }

    @Override
    public int getItemCount() {
        return coursesList.size();
    }

    public static class CoursesViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCourseName, textViewPriceCourse;

        public CoursesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCourseName = itemView.findViewById(R.id.textViewCourseName);
            textViewPriceCourse = itemView.findViewById(R.id.textViewPriceCourse);
        }
    }

    public void updateData(List<Courses> newCourses) {
        this.coursesList = newCourses;
        notifyDataSetChanged();
    }
}