package com.apcachef.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apcachef.R;
import com.apcachef.base.BaseActivity;
import com.apcachef.model.clogin.GCLoginResponse;
import com.apcachef.model.login.LoginRequest;
import com.apcachef.model.login.LoginResponse;
import com.apcachef.networking.ApiClient;
import com.apcachef.networking.ApiService;
import com.apcachef.util.SharedPrefsHelper;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginGoodCustomerActivity extends BaseActivity implements View.OnClickListener {
    SharedPrefsHelper sharedPrefsHelper;
    Button btn_login;
    TextView link_signup, tv_forget_password;
    TextInputEditText input_email, input_password;
    String email, password;
    TextView tv_title;
    ImageView img_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        tv_title = findViewById(R.id.tv_title);
        img_back = findViewById(R.id.img_back);
        tv_title.setText("So good customer");
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginGoodCustomerActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
        initViews();

        sharedPrefsHelper = new SharedPrefsHelper(this);
    }

    private void postLoginRequest() {
        showProgressDialog();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(input_email.getText().toString().trim());
        loginRequest.setPassword(input_password.getText().toString().trim());
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<GCLoginResponse> call = apiService.postLoginRequestGoodCustomer(loginRequest);

        call.enqueue(new Callback<GCLoginResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<GCLoginResponse> call, Response<GCLoginResponse> response) {
                hideProgressDialog();
                GCLoginResponse loginResponse = response.body();
                if (loginResponse != null && loginResponse.getStatus() != 0) {
                    sharedPrefsHelper.setIsLoginGoodCustomer(true);
                    sharedPrefsHelper.setUserIdGoodCustomer(loginResponse.getData().getLogindata().getUserId());

                    startActivityWithFinish(MainActivity.class);
                    Toast.makeText(getApplicationContext(), loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    showSnackBar(loginResponse.getMsg());
                }

            }

            @Override
            public void onFailure(Call<GCLoginResponse> call, Throwable t) {
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
        link_signup.setVisibility(View.GONE);
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
                //startActivity(ForgetPassword.class);
                Intent intent = new Intent(getApplicationContext(), ForgetPassword.class);
                intent.putExtra("forgetPasswordGoodCustomer", true);
                startActivity(intent);
                break;
        }
    }
}