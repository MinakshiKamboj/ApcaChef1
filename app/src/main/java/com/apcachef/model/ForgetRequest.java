package com.apcachef.model;

import com.google.gson.annotations.SerializedName;

public class ForgetRequest{

	@SerializedName("email")
	private String email;

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
}