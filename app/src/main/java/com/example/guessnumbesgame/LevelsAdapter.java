package com.example.guessnumbesgame;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LevelsAdapter extends RecyclerView.Adapter<LevelsAdapter.LevelViewHolder> {

    private List<Level> levels;
    private OnItemClickListener onItemClickListener;

    public LevelsAdapter(List<Level> levels, OnItemClickListener onItemClickListener) {
        this.levels = levels;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public LevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_item, parent, false);
        return new LevelViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelViewHolder holder, int position) {
        Level level = levels.get(position);
        holder.levelTextView.setText("Level " + level.getLevelNumber());

        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(level.getLevelNumber()));
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    static class LevelViewHolder extends RecyclerView.ViewHolder {
        TextView levelTextView;

        LevelViewHolder(View itemView) {
            super(itemView);
            levelTextView = itemView.findViewById(R.id.levelTextView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int levelNumber);
    }
}