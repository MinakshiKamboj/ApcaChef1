package com.apcachef.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apcachef.R;
import com.apcachef.base.BaseActivity;
import com.apcachef.model.aboutUs.AboutUsResponse;
import com.apcachef.networking.ApiClient;
import com.apcachef.networking.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivacyActivity extends BaseActivity {
    ImageView img_back;
    TextView tv_title, tv_content;
    LinearLayout parent_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);
        parent_view = findViewById(R.id.parent_view);
        parent_view.setVisibility(View.GONE);
        tv_title.setText("Privacy Policy");

        img_back.setOnClickListener(view -> {
            finish();
        });

        getDetails();
    }

    private void getDetails() {
        showProgressDialog();
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<AboutUsResponse> call = apiService.getPrivacyPolicy();

        call.enqueue(new Callback<AboutUsResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<AboutUsResponse> call, Response<AboutUsResponse> response) {
                hideProgressDialog();
                parent_view.setVisibility(View.VISIBLE);
                AboutUsResponse aboutUsResponse = response.body();
                if (aboutUsResponse != null) {
                    tv_content.setText(Html.fromHtml(aboutUsResponse.getData().getContent()));
                } else {
                    showSnackBar(getResources().getString(R.string.errorMessage));
                }

            }

            @Override
            public void onFailure(Call<AboutUsResponse> call, Throwable t) {
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }
}