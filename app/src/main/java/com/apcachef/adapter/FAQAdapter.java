package com.apcachef.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apcachef.R;
import com.apcachef.model.faq.FaqsItem;

import java.util.List;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.MyViewHolder> {
    List<FaqsItem> expandableListTitle;

    public FAQAdapter(List<FaqsItem> expandableListTitle) {
        this.expandableListTitle = expandableListTitle;
    }

    @NonNull
    @Override
    public FAQAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.faq_items, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQAdapter.MyViewHolder holder, int position) {
        holder.tv_question.setText(expandableListTitle.get(position).getQuestion());
        holder.tv_answer.setText(expandableListTitle.get(position).getAnswer());
    }

    @Override
    public int getItemCount() {
        return expandableListTitle.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_question, tv_answer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_question = itemView.findViewById(R.id.tv_question);
            tv_answer = itemView.findViewById(R.id.tv_answer);
        }
    }
}
