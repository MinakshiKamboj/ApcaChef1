package com.apcachef.model.egift;

import com.google.gson.annotations.SerializedName;

public class GiftData{

	@SerializedName("sender_det")
	private SenderDet senderDet;

	@SerializedName("egift_user_tbl_id")
	private int egiftUserTblId;

	@SerializedName("mode_type")
	private String modeType;

	@SerializedName("egift_user_id")
	private int egiftUserId;

	public void setSenderDet(SenderDet senderDet){
		this.senderDet = senderDet;
	}

	public SenderDet getSenderDet(){
		return senderDet;
	}

	public void setEgiftUserTblId(int egiftUserTblId){
		this.egiftUserTblId = egiftUserTblId;
	}

	public int getEgiftUserTblId(){
		return egiftUserTblId;
	}

	public void setModeType(String modeType){
		this.modeType = modeType;
	}

	public String getModeType(){
		return modeType;
	}

	public void setEgiftUserId(int egiftUserId){
		this.egiftUserId = egiftUserId;
	}

	public int getEgiftUserId(){
		return egiftUserId;
	}
}