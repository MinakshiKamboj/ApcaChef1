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
import com.apcachef.model.myCourses.SubsItem;
import com.bumptech.glide.Glide;

import java.util.List;

public class PastryProgramCourseAdapter extends RecyclerView.Adapter<PastryProgramCourseAdapter.MyViewHolder> {
    List<SubsItem> onlineProgramList;
    Context mContext;
    OnListItemClickListener onListItemClickListener;

    public PastryProgramCourseAdapter(Context mContext, List<SubsItem> onlineProgramList, OnListItemClickListener onListItemClickListener) {
        this.mContext = mContext;
        this.onlineProgramList = onlineProgramList;
        this.onListItemClickListener = onListItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.course_list_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
  //      holder.tv_title.setText(onlineProgramList.get(position).getTitle());
        holder.tv_title.setText(onlineProgramList.get(position).getShortDescription());
        Glide.with(mContext).load(onlineProgramList.get(position).getImg()).into(holder.card_image);
        holder.itemView.setOnClickListener(view -> onListItemClickListener.onItemClick(position, "pastry"));
        holder.img_arrow.setVisibility(View.GONE);

    }


    @Override
    public int getItemCount() {
        return onlineProgramList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView card_image,img_arrow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            card_image = itemView.findViewById(R.id.card_image);
            img_arrow = itemView.findViewById(R.id.img_arrow);
        }
    }
}
