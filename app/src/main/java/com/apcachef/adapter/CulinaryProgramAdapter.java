package com.apcachef.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apcachef.R;
import com.apcachef.interfaces.OnListItemClickListener;
import com.apcachef.model.home.SubsItem;
import com.bumptech.glide.Glide;

import java.util.List;

public class CulinaryProgramAdapter extends RecyclerView.Adapter<CulinaryProgramAdapter.MyViewHolder> {
    List<SubsItem> culinaryProgramList;
    Context mContext;
    OnListItemClickListener onListItemClickListener;

    public CulinaryProgramAdapter(Context mContext, List<SubsItem> culinaryProgramList, OnListItemClickListener onListItemClickListener) {
        this.mContext = mContext;
        this.culinaryProgramList = culinaryProgramList;
        this.onListItemClickListener = onListItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.video_list_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_category.setText("  "+culinaryProgramList.get(position).getTitle()+"  ");

        //Picasso.get().load(culinaryProgramList.get(position).getImg()).placeholder(R.drawable.image_thumb).into(holder.img_thumb);
        Glide.with(mContext).load(culinaryProgramList.get(position).getImg()).into(holder.img_thumb);
       /* holder.image_play.setOnClickListener(view -> {
            onListItemClickListener.onItemClick(position, "homeCulinary");
        });*/

        holder.itemView.setOnClickListener(view -> onListItemClickListener.onItemClick(position, "culinary"));
    }


    @Override
    public int getItemCount() {
        return culinaryProgramList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_category;
        ImageView image_play, img_thumb;
        RelativeLayout btn_viewProgram;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_category = itemView.findViewById(R.id.tv_category);
            image_play = itemView.findViewById(R.id.image_play);
            img_thumb = itemView.findViewById(R.id.img_thumb);
            btn_viewProgram = itemView.findViewById(R.id.btn_viewProgram);
        }
    }
}
