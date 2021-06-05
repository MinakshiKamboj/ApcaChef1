package com.apcachef.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apcachef.R;
import com.apcachef.interfaces.ListVideoPlay;
import com.apcachef.interfaces.OnListItemClickListener;
import com.apcachef.model.detail.RecipeItem;
import com.ct7ct7ct7.androidvimeoplayer.view.VimeoPlayerView;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.MyViewHolder> {
    List<RecipeItem> recipeList;
    Context mContext;
    ListVideoPlay listVideoPlay;

    public RecipeListAdapter(Context mContext, List<RecipeItem> recipeList, ListVideoPlay listVideoPlay) {
        this.mContext = mContext;
        this.recipeList = recipeList;
        this.listVideoPlay = listVideoPlay;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recipe_list_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (recipeList.get(position).getVideoUrl() != null && !recipeList.get(position).getVideoUrl().equalsIgnoreCase("")) {
            int videoId = Integer.parseInt(recipeList.get(position).getVideoUrl());
            holder.vimeoPlayerView.initialize(true, videoId);
            holder.vimeoPlayerView.setFullscreenVisibility(true);
        } else {
            Toast.makeText(mContext, "Video not found", Toast.LENGTH_LONG).show();
        }

        holder.tv_title.setText(Html.fromHtml(recipeList.get(position).getTitle()));
        holder.tv_description.setText(Html.fromHtml(recipeList.get(position).getDescription()));

        holder.vimeoPlayerView.setFullscreenClickListener(view -> {
            listVideoPlay.onListVideoPlay(position, holder.vimeoPlayerView);
        });

    }


    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_category, tv_title, tv_description;
        VimeoPlayerView vimeoPlayerView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_category = itemView.findViewById(R.id.tv_category);
            vimeoPlayerView = itemView.findViewById(R.id.vimeoPlayer);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
        }
    }
}
