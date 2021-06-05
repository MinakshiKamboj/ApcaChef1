package com.apcachef.activity;

import androidx.appcompat.app.AlertDialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.apcachef.R;
import com.apcachef.adapter.PaymentModeAdapter;
import com.apcachef.base.BaseActivity;
import com.apcachef.constant.AppConstants;
import com.apcachef.model.payment.ModeList;
import com.apcachef.model.payment.PaymentRequest;
import com.apcachef.model.payment.PaymentResponse;
import com.apcachef.networking.ApiClient;
import com.apcachef.networking.ApiService;
import com.apcachef.util.SharedPrefsHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends BaseActivity {
    ImageView img_back;
    Context mContext;
    TextView tv_title, tv_mode;
    EditText edit_card_number, etCardExpiry, edit_cvv;
    TextView tv_payment_amount, btn_pay;
    LinearLayout parent_linear_view;
    SharedPrefsHelper sharedPrefsHelper;
    String modeID = "", eGiftUserId = "", eGiftTblId;
    List<ModeList> modeLists = new ArrayList<>();
    PopupWindow popupWindow = new PopupWindow();
    PaymentModeAdapter paymentModeAdapter;
    boolean showMode, payForGift;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        mContext = this;

        sharedPrefsHelper = new SharedPrefsHelper(this);  //e9d3b7dbb0724a56

        showMode = getIntent().getBooleanExtra("showMode", false);
        modeID = getIntent().getStringExtra("modeID");

        eGiftUserId = getIntent().getStringExtra("eGiftUserId");
        eGiftTblId = getIntent().getStringExtra("eGiftTblId");
        payForGift = getIntent().getBooleanExtra("payForGift", false);

        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);
        tv_mode = findViewById(R.id.tv_mode);
        tv_payment_amount = findViewById(R.id.tv_payment_amount);
        edit_card_number = findViewById(R.id.edit_card_number);
        etCardExpiry = findViewById(R.id.etCardExpiry);
        edit_cvv = findViewById(R.id.edit_cvv);
        btn_pay = findViewById(R.id.btn_pay);
        parent_linear_view = findViewById(R.id.parent_linear_view);

        if (showMode) {
            tv_mode.setVisibility(View.VISIBLE);
        } else {
            tv_mode.setVisibility(View.GONE);
        }

        tv_title.setText("Payment");

        img_back.setOnClickListener(view -> {
            finish();
        });

        tv_mode.setOnClickListener(view -> {
            popupWindow = popupWindowPaymentMode();
            popupWindow.showAsDropDown(view, 0, 0);
        });

        getModeList();

        tv_payment_amount.setText(AppConstants.symbol + " " + AppConstants.price);

        btn_pay.setOnClickListener(view -> {
            if (tv_mode.isShown() && tv_mode.getText().toString().length() == 0) {
                Toast.makeText(getApplicationContext(), "Select Mode", Toast.LENGTH_SHORT).show();
            } else if (edit_card_number.getText().toString().length() < 19) {
                Toast.makeText(getApplicationContext(), "Enter Valid Card Number", Toast.LENGTH_SHORT).show();
            } else if (etCardExpiry.getText().toString().length() < 7) {
                Toast.makeText(getApplicationContext(), "Enter expiry date", Toast.LENGTH_SHORT).show();
            } else if (edit_cvv.getText().toString().length() < 3) {
                Toast.makeText(getApplicationContext(), "Enter CVV Number", Toast.LENGTH_SHORT).show();
            } else {
                String cardNumber = edit_card_number.getText().toString().trim().replaceAll("\\s", "");
                String[] cardExpiry = etCardExpiry.getText().toString().trim().split("/");
                String exp_month = cardExpiry[0];
                String exp_year = cardExpiry[1];
                String c_number = edit_cvv.getText().toString().trim();
                Log.e("cardNumber ", cardNumber + " exp_Month " + exp_month + " exp_Year " + exp_year);

                processForPayment(cardNumber, exp_month, exp_year, c_number);
                //showAlertDialog("Payment success");
            }

        });

        edit_card_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                int len = s.toString().length();

                if (before == 0 && (len == 4 || len == 9 || len == 14))
                    edit_card_number.append(" ");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etCardExpiry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                int len = s.toString().length();

                if (before == 0 && (len == 2))
                    etCardExpiry.append("/");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getModeList() {
        modeLists.clear();
        modeLists.add(new ModeList("Pastry", "1"));
        modeLists.add(new ModeList("Culinary", "2"));
        modeLists.add(new ModeList("Eggless", "3"));
        modeLists.add(new ModeList("Master Chef Series", "5"));
    }

    private PopupWindow popupWindowPaymentMode() {
        final PopupWindow popupWindow = new PopupWindow(getApplicationContext());
        ListView listViewDogs = new ListView(this);
        paymentModeAdapter = new PaymentModeAdapter(mContext, modeLists);
        listViewDogs.setAdapter(paymentModeAdapter);

        listViewDogs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = ((TextView) view.findViewById(R.id.textView)).getText().toString();
                tv_mode.setText(selectedItemText);
                modeID = modeLists.get(position).getValue();
                Log.e("companyID", modeID);
                popupWindow.dismiss();
            }
        });

        popupWindow.setFocusable(true);
        int width = WindowManager.LayoutParams.MATCH_PARENT;
        popupWindow.setWidth(parent_linear_view.getWidth());
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.spinner_background));

        // set the list view as pop up window content
        popupWindow.setContentView(listViewDogs);

        return popupWindow;
    }

    private void processForPayment(String cardNumber, String exp_month, String exp_year, String number) {
        showProgressDialog();
        ApiClient.retrofit = null;
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setUserId(Integer.parseInt(sharedPrefsHelper.getUserId()));
        paymentRequest.setAmount(Integer.parseInt(AppConstants.price));
        paymentRequest.setCurrency(AppConstants.code);
        paymentRequest.setCardNo(cardNumber);
        paymentRequest.setExpMonth(exp_month);
        paymentRequest.setExpYear(exp_year);
        paymentRequest.setCvv(number);
        paymentRequest.setModeType(modeID);

        if (payForGift) {
            paymentRequest.setEgiftUserId(eGiftUserId);
            paymentRequest.setEgiftUserTblId(eGiftTblId);
        }

        Call<PaymentResponse> call = apiService.getPaymentData(paymentRequest);

        call.enqueue(new Callback<PaymentResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                hideProgressDialog();
                assert response.body() != null;
                if (response.body().getStatus() == 1) {
                    showAlertDialog(response.body().getMsg());
                } else {
                    showSnackBar(getResources().getString(R.string.errorMessage));
                }
            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {
                hideProgressDialog();
                showSnackBar(getResources().getString(R.string.errorMessage));
            }
        });
    }

    public void showAlertDialog(String message) {
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.payment_success_dialog, viewGroup, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        TextView tv_message = dialogView.findViewById(R.id.tv_message);
        tv_message.setText(message);
        Button button = dialogView.findViewById(R.id.buttonOk);
        button.setOnClickListener(view -> {
            startActivityWithClearTask(MainActivity.class);
        });
        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}