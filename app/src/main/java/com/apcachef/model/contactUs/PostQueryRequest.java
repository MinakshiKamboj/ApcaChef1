package com.apcachef.model.contactUs;

import com.google.gson.annotations.SerializedName;

public class PostQueryRequest{

	@SerializedName("email_id")
	private String emailId;

	@SerializedName("fname")
	private String fname;

	@SerializedName("lname")
	private String lname;

	@SerializedName("phone_num")
	private int phoneNum;

	@SerializedName("message")
	private String message;

	public void setEmailId(String emailId){
		this.emailId = emailId;
	}

	public String getEmailId(){
		return emailId;
	}

	public void setFname(String fname){
		this.fname = fname;
	}

	public String getFname(){
		return fname;
	}

	public void setLname(String lname){
		this.lname = lname;
	}

	public String getLname(){
		return lname;
	}

	public void setPhoneNum(int phoneNum){
		this.phoneNum = phoneNum;
	}

	public int getPhoneNum(){
		return phoneNum;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}