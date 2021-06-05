package com.apcachef.base;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.apcachef.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

abstract public class BaseActivity extends AppCompatActivity {

    public ProgressDialog dialog;


    public void startActivity(Class activity) {

        Intent intent = new Intent(this, activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }
    }

    public void startActivityWithFinish(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();

    }

    public void startActivityWithClearTask(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            startActivity(intent);
        }
    }


    public void showAlertDialog(String message) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", (dialog, id) -> dialog.dismiss());
        builder.show();
    }


    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    public void showProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setTitle("Loading...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(true);
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    public void hideProgressDialog() {
        if (dialog != null && dialog.isShowing())
            dialog.cancel();
    }


    public void showSnackBar(String messsage) {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar
                .make(parentLayout, "Try again!", Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
        snackbar.setActionTextColor(Color.RED);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
        textView.setText(messsage);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    public boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        for (NetworkInfo ni : ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getAllNetworkInfo()) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI") && ni.isConnected()) {
                haveConnectedWifi = true;
            }
            if (ni.getTypeName().equalsIgnoreCase("MOBILE") && ni.isConnected()) {
                haveConnectedMobile = true;
            }
        }
        if (haveConnectedWifi || haveConnectedMobile) {
            return true;
        }
        return false;
    }

   /* public void okSelectDialog(String strTitle, String strMessage, InterfaceOk interfaceOk) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_view);
        dialog.setCanceledOnTouchOutside(false);
        TextView text_title = (TextView) dialog.findViewById(R.id.text_title);
        TextView text_message = (TextView) dialog.findViewById(R.id.text_message);
        TextView tv_ok = (TextView) dialog.findViewById(R.id.tv_ok);
        text_title.setText(strTitle);
        text_message.setText(strMessage);
        dialog.setCancelable(true);
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                interfaceOk.onOk();
            }
        });
        dialog.show();
    }*/

    /*public void openYesNoDialog(String strMessage, InterfaceOk interfaceOk) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_yes_no_dialog);

        TextView text_message = (TextView) dialog.findViewById(R.id.text_message);
        Button buttonYes = (Button) dialog.findViewById(R.id.buttonYes);
        Button buttonNo = (Button) dialog.findViewById(R.id.buttonNo);
        text_message.setText(strMessage);
        dialog.setCancelable(false);

        buttonYes.setOnClickListener(v -> {
            dialog.dismiss();
        });

        buttonNo.setOnClickListener(v -> {
            dialog.dismiss();
            interfaceOk.onOk();
        });
        dialog.show();
    }*/

   /* public void openCustomDialog(String strMessage) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_ok_dialog);

        TextView text_message = (TextView) dialog.findViewById(R.id.text_message);
        Button buttonOk = (Button) dialog.findViewById(R.id.buttonOk);
        text_message.setText(strMessage);
        dialog.setCancelable(false);

        buttonOk.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }*/

}
