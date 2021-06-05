package com.apcachef.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apcachef.R;
import com.apcachef.model.payment.ModeList;

import java.util.List;

public class PaymentModeAdapter extends BaseAdapter {
    Context context;
    List<ModeList> modeLists;

    public PaymentModeAdapter(Context context, List<ModeList> modeLists) {
        this.context = context;
        this.modeLists = modeLists;
    }

    public int getCount() {
        return modeLists.size();
    }

    @Override
    public ModeList getItem(int position) {
        return modeLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ModeList result = modeLists.get(position);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.custom_spinner_layout, parent, false);
        TextView textView_item = row.findViewById(R.id.textView);
        textView_item.setText(result.getName());
        return row;
    }
}