package com.apcachef.model.aboutUs;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("content")
	private String content;

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}
}