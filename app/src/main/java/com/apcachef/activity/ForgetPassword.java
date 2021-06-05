package com.apcachef.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apcachef.R;
import com.apcachef.base.BaseActivity;
import com.apcachef.model.ForgetPasswordResponse;
import com.apcachef.model.ForgetRequest;
import com.apcachef.model.login.LoginRequest;
import com.apcachef.model.login.LoginResponse;
import com.apcachef.networking.ApiClient;
import com.apcachef.networking.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPassword extends BaseActivity {
    EditText input_email;
    Button btn_forget;
    boolean forgetPasswordGoodCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        forgetPasswordGoodCustomer = getIntent().getBooleanExtra("forgetPasswordGoodCustomer", false);

        input_email = findViewById(R.id.input_email);
        btn_forget = findViewById(R.id.btn_forget);

        btn_forget.setOnClickListener(view -> postForgetRequest());

    }

    private void postForgetRequest() {
        showProgressDialog();
        ForgetRequest forgetRequest = new ForgetRequest();
        forgetRequest.setEmail(input_email.getText().toString().trim());
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ForgetPasswordResponse> call = null;
        if (forgetPasswordGoodCustomer) {
            call = apiService.postForgetRequestGoodCustomer(forgetRequest);
        } else {
            call = apiService.postForgetRequest(forgetRequest);
        }

        call.enqueue(new Callback<ForgetPasswordResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ForgetPasswordResponse> call, Response<ForgetPasswordResponse> response) {
                hideProgressDialog();
                ForgetPasswordResponse forgetPasswordResponse = response.body();
                if (forgetPasswordResponse != null) {
                    Toast.makeText(getApplicationContext(), forgetPasswordResponse.getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    showSnackBar(getResources().getString(R.string.errorMessage));
                }

            }

            @Override
            public void onFailure(Call<ForgetPasswordResponse> call, Throwable t) {
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }

}