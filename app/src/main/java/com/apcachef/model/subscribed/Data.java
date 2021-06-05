package com.apcachef.model.subscribed;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("is_subscribed")
	private int isSubscribed;

	public void setIsSubscribed(int isSubscribed){
		this.isSubscribed = isSubscribed;
	}

	public int getIsSubscribed(){
		return isSubscribed;
	}
}