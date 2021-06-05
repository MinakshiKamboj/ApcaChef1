package com.apcachef.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.apcachef.constant.AppConstants;

public class SharedPrefsHelper {
    private SharedPreferences mSharedPreferences;

    public SharedPrefsHelper(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(AppConstants.PREFENCENAME, Context.MODE_PRIVATE);
        mSharedPreferences = sharedPref;
    }

    public boolean isLogin() {
        return mSharedPreferences.getBoolean("login", false);
    }

    public void setIsLogin(boolean isLogin) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean("login", isLogin);
        editor.apply();
    }

    public boolean isLoginGoodCustomer() {
        return mSharedPreferences.getBoolean("loginGoodCustomer", false);
    }

    public void setIsLoginGoodCustomer(boolean isLogin) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean("loginGoodCustomer", isLogin);
        editor.apply();
    }

    public void setUserIdGoodCustomer(String userId) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("userIdGoodCustomer", userId);
        editor.apply();
    }

    public String getUserIdGoodCustomer() {
        return mSharedPreferences.getString("userIdGoodCustomer", "");
    }

    public boolean isSubscribed() {
        return mSharedPreferences.getBoolean("subscribed", false);
    }

    public void setIsSubscribed(boolean isSubscribed) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean("subscribed", isSubscribed);
        editor.apply();
    }

    public void setUserId(String userId) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("userId", userId);
        editor.apply();
    }

    public String getUserId() {
        return mSharedPreferences.getString("userId", "");
    }

    public void setName(String name) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("name", name);
        editor.apply();
    }

    public String getName() {
        return mSharedPreferences.getString("name", "");
    }

    public void setMobile(String mobile) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("mobile", mobile);
        editor.apply();
    }

    public String getMobile() {
        return mSharedPreferences.getString("mobile", "");
    }

    public void setEmail(String email) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("email", email);
        editor.apply();
    }

    public String getEmail() {
        return mSharedPreferences.getString("email", "");
    }

    public void setPurchaseDate(String purchase_date) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("purchase_date", purchase_date);
        editor.apply();
    }

    public String getPurchaseDate() {
        return mSharedPreferences.getString("purchase_date", "");
    }


    public void setCountryCode(String email) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("countryCode", email);
        editor.apply();
    }

    public String getCountryCode() {
        return mSharedPreferences.getString("countryCode", "");
    }

    public void logout() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
