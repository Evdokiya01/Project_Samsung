package com.example.project_samsung.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_samsung.FirbaseClass.Forums;
import com.example.project_samsung.R;

import java.util.List;

public class ForumsAdapter extends RecyclerView.Adapter<ForumsAdapter.ForumsViewHolder> {

    private List<Forums> forumsList;

    public ForumsAdapter(List<Forums> forumsList) {
        this.forumsList = forumsList;
    }

    @NonNull
    @Override
    public ForumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forums_content, parent, false);
        return new ForumsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumsViewHolder holder, int position) {
        Forums forum = forumsList.get(position);
        holder.textTopic.setText(forum.topic);
        holder.textContent.setText(forum.content);
        holder.textLogin.setText(forum.login);

    }

    @Override
    public int getItemCount() {
        return forumsList.size();
    }

    public static class ForumsViewHolder extends RecyclerView.ViewHolder {
        TextView textTopic, textContent, textLogin;

        public ForumsViewHolder(@NonNull View itemView) {
            super(itemView);
            textTopic = itemView.findViewById(R.id.textName);
            textContent = itemView.findViewById(R.id.textContent);
            textLogin = itemView.findViewById(R.id.textLoginMarket);
        }
    }

    public void updateData(List<Forums> newForums) {
        this.forumsList = newForums;
        notifyDataSetChanged();
    }
}