package com.apcachef.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apcachef.R;
import com.apcachef.adapter.CountryCodeAdapter;
import com.apcachef.adapter.CourseAdapter;
import com.apcachef.base.BaseActivity;
import com.apcachef.interfaces.OnListItemClickListener;
import com.apcachef.model.CountryCodePicker;
import com.apcachef.model.egift.CoursesTypesItem;
import com.apcachef.model.egift.EGiftResponse;
import com.apcachef.model.egift.SendEGiftRequest;
import com.apcachef.model.egift.SendEGiftResponse;
import com.apcachef.model.register.RegisterResponse;
import com.apcachef.networking.ApiClient;
import com.apcachef.networking.ApiService;
import com.apcachef.util.SharedPrefsHelper;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EGiftActivity extends BaseActivity implements View.OnClickListener, OnListItemClickListener {

    Context mContext;
    ImageView img_back;
    TextView tv_title;
    ImageView img_gift_banner;
    TextView tv_gift_title, tv_heading, tv_description, tv_course, tv_country_code;
    TextInputEditText edit_your_name, edit_your_email, edit_to_name, edit_mobile, edit_to_email, edit_message;
    MaterialButton btn_send;

    SharedPrefsHelper sharedPrefsHelper;
    Dialog dialog;
    String countryCode;
    int courseId;
    CountryCodePicker countryCodePicker;
    List<CountryCodePicker> mCountryList = new ArrayList<>();
    CountryCodeAdapter countryCodeAdapter;

    CourseAdapter courseAdapter;
    List<CoursesTypesItem> coursesTypes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_gift);
        mContext = this;
        sharedPrefsHelper = new SharedPrefsHelper(this);

        initViews();

        countryCode = sharedPrefsHelper.getCountryCode();
        tv_country_code.setText(countryCode);

        countryCodePicker = new CountryCodePicker();
        mCountryList = countryCodePicker.getmCountryList();

        getEGift();
    }

    private void openCountryCodePicker(String title, String type) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_country_picker);
        dialog.setCancelable(true);

        TextView dialogTitle = dialog.findViewById(R.id.dialogTitle);
        dialogTitle.setText(title);
        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerView);

        if (type.equalsIgnoreCase("code")) {
            countryCodeAdapter = new CountryCodeAdapter(getApplicationContext(), mCountryList, this);
            recyclerView.setAdapter(countryCodeAdapter);
        } else {
            courseAdapter = new CourseAdapter(getApplicationContext(), coursesTypes, this);
            recyclerView.setAdapter(courseAdapter);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dialog.show();
    }

    private void initViews() {
        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("E-Gift");

        img_back.setOnClickListener(view -> {
            finish();
        });

        img_gift_banner = findViewById(R.id.img_gift_banner);
        tv_gift_title = findViewById(R.id.tv_gift_title);
        tv_heading = findViewById(R.id.tv_heading);
        tv_description = findViewById(R.id.tv_description);

        tv_course = findViewById(R.id.tv_course);
        tv_country_code = findViewById(R.id.tv_country_code);
        edit_your_name = findViewById(R.id.edit_your_name);
        edit_your_email = findViewById(R.id.edit_your_email);
        edit_to_name = findViewById(R.id.edit_to_name);
        edit_mobile = findViewById(R.id.edit_mobile);
        edit_to_email = findViewById(R.id.edit_to_email);
        edit_message = findViewById(R.id.edit_message);
        btn_send = findViewById(R.id.btn_send);

        tv_course.setOnClickListener(this);
        tv_country_code.setOnClickListener(this);
        btn_send.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_course:
                openCountryCodePicker("Select Course", "course");
                break;
            case R.id.tv_country_code:
                openCountryCodePicker("Select country", "code");
                break;
            case R.id.btn_send:
                validateViews();
                break;
        }
    }

    private void validateViews() {
        if (tv_course.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(mContext, "Select Course", Toast.LENGTH_LONG).show();
        } else if (edit_your_name.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(mContext, "Enter name", Toast.LENGTH_LONG).show();
        } else if (edit_your_email.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(mContext, "Enter email", Toast.LENGTH_LONG).show();
        } else if (edit_to_name.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(mContext, "Enter name", Toast.LENGTH_LONG).show();
        } else if (edit_mobile.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(mContext, "Enter mobile", Toast.LENGTH_LONG).show();
        } else if (edit_to_email.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(mContext, "Enter email", Toast.LENGTH_LONG).show();
        } else if (tv_country_code.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(mContext, "Select country code", Toast.LENGTH_LONG).show();
        } else {
            postGiftData();
        }
    }

    private void postGiftData() {
        showProgressDialog();
        ApiClient.retrofit = null;
        SendEGiftRequest sendEGiftRequest = new SendEGiftRequest();
        sendEGiftRequest.setModeType(String.valueOf(courseId));
        sendEGiftRequest.setFromName(edit_your_name.getText().toString().trim());
        sendEGiftRequest.setFromEmail(edit_your_email.getText().toString().trim());
        sendEGiftRequest.setName(edit_to_name.getText().toString().trim());
        sendEGiftRequest.setEmail(edit_to_email.getText().toString().trim());
        sendEGiftRequest.setCountryCode(String.valueOf(countryCode));
        sendEGiftRequest.setMobile(edit_mobile.getText().toString().trim());
        sendEGiftRequest.setMessage(edit_message.getText().toString().trim());

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<SendEGiftResponse> call = apiService.sendGift(sendEGiftRequest);

        call.enqueue(new Callback<SendEGiftResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<SendEGiftResponse> call, Response<SendEGiftResponse> response) {
                hideProgressDialog();
                SendEGiftResponse loginResponse = response.body();
                if (loginResponse != null && loginResponse.getStatus() != 0) {

                    Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                    intent.putExtra("showMode", true);
                    intent.putExtra("payForGift", true);
                    intent.putExtra("eGiftUserId", String.valueOf(response.body().getGiftData().getEgiftUserId()));
                    intent.putExtra("eGiftTblId", String.valueOf(response.body().getGiftData().getEgiftUserTblId()));
                    intent.putExtra("modeID", String.valueOf(courseId));
                    startActivity(intent);
                } else {
                    showSnackBar(loginResponse.getMsg());
                }

            }

            @Override
            public void onFailure(Call<SendEGiftResponse> call, Throwable t) {
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }

    private void getEGift() {
        showProgressDialog();
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<EGiftResponse> call = apiService.getEGift();

        call.enqueue(new Callback<EGiftResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<EGiftResponse> call, Response<EGiftResponse> response) {
                hideProgressDialog();
                EGiftResponse aboutUsResponse = response.body();
                if (aboutUsResponse != null) {

                    Glide.with(mContext).load(response.body().getData().getImg()).into(img_gift_banner);
                    tv_gift_title.setText(response.body().getData().getTitle());
                    tv_heading.setText(response.body().getData().getHeading());
                    tv_description.setText(response.body().getData().getDescription());

                    coursesTypes.clear();
                    coursesTypes.addAll(response.body().getData().getCoursesTypes());

                } else {
                    showSnackBar(getResources().getString(R.string.errorMessage));
                }

            }

            @Override
            public void onFailure(Call<EGiftResponse> call, Throwable t) {
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }

    @Override
    public void onItemClick(int position, String type) {
        dialog.dismiss();
        if (type.equalsIgnoreCase("course")) {
            courseId = coursesTypes.get(position).getId();
            tv_course.setText(coursesTypes.get(position).getName());
        } else {
            tv_country_code.setText(mCountryList.get(position).getCountryCode());
            countryCode = mCountryList.get(position).getCountryCode();
        }

    }
}