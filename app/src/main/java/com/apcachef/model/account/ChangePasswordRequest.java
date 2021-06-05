package com.apcachef.model.account;

import com.google.gson.annotations.SerializedName;

public class ChangePasswordRequest {

	@SerializedName("password")
	private String password;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("confirm_password")
	private String confirmPassword;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setConfirmPassword(String confirmPassword){
		this.confirmPassword = confirmPassword;
	}

	public String getConfirmPassword(){
		return confirmPassword;
	}
}