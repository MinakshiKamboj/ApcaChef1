package com.apcachef.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apcachef.R;
import com.apcachef.adapter.CustomExpandableListAdapter;
import com.apcachef.adapter.FAQAdapter;
import com.apcachef.base.BaseActivity;
import com.apcachef.model.aboutUs.AboutUsResponse;
import com.apcachef.model.faq.FAQResponse;
import com.apcachef.model.faq.FaqsItem;
import com.apcachef.networking.ApiClient;
import com.apcachef.networking.ApiService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FAQActivity extends BaseActivity {
    ImageView img_back;
    TextView tv_title;
    RecyclerView recycler_faq;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<FaqsItem> expandableListTitle = new ArrayList<>();
    HashMap<String, List<String>> expandableListDetail;
    FAQAdapter faqAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);
        recycler_faq = findViewById(R.id.recycler_faq);
        tv_title.setText("FAQ");

        img_back.setOnClickListener(view -> finish());

        getFAQData();

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        //expandableListDetail = ExpandableListDataPump.getData();
        //expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
       /* expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);*/
        faqAdapter = new FAQAdapter(expandableListTitle);
        recycler_faq.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycler_faq.setAdapter(faqAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });

    }

    private void getFAQData() {
        showProgressDialog();
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<FAQResponse> call = apiService.getFAQData();

        call.enqueue(new Callback<FAQResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<FAQResponse> call, Response<FAQResponse> response) {
                hideProgressDialog();
                FAQResponse aboutUsResponse = response.body();
                if (aboutUsResponse != null) {

                    expandableListTitle.addAll(aboutUsResponse.getData().getFaqs());
                    faqAdapter.notifyDataSetChanged();

                    /*expandableListAdapter = new CustomExpandableListAdapter(getApplicationContext(), expandableListTitle, expandableListDetail);
                    expandableListView.setAdapter(expandableListAdapter);*/
                } else {
                    showSnackBar(getResources().getString(R.string.errorMessage));
                }

            }

            @Override
            public void onFailure(Call<FAQResponse> call, Throwable t) {
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }
}