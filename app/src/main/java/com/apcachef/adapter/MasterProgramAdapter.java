package com.apcachef.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apcachef.R;
import com.apcachef.interfaces.OnListItemClickListener;
import com.apcachef.model.home.MasterClassItem;
import com.bumptech.glide.Glide;

import java.util.List;

public class MasterProgramAdapter extends RecyclerView.Adapter<MasterProgramAdapter.MyViewHolder> {
    private List<MasterClassItem> masterClassList;
    Context mContext;
    OnListItemClickListener onListItemClickListener;

    public MasterProgramAdapter(Context mContext, List<MasterClassItem> masterClassList, OnListItemClickListener onListItemClickListener) {
        this.mContext = mContext;
        this.masterClassList = masterClassList;
        this.onListItemClickListener = onListItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.master_program_list_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.linear_name.setVisibility(View.VISIBLE);
        holder.tv_category.setText("  "+masterClassList.get(position).getChefName()+"  ");

        Glide.with(mContext).load(masterClassList.get(position).getImg()).into(holder.img_thumb);

        holder.itemView.setOnClickListener(view -> onListItemClickListener.onItemClick(position, "master"));
    }


    @Override
    public int getItemCount() {
        return masterClassList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_category;
        ImageView image_play, img_thumb;
        RelativeLayout btn_viewProgram;
        LinearLayout linear_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_category = itemView.findViewById(R.id.tv_category);
            image_play = itemView.findViewById(R.id.image_play);
            img_thumb = itemView.findViewById(R.id.img_thumb);
            linear_name = itemView.findViewById(R.id.linear_name);
            btn_viewProgram = itemView.findViewById(R.id.btn_viewProgram);
        }
    }
}


