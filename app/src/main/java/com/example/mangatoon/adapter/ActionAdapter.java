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
import com.example.mangatoon.model.Action;

import java.util.List;

public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.ActionViewHolder> {

    private Context mContext;
    private List<Action> list;

    public ActionAdapter(Context mContext, List<Action> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ActionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_action, parent, false);
        return new ActionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActionViewHolder holder, int position) {
        holder.setImageUrl(position);
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ActionViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvTitle;

        public ActionViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgAction);
            tvTitle = itemView.findViewById(R.id.tv_titleAction);
        }

        public void setImageUrl(int position) {
            Glide.with(mContext).load(list.get(position).getImageUrl()).into(imageView);
        }
    }
}
