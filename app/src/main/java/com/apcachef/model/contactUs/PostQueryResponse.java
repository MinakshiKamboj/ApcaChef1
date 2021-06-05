package com.apcachef.model.contactUs;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PostQueryResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private List<Object> data;

	@SerializedName("status")
	private int status;

	public String getMsg(){
		return msg;
	}

	public List<Object> getData(){
		return data;
	}

	public int getStatus(){
		return status;
	}
}