package com.apcachef.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apcachef.R;
import com.apcachef.base.BaseActivity;
import com.apcachef.model.login.LoginRequest;
import com.apcachef.model.login.LoginResponse;
import com.apcachef.model.register.RegisterRequest;
import com.apcachef.model.register.RegisterResponse;
import com.apcachef.networking.ApiClient;
import com.apcachef.networking.ApiService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    AppCompatButton btn_signup;
    TextView link_login;
    TextInputEditText input_name, input_mobile, input_email, input_password, input_confirm_password, input_promo_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        link_login = findViewById(R.id.link_login);
        btn_signup = findViewById(R.id.btn_signup);

        input_name = findViewById(R.id.input_name);
        input_mobile = findViewById(R.id.input_mobile);
        input_email = findViewById(R.id.input_email);
        input_password = findViewById(R.id.input_password);
        input_confirm_password = findViewById(R.id.input_confirm_password);
        input_promo_code = findViewById(R.id.input_promo_code);

        link_login.setOnClickListener(this);
        btn_signup.setOnClickListener(this);

    }

    private void postRegisterRequest() {
        showProgressDialog();
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setName(input_name.getText().toString().trim());
        registerRequest.setCountryCode("91");
        registerRequest.setMobile(input_mobile.getText().toString().trim());
        registerRequest.setEmail(input_email.getText().toString().trim());
        registerRequest.setPassword(input_password.getText().toString().trim());
        registerRequest.setPassword(input_confirm_password.getText().toString().trim());
        registerRequest.setPromoCode(input_promo_code.getText().toString().trim());
        registerRequest.setRegType("1");
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<RegisterResponse> call = apiService.postRegisterRequest(registerRequest);

        call.enqueue(new Callback<RegisterResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                hideProgressDialog();
                RegisterResponse loginResponse = response.body();
                if (loginResponse != null && loginResponse.getStatus() != 0) {
                    Toast.makeText(getApplicationContext(), loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra("email", input_email.getText().toString().trim());
                    intent.putExtra("password", input_password.getText().toString().trim());
                    startActivity(intent);
                    finish();
                } else {
                    showSnackBar(loginResponse.getMsg());
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_signup:
                postRegisterRequest();
                break;
            case R.id.link_login:
                finish();
                break;
        }
    }
}