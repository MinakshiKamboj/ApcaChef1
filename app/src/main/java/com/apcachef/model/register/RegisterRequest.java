package com.apcachef.model.register;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest{

	@SerializedName("password")
	private String password;

	@SerializedName("countryCode")
	private String countryCode;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("name")
	private String name;

	@SerializedName("reg_type")
	private String regType;

	@SerializedName("promo_code")
	private String promoCode;

	@SerializedName("email")
	private String email;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
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

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setRegType(String regType){
		this.regType = regType;
	}

	public String getRegType(){
		return regType;
	}

	public void setPromoCode(String promoCode){
		this.promoCode = promoCode;
	}

	public String getPromoCode(){
		return promoCode;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
}