package com.apcachef.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Paint;
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
import com.apcachef.Services.ServiceGenerator;
import com.apcachef.adapter.PaymentModeAdapter;
import com.apcachef.base.BaseActivity;
import com.apcachef.constant.AppConstants;
import com.apcachef.model.RenewPayments.RenewPaymentRequest;
import com.apcachef.model.RenewPayments.RenewPaymentStatus;
import com.apcachef.model.RenewStatus;
import com.apcachef.model.RenewStatusRequest;
import com.apcachef.model.payment.ModeList;
import com.apcachef.model.payment.PaymentRequest;
import com.apcachef.model.payment.PaymentResponse;
import com.apcachef.networking.ApiClient;
import com.apcachef.networking.ApiService;
import com.apcachef.util.SharedPrefsHelper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RenewPaymentActivity extends BaseActivity {
    TextView tv_title;
    ImageView img_back;
    LinearLayout linear_amt, linear_amt1, parent_linear_view;
    TextView tv_payment_amount, txt_plan, tv_payment_amount1, tv_payment_amount2;
    TextView  btn_pay;
    TextView tv_mode, txt_daruPlan;
    boolean showMode, payForGift;
    PopupWindow popupWindow = new PopupWindow();
    PaymentModeAdapter paymentModeAdapter;
    Context mContext;
    String modeID = "", eGiftUserId = "", eGiftTblId;
    SharedPrefsHelper sharedPrefsHelper;
    EditText edit_card_number, etCardExpiry, edit_cvv;

    List<ModeList> modeLists = new ArrayList<>();
    private static final String TAG = "RenewPaymentActivity";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renew_payment);
        mContext = this;
        progressDialog=new ProgressDialog(RenewPaymentActivity.this);
        progressDialog.setMessage("Please wait...");
        sharedPrefsHelper = new SharedPrefsHelper(this);  //e9d3b7dbb0724a56

        showMode = getIntent().getBooleanExtra("showMode", true);
        modeID = getIntent().getStringExtra("modeID");

        eGiftUserId = getIntent().getStringExtra("eGiftUserId");
        eGiftTblId = getIntent().getStringExtra("eGiftTblId");
        payForGift = getIntent().getBooleanExtra("payForGift", false);

        edit_cvv = findViewById(R.id.edit_cvv);
        txt_daruPlan = findViewById(R.id.txt_daruPlan);
        edit_card_number = findViewById(R.id.edit_card_number);
        etCardExpiry = findViewById(R.id.etCardExpiry);
        tv_mode = findViewById(R.id.tv_mode);
        btn_pay = findViewById(R.id.btn_pay);
        txt_plan = findViewById(R.id.txt_plan);
        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);
        linear_amt = findViewById(R.id.linear_amt);
        linear_amt1 = findViewById(R.id.linear_amt1);
        tv_payment_amount = findViewById(R.id.tv_payment_amount);
        tv_payment_amount1 = findViewById(R.id.tv_payment_amount1);
        tv_payment_amount2 = findViewById(R.id.tv_payment_amount2);
        parent_linear_view = findViewById(R.id.parent_linear_view);
        parent_linear_view.setVisibility(View.GONE);
        tv_title.setText("Renew Now");
        showMode = getIntent().getBooleanExtra("showMode", true);

        txt_plan.setPaintFlags(txt_plan.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        img_back.setOnClickListener(view -> {
            finish();
        });
        if (showMode) {
            tv_mode.setVisibility(View.GONE);
        } else {
            tv_mode.setVisibility(View.GONE);
        }
        linear_amt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_daruPlan.setText("2");
                tv_payment_amount1.setText("INR ");
                tv_payment_amount.setText("22500");
                tv_payment_amount2.setText(" for straight 2 years");
                parent_linear_view.setVisibility(View.VISIBLE);
                linear_amt.setBackground(ContextCompat.getDrawable(RenewPaymentActivity.this, R.drawable.card_background));
                linear_amt1.setBackground(ContextCompat.getDrawable(RenewPaymentActivity.this, R.drawable.card_background1));
            }
        });

        linear_amt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_daruPlan.setText("4");
                tv_payment_amount1.setText("INR ");
                tv_payment_amount.setText("44500");
                tv_payment_amount2.setText(" for straight 4 years");
                parent_linear_view.setVisibility(View.VISIBLE);
                linear_amt.setBackground(ContextCompat.getDrawable(RenewPaymentActivity.this, R.drawable.card_background1));
                linear_amt1.setBackground(ContextCompat.getDrawable(RenewPaymentActivity.this, R.drawable.card_background));
            }
        });

        tv_mode.setOnClickListener(view -> {
            popupWindow = popupWindowPaymentMode();
            popupWindow.showAsDropDown(view, 0, 0);
        });
        getModeList();
        tv_payment_amount.setText(AppConstants.symbol + " " + AppConstants.price);

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

        btn_pay.setOnClickListener(view -> {

       //     makeRenewRequest();

         /*   if (tv_mode.isShown() && tv_mode.getText().toString().length() == 0) {
                Toast.makeText(getApplicationContext(), "Select Mode", Toast.LENGTH_SHORT).show();
            } else*/
                if (edit_card_number.getText().toString().length() < 19) {
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

                    makeRenewRequest(cardNumber, exp_month, exp_year, c_number);
                //showAlertDialog("Payment success");
            }

        });


    }
    public void hideProgressDialog() {
        if (dialog != null && dialog.isShowing())
            dialog.cancel();
    }

    private void makeRenewRequest(String cardNumber, String exp_month, String exp_year, String number) {
        progressDialog.show();
        ApiService interUserdata = ServiceGenerator.createService(ApiService.class);
 //       interUserdata.getRenewPaymentStatus( new RenewPaymentRequest(51,100,2,2,
   //             "inr","4242424242424242","12","2021","314")).enqueue(new Callback<RenewPaymentStatus>() {

        interUserdata.getRenewPaymentStatus( new RenewPaymentRequest(Integer.parseInt(sharedPrefsHelper.getUserId()),Integer.parseInt(tv_payment_amount.getText().toString())
                ,Integer.parseInt(txt_daruPlan.getText().toString()),2,
                AppConstants.code,cardNumber,exp_month,exp_year,number)).enqueue(new Callback<RenewPaymentStatus>() {
            public void onResponse(Call<RenewPaymentStatus> call, Response<RenewPaymentStatus> response) {
                progressDialog.hide();
                Log.e("aresponse", String.valueOf(response));
                Log.d(TAG, "onResponse: "+new Gson().toJson(response.body()));
                Log.d(TAG, "onResponse: "+response.message());
                Log.d(TAG, "onResponse: "+response.code());
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 1) {
                        showAlertDialog(response.body().getMsg());
                    } else {
                        showSnackBar(getResources().getString(R.string.errorMessage));

                    } }
            }
            @Override
            public void onFailure(Call<RenewPaymentStatus> call, Throwable t) {
                progressDialog.hide();
                Log.d(TAG, "onFailure: "+t.getMessage());
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


    private void getModeList() {
        modeLists.clear();
        modeLists.add(new ModeList("Pastry", "1"));
        modeLists.add(new ModeList("Culinary", "2"));
        modeLists.add(new ModeList("Eggless", "3"));
        modeLists.add(new ModeList("Master Class", "4"));
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
}