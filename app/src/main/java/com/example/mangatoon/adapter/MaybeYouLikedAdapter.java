package com.example.mangatoon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mangatoon.R;
import com.example.mangatoon.model.MaybeYouLiked;

import java.util.List;

public class MaybeYouLikedAdapter extends RecyclerView.Adapter<MaybeYouLikedAdapter.MaybeYouLikedViewHolder> {

    private List<MaybeYouLiked> list;
    private Context mContext;

    public MaybeYouLikedAdapter(List<MaybeYouLiked> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MaybeYouLikedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).
                inflate(R.layout.item_maybeyouliked, parent, false);
        return new MaybeYouLikedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaybeYouLikedViewHolder holder, int position) {
        holder.setImageUrl(position);
        holder.tvTitle.setText(list.get(position).getTilte());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MaybeYouLikedViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvTitle;

        public MaybeYouLikedViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagemaybe);
            tvTitle = itemView.findViewById(R.id.tvTiltle);
        }

        public void setImageUrl(int position) {
            Glide.with(mContext).load(list.get(position).getImageUrl()).into(imageView);
        }
    }
}
