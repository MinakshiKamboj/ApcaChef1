package com.apcachef.model.subscribed;

import com.google.gson.annotations.SerializedName;

public class SubscribeRequest{

	@SerializedName("user_id")
	private int userId;

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}
}