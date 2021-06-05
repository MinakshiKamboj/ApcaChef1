package com.apcachef.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apcachef.R;
import com.apcachef.adapter.ShortLessonAdapter;
import com.apcachef.adapter.ShortLessonAdapter1;
import com.apcachef.base.BaseActivity;
import com.apcachef.constant.AppConstants;
import com.apcachef.interfaces.OnListItemClickListener;
import com.apcachef.model.detail.CourseDetailRequest;
import com.apcachef.model.shortLesson.RowsItem;
import com.apcachef.model.shortLesson.ShortLessonResponse;
import com.apcachef.networking.ApiClient;
import com.apcachef.networking.ApiService;
import com.apcachef.util.SharedPrefsHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShortLessonActivity extends BaseActivity implements OnListItemClickListener {
    ImageView img_back;
    TextView tv_title;
    RecyclerView recycler_lesson;
    ShortLessonAdapter1 shortLessonAdapter;
    List<RowsItem> shortLessonList = new ArrayList<>();
    SharedPrefsHelper sharedPrefsHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_lesson);
        sharedPrefsHelper = new SharedPrefsHelper(this);
        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("Short Lessons");
        img_back.setOnClickListener(view -> finish());
        recycler_lesson = findViewById(R.id.recycler_lesson);
        shortLessonAdapter = new ShortLessonAdapter1(this, shortLessonList, this);
        recycler_lesson.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recycler_lesson.setAdapter(shortLessonAdapter);
        getShortLessonData();
    }
    private void getShortLessonData() {
        showProgressDialog();
        CourseDetailRequest shortVideoRequest = new CourseDetailRequest();
        shortVideoRequest.setId("1");
        shortVideoRequest.setUserId(sharedPrefsHelper.getUserId());
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ShortLessonResponse> call = apiService.getShortLesson(shortVideoRequest);

        call.enqueue(new Callback<ShortLessonResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ShortLessonResponse> call, Response<ShortLessonResponse> response) {
                hideProgressDialog();
                if (response.isSuccessful()) {
                    assert response.body() != null;

                    if (!AppConstants.countryCode.equals("") && AppConstants.countryCode.equals("IN")) {
                        AppConstants.symbol = response.body().getData().getInr().getSymbol();
                        AppConstants.code = response.body().getData().getInr().getCode();
                        AppConstants.price = response.body().getData().getInr().getPrice();
                        AppConstants.alertMessage = "Get Lesson in " + AppConstants.symbol + AppConstants.price;
                    } else if (!AppConstants.countryCode.equals("") && AppConstants.countryCode.equals("US")) {
                        AppConstants.symbol = response.body().getData().getInr().getSymbol();
                        AppConstants.code = response.body().getData().getInr().getCode();
                        AppConstants.price = response.body().getData().getInr().getPrice();
                        AppConstants.alertMessage = "Get Lesson in " + AppConstants.symbol + AppConstants.price;
                    } else if (!AppConstants.countryCode.equals("") && AppConstants.countryCode.equals("MY")) {
                        AppConstants.symbol = response.body().getData().getInr().getSymbol();
                        AppConstants.code = response.body().getData().getInr().getCode();
                        AppConstants.price = response.body().getData().getInr().getPrice();
                        AppConstants.alertMessage = "Get Lesson in " + AppConstants.symbol + AppConstants.price;
                    } else {
                        AppConstants.symbol = response.body().getData().getInr().getSymbol();
                        AppConstants.code = response.body().getData().getInr().getCode();
                        AppConstants.price = response.body().getData().getInr().getPrice();
                        AppConstants.alertMessage = "Get Lesson in " + AppConstants.symbol + AppConstants.price;
                    }
                    shortLessonList.addAll(response.body().getData().getRows());
                    shortLessonAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<ShortLessonResponse> call, Throwable t) {
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }
    @Override
    public void onItemClick(int position, String type) {
        Intent intent = new Intent(getApplicationContext(), ProgramListActivityVideo.class);
        intent.putExtra("id", shortLessonList.get(position).getId());
        intent.putExtra("name", "Short Videos");
        intent.putExtra("title", shortLessonList.get(position).getTitle());
        intent.putExtra("video_url", shortLessonList.get(position).getVideoUrl());
        intent.putExtra("desc", shortLessonList.get(position).getDescription());
        startActivity(intent);
    }
}