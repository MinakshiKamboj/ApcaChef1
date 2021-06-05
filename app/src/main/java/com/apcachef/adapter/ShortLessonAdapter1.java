package com.apcachef.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apcachef.R;
import com.apcachef.interfaces.OnListItemClickListener;
import com.apcachef.model.shortLesson.RowsItem;
import com.bumptech.glide.Glide;

import java.util.List;

public class ShortLessonAdapter1 extends RecyclerView.Adapter<ShortLessonAdapter1.MyViewHolder> {
    List<RowsItem> shortLessonList;
    Context mContext;
    OnListItemClickListener onListItemClickListener;

    public ShortLessonAdapter1(Context mContext, List<RowsItem> shortLessonList, OnListItemClickListener onListItemClickListener) {
        this.mContext = mContext;
        this.shortLessonList = shortLessonList;
        this.onListItemClickListener = onListItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.short_lesson_item1, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_title.setText(shortLessonList.get(position).getTitle());
        holder.tv_des.setText(shortLessonList.get(position).getDescription());
        Glide.with(mContext).load(shortLessonList.get(position).getImg()).into(holder.card_image);

        holder.itemView.setOnClickListener(view -> onListItemClickListener.onItemClick(position, "short_lesson"));
    }


    @Override
    public int getItemCount() {
        return shortLessonList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_des;
        ImageView card_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_des = itemView.findViewById(R.id.tv_des);
            card_image = itemView.findViewById(R.id.card_image);
        }
    }
}
