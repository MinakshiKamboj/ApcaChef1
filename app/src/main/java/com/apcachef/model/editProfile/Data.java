package com.apcachef.model.editProfile;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("user_id")
	private int userId;

	@SerializedName("countryCode")
	private String countryCode;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("first_name")
	private String firstName;

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}
}