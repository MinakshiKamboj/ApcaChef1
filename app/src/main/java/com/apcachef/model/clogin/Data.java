package com.apcachef.model.clogin;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("temp_login")
	private int tempLogin;

	@SerializedName("data")
	private Logindata logindata;

	public void setTempLogin(int tempLogin){
		this.tempLogin = tempLogin;
	}

	public int getTempLogin(){
		return tempLogin;
	}

	public void setLogindata(Logindata logindata){
		this.logindata = logindata;
	}

	public Logindata getLogindata(){
		return logindata;
	}
}