package com.apcachef.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apcachef.R;
import com.apcachef.adapter.CountryCodeAdapter;
import com.apcachef.base.BaseActivity;
import com.apcachef.interfaces.OnListItemClickListener;
import com.apcachef.model.CountryCodePicker;
import com.apcachef.model.account.ChangePasswordRequest;
import com.apcachef.model.account.ChangePasswordResponse;
import com.apcachef.model.editProfile.UpdateProfileRequest;
import com.apcachef.model.editProfile.UpdateProfileResponse;
import com.apcachef.networking.ApiClient;
import com.apcachef.networking.ApiService;
import com.apcachef.util.SharedPrefsHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAccountActivity extends BaseActivity implements OnListItemClickListener {
    ImageView img_back;
    TextView tv_desc, tv_get_renew;
    EditText edit_name, edit_mobile, edit_old_pass, edit_new_pass, edit_confirm_new_pass;
    TextView tv_title, tv_country_code, tv_email;
    Button btn_buy, btn_save, btn_update;
    SharedPrefsHelper sharedPrefsHelper;
    Dialog dialog;
    String countryCode;
    CountryCodePicker countryCodePicker;
    List<CountryCodePicker> mCountryList = new ArrayList<>();
    CountryCodeAdapter countryCodeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        sharedPrefsHelper = new SharedPrefsHelper(this);

        initViews();
        tv_title.setText("My Account");
        tv_get_renew = findViewById(R.id.tv_get_renew);

        if (sharedPrefsHelper.getPurchaseDate().equals("2020")){
            tv_get_renew.setVisibility(View.VISIBLE);
        }else{
            tv_get_renew.setVisibility(View.GONE);
        }
        countryCode = sharedPrefsHelper.getCountryCode();
        tv_country_code.setText(countryCode);

        countryCodePicker = new CountryCodePicker();
        mCountryList = countryCodePicker.getmCountryList();

        tv_country_code.setOnClickListener(view -> {
            openCountryCodePicker();
        });

        img_back.setOnClickListener(view -> finish());

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


    }

    private void openCountryCodePicker() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_country_picker);
        dialog.setCancelable(true);

        TextView dialogTitle = dialog.findViewById(R.id.dialogTitle);
        dialogTitle.setText("Select country");
        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerView);
        countryCodeAdapter = new CountryCodeAdapter(getApplicationContext(), mCountryList, this);
        recyclerView.setAdapter(countryCodeAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dialog.show();
    }

    private void initViews() {
        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);
        tv_desc = findViewById(R.id.tv_desc);
        tv_email = findViewById(R.id.tv_email);
        tv_country_code = findViewById(R.id.tv_country_code);
        edit_name = findViewById(R.id.edit_name);
        edit_mobile = findViewById(R.id.edit_mobile);
        btn_save = findViewById(R.id.btn_save);
        btn_buy = findViewById(R.id.btn_buy);

        edit_old_pass = findViewById(R.id.edit_old_pass);
        edit_new_pass = findViewById(R.id.edit_new_pass);
        edit_confirm_new_pass = findViewById(R.id.edit_confirm_new_pass);
        btn_update = findViewById(R.id.btn_update);

        tv_email.setText(sharedPrefsHelper.getEmail());
        edit_name.setText(sharedPrefsHelper.getName());
        edit_mobile.setText(sharedPrefsHelper.getMobile());


        btn_update.setOnClickListener(view -> {
            if (edit_old_pass.getText().toString().trim().length() == 0) {
                Toast.makeText(getApplicationContext(), "Please enter current password", Toast.LENGTH_SHORT).show();
            } else if (edit_new_pass.getText().toString().trim().length() == 0) {
                Toast.makeText(getApplicationContext(), "Please enter new password", Toast.LENGTH_SHORT).show();
            } else if (edit_confirm_new_pass.getText().toString().trim().length() == 0) {
                Toast.makeText(getApplicationContext(), "Please re-enter new password", Toast.LENGTH_SHORT).show();
            } else if (!edit_new_pass.getText().toString().trim().equals(edit_confirm_new_pass.getText().toString().trim())) {
                Toast.makeText(getApplicationContext(), "New password and confirm password not same", Toast.LENGTH_SHORT).show();
            } else {
                updatePassword();
            }
        });

        btn_save.setOnClickListener(view -> {
            if (edit_name.getText().toString().trim().length() == 0) {
                Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_SHORT).show();
            } else if (edit_mobile.getText().toString().trim().length() == 0) {
                Toast.makeText(getApplicationContext(), "Please enter mobile number", Toast.LENGTH_SHORT).show();
            } else {
                updateProfile();
            }
        });


        btn_buy.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
            intent.putExtra("showMode", true);
            startActivity(intent);
        });

        if (sharedPrefsHelper.isSubscribed()) {
            tv_desc.setText("You are subscribed, you can watch all courses");
            btn_buy.setVisibility(View.GONE);
        }

    }

    private void updatePassword() {
        showProgressDialog();
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setUserId(Integer.parseInt(sharedPrefsHelper.getUserId()));
        changePasswordRequest.setPassword(edit_old_pass.getText().toString().trim());
        changePasswordRequest.setConfirmPassword(edit_new_pass.getText().toString().trim());
        Call<ChangePasswordResponse> call = apiService.changePassword(changePasswordRequest);

        call.enqueue(new Callback<ChangePasswordResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                hideProgressDialog();
                if (response.body().getStatus() == 1) {
                    String msg = response.body().getMsg();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }

    private void updateProfile() {
        showProgressDialog();
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        UpdateProfileRequest changePasswordRequest = new UpdateProfileRequest();
        changePasswordRequest.setUserId(Integer.parseInt(sharedPrefsHelper.getUserId()));
        changePasswordRequest.setName(edit_name.getText().toString().trim());
        changePasswordRequest.setMobile(edit_mobile.getText().toString().trim());
        changePasswordRequest.setCountryCode(countryCode);
        Call<UpdateProfileResponse> call = apiService.updateProfile(changePasswordRequest);

        call.enqueue(new Callback<UpdateProfileResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                hideProgressDialog();
                if (response.body().getStatus() == 1) {
                    String msg = response.body().getMsg();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

                    sharedPrefsHelper.setName(response.body().getData().getFirstName());
                    sharedPrefsHelper.setMobile(response.body().getData().getMobile());
                    sharedPrefsHelper.setCountryCode(response.body().getData().getCountryCode());
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }

    @Override
    public void onItemClick(int position, String type) {
        dialog.dismiss();
        tv_country_code.setText(mCountryList.get(position).getCountryCode());
        countryCode = mCountryList.get(position).getCountryCode();
    }
}