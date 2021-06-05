package com.apcachef.model;

import com.google.gson.annotations.SerializedName;

public class ForgetPasswordResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}