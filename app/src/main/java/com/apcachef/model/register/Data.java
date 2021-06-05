package com.apcachef.model.register;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("user_id")
	private int userId;

	@SerializedName("countryCode")
	private String countryCode;

	@SerializedName("name")
	private String name;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("reg_type")
	private int regType;

	@SerializedName("email")
	private String email;

	@SerializedName("is_invalid")
	private boolean isInvalid;

	@SerializedName("is_used")
	private boolean isUsed;

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

	public void setRegType(int regType){
		this.regType = regType;
	}

	public int getRegType(){
		return regType;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setIsInvalid(boolean isInvalid){
		this.isInvalid = isInvalid;
	}

	public boolean isIsInvalid(){
		return isInvalid;
	}

	public void setIsUsed(boolean isUsed){
		this.isUsed = isUsed;
	}

	public boolean isIsUsed(){
		return isUsed;
	}
}