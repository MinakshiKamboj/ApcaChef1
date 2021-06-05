package com.apcachef.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.apcachef.activity.MasterProgramsActivity;
import com.apcachef.interfaces.OnListItemClickListener;
import com.apcachef.model.MasterChefSeries.MasterChefSeriesItem;
import com.apcachef.model.home.MasterClassItem;
import com.bumptech.glide.Glide;

import java.util.List;

public class MasterChefSeriesAdapter  extends RecyclerView.Adapter<MasterChefSeriesAdapter.MyViewHolder> {
    private List<MasterChefSeriesItem> masterClassList;
    Context mContext;

    public MasterChefSeriesAdapter(Context mContext, List<MasterChefSeriesItem> masterClassList) {
        this.mContext = mContext;
        this.masterClassList = masterClassList;
    }

    @NonNull
    @Override
    public MasterChefSeriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.master_program_list_item, viewGroup, false);
        return new MasterChefSeriesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MasterChefSeriesAdapter.MyViewHolder holder, int position) {
        holder.tv_title.setText("  "+masterClassList.get(position).getTitle()+"  ");

        Glide.with(mContext).load(masterClassList.get(position).getImg()).into(holder.img_thumb);

        holder.linear_master.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, MasterProgramsActivity.class);
                i.putExtra("id",masterClassList.get(position).getId());
                i.putExtra("name",masterClassList.get(position).getChef_name());
                i.putExtra("title",masterClassList.get(position).getTitle());
                i.putExtra("video_url",masterClassList.get(position).getVideo_url());
                i.putExtra("desc",masterClassList.get(position).getDescription());
                i.putExtra("showMode", false);
                i.putExtra("modeID", "5");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });

 //       holder.itemView.setOnClickListener(view -> onListItemClickListener.onItemClick(position, "master"));
    }


    @Override
    public int getItemCount() {
        return masterClassList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_category, tv_title;
        ImageView image_play, img_thumb;
        RelativeLayout btn_viewProgram;
        LinearLayout linear_master;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_category = itemView.findViewById(R.id.tv_category);
            image_play = itemView.findViewById(R.id.image_play);
            img_thumb = itemView.findViewById(R.id.img_thumb);
            linear_master = itemView.findViewById(R.id.linear_master);
            tv_title = itemView.findViewById(R.id.tv_title);
            btn_viewProgram = itemView.findViewById(R.id.btn_viewProgram);
        }
    }
}
