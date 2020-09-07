package com.codexo.gads2020leaderboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codexo.gads2020leaderboard.R;
import com.codexo.gads2020leaderboard.model.LearningLeader;

import java.util.List;

public class LearningLeadersRvAdapter extends RecyclerView.Adapter<LearningLeadersRvAdapter.ViewHolder> {

    private Context context;
    private List<LearningLeader> items;

    public LearningLeadersRvAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<LearningLeader> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.learning_leaders_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LearningLeader item = items.get(position);
        holder.txtTitle.setText(item.getName());
        holder.txtSubtitle.setText(context.getString(R.string.learning_leader_details, item.getHours(), item.getCountry()));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle, txtSubtitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtSubtitle = itemView.findViewById(R.id.txtSubtitle);
        }
    }
}
