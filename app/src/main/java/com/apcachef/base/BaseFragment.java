package com.apcachef.base;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;

import androidx.fragment.app.Fragment;


abstract public class BaseFragment extends Fragment {
    ProgressDialog dialog;


    public void startActivity(Class activity) {

        Intent intent = new Intent(getActivity(), activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
        } else {
            startActivity(intent);
        }
    }

    public void startActivityWithFinish(Class activity) {
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
        getActivity().finish();

    }

    public void startActivityWithClearTask(Class activity) {
        Intent intent = new Intent(getActivity(), activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition

            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
        } else {
            // Swap without transition
            startActivity(intent);
        }
    }

    /*public void dialogwithdismiss(String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setIcon(R.drawable.warning);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    public void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok, (dialog, id) -> dialog.dismiss());
        builder.show();
    }


    public void showProgressDialog() {


        dialog = new ProgressDialog(getActivity());
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
        View parentLayout = getActivity().findViewById(android.R.id.content);
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
        for (NetworkInfo ni : ((ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE)).getAllNetworkInfo()) {
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

    public void onBackPressed() {
    }


    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public void okSelectDialog(String strTitle, String strMessage, InterfaceOk interfaceOk) {
        final Dialog dialog = new Dialog(getActivity());
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

}
