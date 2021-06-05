package com.apcachef.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apcachef.R;
import com.apcachef.model.Model;
import com.apcachef.model.home.ChefsItem;
import com.bumptech.glide.Glide;

import java.util.List;

public class MultiViewTypeAdapter extends RecyclerView.Adapter {
    private List<ChefsItem> chefsList;
    Context mContext;
    private boolean showAll;

    public MultiViewTypeAdapter(Context mContext, List<ChefsItem> chefsList, boolean showAll) {
        this.mContext = mContext;
        this.chefsList = chefsList;
        this.showAll = showAll;
    }

    public class LeftViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title, tv_description, tv_first_name, tv_last_name;
        ImageView img_profile;

        public LeftViewHolder(View itemView) {
            super(itemView);

            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            tv_first_name = (TextView) itemView.findViewById(R.id.tv_first_name);
            tv_last_name = (TextView) itemView.findViewById(R.id.tv_last_name);
            img_profile = (ImageView) itemView.findViewById(R.id.img_profile);
        }
    }

    public class RightViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title, tv_description, tv_first_name, tv_last_name;
        ImageView img_profile;

        public RightViewHolder(View itemView) {
            super(itemView);

            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            tv_first_name = (TextView) itemView.findViewById(R.id.tv_first_name);
            tv_last_name = (TextView) itemView.findViewById(R.id.tv_last_name);
            img_profile = (ImageView) itemView.findViewById(R.id.img_profile);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case Model.LEFT_VIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.instructors_item_left, parent, false);
                return new LeftViewHolder(view);
            case Model.RIGHT_VIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.instructors_item_right, parent, false);
                return new RightViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (chefsList.size() > 0) {
            if (showAll) {
                ChefsItem chefsItem = chefsList.get(position);
                if (position % 2 == 0) {
                    ((LeftViewHolder) holder).tv_title.setText(chefsItem.getHeadline());
                    ((LeftViewHolder) holder).tv_description.setText(chefsItem.getAbout());
                    ((LeftViewHolder) holder).tv_first_name.setText(chefsItem.getFirstName());
                    ((LeftViewHolder) holder).tv_last_name.setText(chefsItem.getLastName());
                    //Picasso.get().load(chefsItem.getImg()).placeholder(R.drawable.image_thumb).into(((LeftViewHolder) holder).img_profile);
                    Glide.with(mContext).load(chefsItem.getImg()).into(((LeftViewHolder) holder).img_profile);
                } else {
                    ((RightViewHolder) holder).tv_title.setText(chefsItem.getHeadline());
                    ((RightViewHolder) holder).tv_description.setText(chefsItem.getAbout());
                    ((RightViewHolder) holder).tv_first_name.setText(chefsItem.getFirstName());
                    ((RightViewHolder) holder).tv_last_name.setText(chefsItem.getLastName());
                    //Picasso.get().load(chefsItem.getImg()).placeholder(R.drawable.image_thumb).into(((RightViewHolder) holder).img_profile);
                    Glide.with(mContext).load(chefsItem.getImg()).into(((RightViewHolder) holder).img_profile);
                }
            } else {
                if (position < 2) {
                    ChefsItem chefsItem = chefsList.get(position);
                    if (position % 2 == 0) {
                        ((LeftViewHolder) holder).tv_title.setText(chefsItem.getHeadline());
                        ((LeftViewHolder) holder).tv_description.setText(chefsItem.getAbout());
                        ((LeftViewHolder) holder).tv_first_name.setText(chefsItem.getFirstName());
                        ((LeftViewHolder) holder).tv_last_name.setText(chefsItem.getLastName());
                        //Picasso.get().load(chefsItem.getImg()).placeholder(R.drawable.image_thumb).into(((LeftViewHolder) holder).img_profile);
                        Glide.with(mContext).load(chefsItem.getImg()).into(((LeftViewHolder) holder).img_profile);
                    } else {
                        ((RightViewHolder) holder).tv_title.setText(chefsItem.getHeadline());
                        ((RightViewHolder) holder).tv_description.setText(chefsItem.getAbout());
                        ((RightViewHolder) holder).tv_first_name.setText(chefsItem.getFirstName());
                        ((RightViewHolder) holder).tv_last_name.setText(chefsItem.getLastName());
                        //Picasso.get().load(chefsItem.getImg()).placeholder(R.drawable.image_thumb).into(((RightViewHolder) holder).img_profile);
                        Glide.with(mContext).load(chefsItem.getImg()).into(((RightViewHolder) holder).img_profile);

                    }
                }
            }

        }

        /*if (object != null) {
            switch (object.viewPosition) {
                case Model.LEFT_VIEW:
                    //((TextTypeViewHolder) holder).txtType.setText(object.text);
                    break;
                case Model.RIGHT_VIEW:
                    *//*((ImageTypeViewHolder) holder).txtType.setText(object.text);
                    ((ImageTypeViewHolder) holder).image.setImageResource(object.data);*//*
                    break;
            }
        }*/
    }

    @Override
    public int getItemCount() {
        if (showAll) {
            return chefsList.size();
        } else {
            return 2;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return Model.LEFT_VIEW;
        } else {
            return Model.RIGHT_VIEW;
        }
    }

}
