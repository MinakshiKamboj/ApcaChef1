package com.apcachef.model.egift;

import com.google.gson.annotations.SerializedName;

public class SendEGiftRequest{

	@SerializedName("from_email")
	private String fromEmail;

	@SerializedName("countryCode")
	private String countryCode;

	@SerializedName("name")
	private String name;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("mode_type")
	private String modeType;

	@SerializedName("from_name")
	private String fromName;

	@SerializedName("message")
	private String message;

	@SerializedName("email")
	private String email;

	public void setFromEmail(String fromEmail){
		this.fromEmail = fromEmail;
	}

	public String getFromEmail(){
		return fromEmail;
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

	public void setModeType(String modeType){
		this.modeType = modeType;
	}

	public String getModeType(){
		return modeType;
	}

	public void setFromName(String fromName){
		this.fromName = fromName;
	}

	public String getFromName(){
		return fromName;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
}