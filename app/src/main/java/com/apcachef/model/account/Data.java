package com.apcachef.model.account;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("user_id")
	private int userId;

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}
}