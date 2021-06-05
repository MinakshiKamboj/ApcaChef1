package com.apcachef.model.egift;

import com.google.gson.annotations.SerializedName;

public class SendEGiftResponse{

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private GiftData giftData;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setGiftData(GiftData giftData){
		this.giftData = giftData;
	}

	public GiftData getGiftData(){
		return giftData;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}