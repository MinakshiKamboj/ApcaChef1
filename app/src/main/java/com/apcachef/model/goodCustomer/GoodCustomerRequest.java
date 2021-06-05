package com.apcachef.model.goodCustomer;

import com.google.gson.annotations.SerializedName;

public class GoodCustomerRequest{

	@SerializedName("user_id")
	private String userId;

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}
}