package com.apcachef.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

import com.apcachef.R;
import com.apcachef.adapter.TopicsAdapter;
import com.apcachef.adapter.TopicsAdapterGoodCustomer;
import com.apcachef.base.BaseActivity;
import com.apcachef.interfaces.OnListItemClickListener;
import com.apcachef.model.detail.RecipeItem;
import com.apcachef.model.goodCustomer.GoodCustomerRequest;
import com.apcachef.model.goodCustomer.GoodCustomerResponse;
import com.apcachef.model.goodCustomer.TopicsItem;
import com.apcachef.networking.ApiClient;
import com.apcachef.networking.ApiService;
import com.apcachef.util.SharedPrefsHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoodCustomerDetailActivity extends BaseActivity implements OnListItemClickListener {
    ImageView img_back;
    TextView tv_title;
    RecyclerView recyclerTopics;
    List<TopicsItem> topics = new ArrayList<>();
    TopicsAdapterGoodCustomer topicsAdapter;
    SharedPrefsHelper sharedPrefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_customer_detail);
        sharedPrefsHelper = new SharedPrefsHelper(this);

        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);

        tv_title.setText("Topics List");

        img_back.setOnClickListener(view -> finish());

        recyclerTopics = findViewById(R.id.recyclerTopics);
        topicsAdapter = new TopicsAdapterGoodCustomer(getApplicationContext(), topics, this);
        recyclerTopics.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerTopics.setAdapter(topicsAdapter);
        recyclerTopics.setNestedScrollingEnabled(false);

        getDetailData();
    }

    private void getDetailData() {
        showProgressDialog();
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        GoodCustomerRequest goodCustomerRequest = new GoodCustomerRequest();
        goodCustomerRequest.setUserId(sharedPrefsHelper.getUserIdGoodCustomer());
        Call<GoodCustomerResponse> call = apiService.getGoodCustomerData(goodCustomerRequest);

        call.enqueue(new Callback<GoodCustomerResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<GoodCustomerResponse> call, Response<GoodCustomerResponse> response) {
                hideProgressDialog();
                assert response.body() != null;
                if (response.body().getStatus() == 1) {
                    topics.addAll(response.body().getData().getTopics());
                    topicsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<GoodCustomerResponse> call, Throwable t) {
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }

    @Override
    public void onItemClick(int position, String type) {
        List<RecipeItem> recipeList = topics.get(position).getRecipes();
        Intent intent = new Intent(getApplicationContext(), RecipeListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id", topics.get(position).getId());
        bundle.putString("title", topics.get(position).getTitle());
        bundle.putParcelableArrayList("recipeList", (ArrayList<? extends Parcelable>) recipeList);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}