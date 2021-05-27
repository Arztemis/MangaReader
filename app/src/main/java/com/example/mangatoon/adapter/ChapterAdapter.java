package com.example.mangatoon.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangatoon.R;
import com.example.mangatoon.activity.DetailActivity;
import com.example.mangatoon.activity.DetailChapterActivity;
import com.example.mangatoon.model.Chapter;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

    private Context mContext;
    private List<Chapter> list;

    public ChapterAdapter(Context mContext, List<Chapter> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_chapter, parent, false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        holder.tvChapter.setText(list.get(position).getChapter());
        holder.tvTimeUpload.setText(list.get(position).getTimeUpload());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailChapterActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvChapter;
        TextView tvTimeUpload;
        RelativeLayout relativeLayout;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChapter = itemView.findViewById(R.id.tv_chapter);
            tvTimeUpload = itemView.findViewById(R.id.tv_timeUpload);
            relativeLayout = itemView.findViewById(R.id.layoutChapter);
        }
    }
}
