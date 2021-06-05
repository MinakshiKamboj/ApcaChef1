package com.apcachef.model.detail;

import com.google.gson.annotations.SerializedName;

public class CourseDetailRequest{

	@SerializedName("user_id")
	private String userId;

	@SerializedName("id")
	private String id;

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}