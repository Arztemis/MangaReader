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
import com.example.mangatoon.model.Fiction;

import java.util.List;

public class FictionAdapter extends RecyclerView.Adapter<FictionAdapter.FictionViewHolder> {

    private Context mContext;
    private List<Fiction> list;

    public FictionAdapter(Context mContext, List<Fiction> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public FictionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fiction, parent, false);
        return new FictionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FictionViewHolder holder, int position) {
        holder.setImageUrl(position);
        holder.tvTitle.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FictionViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvTitle;

        public FictionViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgFiction);
            tvTitle = itemView.findViewById(R.id.tv_titleFiction);
        }

        public void setImageUrl(int position) {
            Glide.with(mContext).load(list.get(position).getImageUrl()).into(imageView);
        }
    }
}
