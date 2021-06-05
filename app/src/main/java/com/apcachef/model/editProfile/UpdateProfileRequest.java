package com.apcachef.model.editProfile;

import com.google.gson.annotations.SerializedName;

public class UpdateProfileRequest{

	@SerializedName("country_code")
	private String countryCode;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("name")
	private String name;

	@SerializedName("mobile")
	private String mobile;

	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}
}