package com.apcachef.model.login;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("user_id")
	private String userId;

	@SerializedName("countryCode")
	private String countryCode;

	@SerializedName("name")
	private String name;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("reg_type")
	private String regType;

	@SerializedName("email")
	private String email;

	public String getUserId(){
		return userId;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public String getName(){
		return name;
	}

	public String getMobile(){
		return mobile;
	}

	public String getRegType(){
		return regType;
	}

	public String getEmail(){
		return email;
	}
}