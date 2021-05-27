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
import com.example.mangatoon.model.Horror;

import java.util.List;

public class HorrorAdapter extends RecyclerView.Adapter<HorrorAdapter.HorrorViewHolder> {

    private Context mContext;
    private List<Horror> list;

    public HorrorAdapter(Context mContext, List<Horror> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public HorrorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_horror, parent, false);
        return new HorrorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorrorViewHolder holder, int position) {
        holder.setImageUrl(position);
        holder.tvTitle.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HorrorViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvTitle;

        public HorrorViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgHorror);
            tvTitle = itemView.findViewById(R.id.tv_titleHorror);
        }

        public void setImageUrl(int position) {
            Glide.with(mContext).load(list.get(position).getImageUrl()).into(imageView);
        }
    }
}
