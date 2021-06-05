package com.apcachef.model.payment;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("txn_id")
	private String txnId;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("payment_id")
	private int paymentId;

	@SerializedName("expiry_date")
	private String expiryDate;

	@SerializedName("purchase_date")
	private String purchaseDate;

	@SerializedName("payment_req_id")
	private String paymentReqId;

	public void setTxnId(String txnId){
		this.txnId = txnId;
	}

	public String getTxnId(){
		return txnId;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setPaymentId(int paymentId){
		this.paymentId = paymentId;
	}

	public int getPaymentId(){
		return paymentId;
	}

	public void setExpiryDate(String expiryDate){
		this.expiryDate = expiryDate;
	}

	public String getExpiryDate(){
		return expiryDate;
	}

	public void setPurchaseDate(String purchaseDate){
		this.purchaseDate = purchaseDate;
	}

	public String getPurchaseDate(){
		return purchaseDate;
	}

	public void setPaymentReqId(String paymentReqId){
		this.paymentReqId = paymentReqId;
	}

	public String getPaymentReqId(){
		return paymentReqId;
	}
}