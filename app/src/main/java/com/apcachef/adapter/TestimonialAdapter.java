package com.apcachef.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apcachef.R;
import com.apcachef.model.home.TestimonialItem;
import com.bumptech.glide.Glide;

import java.util.List;

public class TestimonialAdapter extends RecyclerView.Adapter<TestimonialAdapter.MyViewHolder> {
    private Context mContext;
    private List<TestimonialItem> testimonialList;
    ProgressBar progressBar;

    public TestimonialAdapter(Context mContext, List<TestimonialItem> testimonialList) {
        this.mContext = mContext;
        this.testimonialList = testimonialList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.testimonial_list_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_name.setText(testimonialList.get(position).getName());
        holder.tv_headline.setText(testimonialList.get(position).getHeadline());
        holder.tv_description.setText(testimonialList.get(position).getDesc());
        //Picasso.get().load(testimonialList.get(position).getImg()).placeholder(R.drawable.placeholder).into(holder.img_profile);
        Glide.with(mContext).load(testimonialList.get(position).getImg()).into(holder.img_profile);

       /* Picasso.get().load(testimonialList.get(position).getImg())
                .placeholder(R.drawable.placeholder)
                .into(holder.img_profile, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        if (progressBar != null) {
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Exception e) {

                    }

                });*/
    }


    @Override
    public int getItemCount() {
        return testimonialList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_headline, tv_description;
        ImageView img_profile;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_headline = itemView.findViewById(R.id.tv_headline);
            tv_description = itemView.findViewById(R.id.tv_description);
            img_profile = itemView.findViewById(R.id.img_profile);
        }
    }
}
