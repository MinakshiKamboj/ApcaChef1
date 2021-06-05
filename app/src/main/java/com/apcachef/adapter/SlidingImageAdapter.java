package com.apcachef.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.apcachef.R;
import com.apcachef.model.PagerModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class SlidingImageAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<PagerModel> pagerModelList;

    public SlidingImageAdapter(Context context, List<PagerModel> pagerModelList) {
        this.mContext = context;
        this.pagerModelList = pagerModelList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return pagerModelList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup viewGroup, int position) {
        View itemView = inflater.inflate(R.layout.pager_item, viewGroup, false);

        PagerModel get = pagerModelList.get(position);

        TextView textView = itemView.findViewById(R.id.name);
        textView.setText(get.getDescription());

        ImageView photoView = (ImageView) itemView.findViewById(R.id.image);
        //Picasso.get().load(get.getImageUrl()).memoryPolicy(MemoryPolicy.NO_CACHE).into(photoView);
        Glide.with(mContext).load(get.getImageUrl()).into(photoView);
        viewGroup.addView(itemView);
        return itemView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}