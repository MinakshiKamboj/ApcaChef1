package com.apcachef.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apcachef.R;
import com.apcachef.interfaces.OnListItemClickListener;
import com.apcachef.model.CountryCodePicker;
import com.apcachef.model.egift.CoursesTypesItem;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    private Context mContext;
    private List<CoursesTypesItem> coursesTypes;
    OnListItemClickListener onListItemClickListener;

    public CourseAdapter(Context mContext, List<CoursesTypesItem> coursesTypes, OnListItemClickListener onListItemClickListener) {
        this.mContext = mContext;
        this.coursesTypes = coursesTypes;
        this.onListItemClickListener = onListItemClickListener;
    }

    @NonNull
    @Override
    public CourseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.drop_down_list_item, viewGroup, false);

        return new CourseAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.MyViewHolder viewHolder, final int position) {
        viewHolder.textView.setText(coursesTypes.get(position).getName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListItemClickListener.onItemClick(position, "course");
            }
        });
    }

    @Override
    public int getItemCount() {
        return coursesTypes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
