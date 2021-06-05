/*
package com.apcachef.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.apcachef.R;
import com.apcachef.model.PagerModel;

import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {
    List<PagerModel> pagerModelList;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int pos) {
        switch (pos) {
            case 0:
                return FragmentViewPager.newInstance(pagerModelList.get(pos).getDescription(), R.drawable.video_thumbnail);
            case 1:
                return FragmentViewPager.newInstance(pagerModelList.get(pos).getDescription(), R.drawable.video_thumbnail);
            case 2:
                return FragmentViewPager.newInstance(pagerModelList.get(pos).getDescription(), R.drawable.video_thumbnail);
            default:
                return FragmentViewPager.newInstance(pagerModelList.get(pos).getDescription(), R.drawable.video_thumbnail);
        }
    }

    @Override
    public int getCount() {
        return pagerModelList.size();
    }
}*/
