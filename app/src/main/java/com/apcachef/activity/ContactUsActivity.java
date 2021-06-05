package com.apcachef.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apcachef.R;
import com.apcachef.base.BaseActivity;
import com.apcachef.model.contact.ContactResponse;
import com.apcachef.model.contactUs.PostQueryRequest;
import com.apcachef.model.contactUs.PostQueryResponse;
import com.apcachef.networking.ApiClient;
import com.apcachef.networking.ApiService;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsActivity extends BaseActivity {
    ImageView img_back;
    TextView tv_title;
    LinearLayout parent_view;
    TextView tv_address, tv_mobile, tv_mobile1, tv_email;
    Button btn_send;
    TextInputEditText edit_first_name, edit_last_name, edit_phone, edit_email, edit_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);

        parent_view = findViewById(R.id.parent_view);
        parent_view.setVisibility(View.GONE);

        tv_address = findViewById(R.id.tv_address);
        tv_mobile = findViewById(R.id.tv_mobile);
        tv_mobile1 = findViewById(R.id.tv_mobile1);
        tv_email = findViewById(R.id.tv_email);

        edit_first_name = findViewById(R.id.edit_first_name);
        edit_last_name = findViewById(R.id.edit_last_name);
        edit_phone = findViewById(R.id.edit_phone);
        edit_email = findViewById(R.id.edit_email);
        edit_message = findViewById(R.id.edit_message);
        btn_send = findViewById(R.id.btn_send);

        tv_title.setText("Contact Us");

        tv_mobile.setOnClickListener(view -> {
            openPhoneDialer(1);
        });

        tv_mobile1.setOnClickListener(view -> {
            openPhoneDialer(2);
        });

        img_back.setOnClickListener(view -> finish());

        btn_send.setOnClickListener(view -> postQueryData());

        getContactDetails();

    }

    private void openPhoneDialer(int position) {
        Uri uri = null;
        if (position == 1) {
            uri = Uri.parse("tel:" + tv_mobile.getText().toString());
        } else {
            uri = Uri.parse("tel:" + tv_mobile1.getText().toString());
        }
        // Create the intent and set the data for the
        // intent as the phone number.
        Intent i = new Intent(Intent.ACTION_DIAL, uri);
        try {
            // Launch the Phone app's dialer with a phone
            // number to dial a call.
            startActivity(i);
        } catch (SecurityException s) {
            // show() method display the toast with
            // exception message.
            Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG).show();
        }
    }

    private void postQueryData() {
        showProgressDialog();
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setFname(edit_first_name.getText().toString().trim());
        postQueryRequest.setLname(edit_last_name.getText().toString().trim());
        postQueryRequest.setPhoneNum(Integer.parseInt(edit_phone.getText().toString().trim()));
        postQueryRequest.setEmailId(edit_email.getText().toString().trim());
        postQueryRequest.setMessage(edit_message.getText().toString().trim());
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<PostQueryResponse> call = apiService.postQueryRequest(postQueryRequest);

        call.enqueue(new Callback<PostQueryResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<PostQueryResponse> call, Response<PostQueryResponse> response) {
                hideProgressDialog();
                PostQueryResponse postQueryResponse = response.body();
                if (postQueryResponse != null) {
                    Toast.makeText(getApplicationContext(), postQueryResponse.getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    showSnackBar(getResources().getString(R.string.errorMessage));
                }

            }

            @Override
            public void onFailure(Call<PostQueryResponse> call, Throwable t) {
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }

    private void getContactDetails() {
        showProgressDialog();
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ContactResponse> call = apiService.getContactDetails();

        call.enqueue(new Callback<ContactResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                hideProgressDialog();
                parent_view.setVisibility(View.VISIBLE);
                ContactResponse contactResponse = response.body();
                if (contactResponse != null) {
                    tv_address.setText(contactResponse.getData().getAddress());
                    tv_mobile.setText(contactResponse.getData().getMobile1());
                    tv_mobile1.setText(contactResponse.getData().getMobile2());
                    tv_email.setText(contactResponse.getData().getEmail());
                } else {
                    showSnackBar(getResources().getString(R.string.errorMessage));
                }

            }

            @Override
            public void onFailure(Call<ContactResponse> call, Throwable t) {
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }
}