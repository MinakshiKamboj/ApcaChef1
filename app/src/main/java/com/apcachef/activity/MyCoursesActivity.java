package com.apcachef.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apcachef.R;
import com.apcachef.adapter.CulinaryProgramCourseAdapter;
import com.apcachef.adapter.MasterProgramAdapter;
import com.apcachef.adapter.PastryProgramCourseAdapter;
import com.apcachef.base.BaseActivity;
import com.apcachef.interfaces.OnListItemClickListener;
import com.apcachef.model.goodCustomer.GoodCustomerRequest;
import com.apcachef.model.home.MasterClassItem;
import com.apcachef.model.myCourses.Culinary;
import com.apcachef.model.myCourses.MyCoursesResponse;
import com.apcachef.model.myCourses.Pastry;
import com.apcachef.model.myCourses.SubsItem;
import com.apcachef.networking.ApiClient;
import com.apcachef.networking.ApiService;
import com.apcachef.util.SharedPrefsHelper;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCoursesActivity extends BaseActivity implements OnListItemClickListener {
    ImageView img_back, image_eggless;
    TextView tv_title,tv_get_renew;//bye bye :)
    LinearLayout parent_view;
    RecyclerView recycler_pastry_program, recycler_culinary_program, recycler_master_class;
    String firstCategoryName, secondCategoryName;
    PastryProgramCourseAdapter pastryProgramCourseAdapter;
    CulinaryProgramCourseAdapter culinaryProgramCourseAdapter;
    MasterProgramAdapter masterProgramAdapter;
    List<SubsItem> onlineProgramList = new ArrayList<>();
    List<SubsItem> culinaryProgramList = new ArrayList<>();
    private List<MasterClassItem> masterClassList = new ArrayList<>();
    LinearLayout eggLess_program;
    SharedPrefsHelper sharedPrefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses);

        sharedPrefsHelper = new SharedPrefsHelper(this);
        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);
        eggLess_program = findViewById(R.id.eggLess_program);
        image_eggless = findViewById(R.id.image_eggless);
        parent_view = findViewById(R.id.parent_view);
        tv_title.setText("My Courses");
        tv_get_renew = findViewById(R.id.tv_get_renew);

        if (sharedPrefsHelper.getPurchaseDate().equals("2020")){
    tv_get_renew.setVisibility(View.VISIBLE);
        }  else{
    tv_get_renew.setVisibility(View.GONE);
}
        img_back.setOnClickListener(view -> {
            finish();
        });

        eggLess_program.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ProgramListActivity.class);
            intent.putExtra("id", "eggless");
            intent.putExtra("name", "EGG LESS PROGRAM");
            intent.putExtra("title", "EGGLESS CERTIFICATE PROGRAM");
            startActivity(intent);
        });

        tv_get_renew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //            Toast.makeText(MainActivity.this, "Oooo meri jaan...", Toast.LENGTH_SHORT).show();
                if (sharedPrefsHelper.isLogin()) {
                    //         if (!sharedPrefsHelper.isSubscribed()) {
                    Intent intent = new Intent(getApplicationContext(), RenewPaymentActivity.class);
                    intent.putExtra("showMode", true);
                    startActivity(intent);
                    //         }
                } else {
                    Intent about = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(about);
                }
            }
        });


        recycler_pastry_program = findViewById(R.id.recycler_pastry_program);
        pastryProgramCourseAdapter = new PastryProgramCourseAdapter(getApplicationContext(), onlineProgramList, this);
        recycler_pastry_program.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recycler_pastry_program.setAdapter(pastryProgramCourseAdapter);
        recycler_pastry_program.setNestedScrollingEnabled(false);

        recycler_culinary_program = findViewById(R.id.recycler_culinary_program);
        culinaryProgramCourseAdapter = new CulinaryProgramCourseAdapter(getApplicationContext(), culinaryProgramList, this);
        recycler_culinary_program.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recycler_culinary_program.setAdapter(culinaryProgramCourseAdapter);
        recycler_culinary_program.setNestedScrollingEnabled(false);

        recycler_master_class = findViewById(R.id.recycler_master_class);
        masterProgramAdapter = new MasterProgramAdapter(getApplicationContext(), masterClassList, this);
        recycler_master_class.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recycler_master_class.setAdapter(masterProgramAdapter);

        getMyCourses();

    }

    private void getMyCourses() {
        showProgressDialog();
        GoodCustomerRequest goodCustomerRequest = new GoodCustomerRequest();
        goodCustomerRequest.setUserId(sharedPrefsHelper.getUserId());
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<MyCoursesResponse> call = apiService.getMyCourses(goodCustomerRequest);
        call.enqueue(new Callback<MyCoursesResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<MyCoursesResponse> call, Response<MyCoursesResponse> response) {
                Log.e("response", String.valueOf(response));
                hideProgressDialog();
                parent_view.setVisibility(View.VISIBLE);


                if (!response.isSuccessful()){
                    return;
                }

                if (response.body()==null){
                    return;
                }


                if (response.body().getData()==null){
                    return;
                }

                Glide.with(getApplicationContext()).load(response.body().getData().getEggless().getRow().getImg()).into(image_eggless);
                Log.e("mini", new Gson().toJson(response.body().getData().getPastry()));
                Log.e("mini", new Gson().toJson(response.body().getData().getCulinary()));
                Log.e("mini", new Gson().toJson(response.body().getData().getMasterClass()));

                Pastry pastry= response.body().getData().getPastry();
                if (pastry != null){
                    if (pastry.getId()!=null) {
                        firstCategoryName = pastry.getName();
                        onlineProgramList.addAll(response.body().getData().getPastry().getSubs());
                        pastryProgramCourseAdapter.notifyDataSetChanged();
                    }
                }
                Culinary culinary = response.body().getData().getCulinary();
               if (culinary != null){
                   if (culinary.getId() != null) {
                       secondCategoryName = culinary.getName();
                       culinaryProgramList.addAll(culinary.getSubs());
                       culinaryProgramCourseAdapter.notifyDataSetChanged();
                   }
                }
                 if (response.body().getData().getMasterClass() != null){
                    masterClassList.addAll(response.body().getData().getMasterClass());
                    masterProgramAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MyCoursesResponse> call, Throwable t) {
                t.printStackTrace();
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }

    @Override
    public void onItemClick(int position, String type) {
        if (type.equalsIgnoreCase("pastry")) {
            Intent intent = new Intent(getApplicationContext(), ProgramListActivity.class);
            intent.putExtra("id", onlineProgramList.get(position).getId());
            intent.putExtra("name", firstCategoryName);
            intent.putExtra("title", onlineProgramList.get(position).getTitle());
            intent.putExtra("video_url", onlineProgramList.get(position).getVideoUrl());
            intent.putExtra("desc", onlineProgramList.get(position).getShortDescription());
            startActivity(intent);

        } else {
            Intent intent = new Intent(getApplicationContext(), ProgramListActivity.class);
            intent.putExtra("id", culinaryProgramList.get(position).getId());
            intent.putExtra("name", secondCategoryName);
            intent.putExtra("title", culinaryProgramList.get(position).getTitle());
            intent.putExtra("video_url", culinaryProgramList.get(position).getVideoUrl());
            intent.putExtra("desc", culinaryProgramList.get(position).getShortDescription());
            startActivity(intent);
        }
    }
}