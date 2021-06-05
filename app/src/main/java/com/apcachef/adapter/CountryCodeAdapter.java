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

import java.util.List;

public class CountryCodeAdapter extends RecyclerView.Adapter<CountryCodeAdapter.MyViewHolder> {

    private Context mContext;
    private List<CountryCodePicker> mCountryList;
    OnListItemClickListener onListItemClickListener;

    public CountryCodeAdapter(Context mContext, List<CountryCodePicker> mCountryList, OnListItemClickListener onListItemClickListener) {
        this.mContext = mContext;
        this.mCountryList = mCountryList;
        this.onListItemClickListener = onListItemClickListener;
    }

    @NonNull
    @Override
    public CountryCodeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.drop_down_list_item, viewGroup, false);

        return new CountryCodeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryCodeAdapter.MyViewHolder viewHolder, final int position) {
        viewHolder.textView.setText(mCountryList.get(position).getCountryName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListItemClickListener.onItemClick(position, "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCountryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
