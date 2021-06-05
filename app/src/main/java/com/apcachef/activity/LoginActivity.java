package com.apcachef.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.apcachef.R;
import com.apcachef.base.BaseActivity;
import com.apcachef.model.login.LoginRequest;
import com.apcachef.model.login.LoginResponse;
import com.apcachef.networking.ApiClient;
import com.apcachef.networking.ApiService;
import com.apcachef.util.SharedPrefsHelper;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    SharedPrefsHelper sharedPrefsHelper;
    Button btn_login;
    TextView link_signup, tv_forget_password;
    TextInputEditText input_email, input_password;
    String email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        sharedPrefsHelper = new SharedPrefsHelper(this);

        if (getIntent().getStringExtra("email") != null) {
            email = getIntent().getStringExtra("email");
            password = getIntent().getStringExtra("password");

            input_email.setText(email);
            input_password.setText(password);
        }
    }

    private void postLoginRequest() {
        showProgressDialog();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(input_email.getText().toString().trim());
        loginRequest.setPassword(input_password.getText().toString().trim());
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<LoginResponse> call = apiService.postLoginRequest(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                hideProgressDialog();
                LoginResponse loginResponse = response.body();
                if (loginResponse != null && loginResponse.getStatus() != 0) {
                    sharedPrefsHelper.setIsLogin(true);
                    sharedPrefsHelper.setUserId(loginResponse.getData().getUserId());
                    sharedPrefsHelper.setName(loginResponse.getData().getName());
                    sharedPrefsHelper.setMobile(loginResponse.getData().getMobile());
                    sharedPrefsHelper.setEmail(loginResponse.getData().getEmail());
                    sharedPrefsHelper.setCountryCode(loginResponse.getData().getCountryCode());

                    startActivityWithFinish(MainActivity.class);
                    Toast.makeText(getApplicationContext(), loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    showSnackBar(loginResponse.getMsg());
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }

    private void initViews() {
        input_email = findViewById(R.id.input_email);
        input_password = findViewById(R.id.input_password);
        btn_login = findViewById(R.id.btn_login);
        link_signup = findViewById(R.id.link_signup);
        tv_forget_password = findViewById(R.id.tv_forget_password);

        btn_login.setOnClickListener(this);
        link_signup.setOnClickListener(this);
        tv_forget_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                postLoginRequest();
                break;
            case R.id.link_signup:
                startActivityWithFinish(RegisterActivity.class);
                break;
            case R.id.tv_forget_password:
                startActivity(ForgetPassword.class);
                break;
        }
    }
}