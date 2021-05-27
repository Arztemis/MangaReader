package com.example.mangatoon.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mangatoon.R;
import com.example.mangatoon.activity.MangaActivity;
import com.example.mangatoon.model.Banner;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.List;

public class BannerApdapter extends RecyclerView.Adapter<BannerApdapter.BannerViewHolder> {

    private final List<Banner> list;
    private final Context mContext;

    public BannerApdapter(List<Banner> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {


        Banner banner = list.get(position);
        holder.setImage(position);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, MangaActivity.class);
            intent.putExtra("imageUrl", banner.getImage());
            intent.putExtra("title", banner.getTitle());
            intent.putExtra("newChapter", banner.getNewChapter());
            intent.putExtra("desc", banner.getDesc());
            intent.putExtra("author", banner.getAuthor());
            intent.putExtra("type", banner.getType());
            intent.putExtra("countView", banner.getCountView());
            intent.putExtra("transTeam", banner.getTransTeam());
            intent.putExtra("statusUpdate", banner.getStatusUpdate());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {

        private final KenBurnsView kenBurnsView;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            kenBurnsView = itemView.findViewById(R.id.kenburnView);
        }

        public void setImage(int position) {
            Glide.with(mContext).load(list.get(position).getImage()).placeholder(R.drawable.image2).into(kenBurnsView);
        }
    }

}
