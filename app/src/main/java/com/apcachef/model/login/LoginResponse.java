package com.apcachef.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private Data data;

	@SerializedName("status")
	private int status;

	public String getMsg(){
		return msg;
	}

	public Data getData(){
		return data;
	}

	public int getStatus(){
		return status;
	}
}