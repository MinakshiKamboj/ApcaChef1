package com.apcachef.model.contact;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("address")
	private String address;

	@SerializedName("mobile_1")
	private String mobile1;

	@SerializedName("mobile_2")
	private String mobile2;

	@SerializedName("email")
	private String email;

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setMobile1(String mobile1){
		this.mobile1 = mobile1;
	}

	public String getMobile1(){
		return mobile1;
	}

	public void setMobile2(String mobile2){
		this.mobile2 = mobile2;
	}

	public String getMobile2(){
		return mobile2;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
}