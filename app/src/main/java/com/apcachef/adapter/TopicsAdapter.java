package com.apcachef.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apcachef.R;
import com.apcachef.interfaces.OnListItemClickListener;
import com.apcachef.model.home.TopicsItem;

import java.util.List;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.MyViewHolder> {
    List<TopicsItem> topics;
    Context mContext;
    OnListItemClickListener onListItemClickListener;

    public TopicsAdapter(Context mContext, List<TopicsItem> topics, OnListItemClickListener onListItemClickListener) {
        this.mContext = mContext;
        this.topics = topics;
        this.onListItemClickListener = onListItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.group_list_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_category.setText(Html.fromHtml(topics.get(position).getTitle()));
        holder.itemView.setOnClickListener(view -> onListItemClickListener.onItemClick(position, "pastry"));
    }


    @Override
    public int getItemCount() {
        return topics.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_category;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_category = itemView.findViewById(R.id.tv_category);
        }
    }
}
