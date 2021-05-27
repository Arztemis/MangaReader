package com.example.mangatoon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mangatoon.R;

import java.util.Collections;
import java.util.List;

public class DetailChapterApdater extends RecyclerView.Adapter<DetailChapterApdater.DetailChapterViewHolder> {

    private List<String> listImageUrl;
    private Context mContext;


    public DetailChapterApdater(List<String> listImageUrl, Context mContext) {

        this.listImageUrl = listImageUrl;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DetailChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_detail_chapter, parent, false);
        return new DetailChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailChapterViewHolder holder, int position) {

        holder.setImage(position);
    }

    @Override
    public int getItemCount() {
        return listImageUrl.size();
    }

    public class DetailChapterViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDetailChapter;

        public DetailChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDetailChapter = itemView.findViewById(R.id.imgDetailChapter);
        }

        public void setImage(int position) {
            Glide.with(mContext).load(listImageUrl.get(position))
                    .error(R.drawable.image5).into(imgDetailChapter);
        }
    }
}
