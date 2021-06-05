package com.apcachef.model.payment;

import com.google.gson.annotations.SerializedName;

public class PaymentRequest {

    @SerializedName("amount")
    private int amount;

    @SerializedName("cvv")
    private String cvv;

    @SerializedName("card_no")
    private String cardNo;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("exp_month")
    private String expMonth;

    @SerializedName("currency")
    private String currency;

    @SerializedName("exp_year")
    private String expYear;

    @SerializedName("mode_type")
    private String modeType;

    @SerializedName("egift_user_id")
    private String egiftUserId;

    @SerializedName("egift_user_tbl_id")
    private String egiftUserTblId;

    public int getPlandura() {
        return plandura;
    }

    public void setPlandura(int plandura) {
        this.plandura = plandura;
    }

    @SerializedName("plan_dura")
    private int plandura;

   /* @SerializedName("mode_type")
    private int modeTypeInt;


    public int getModeTypeInt() {
        return modeTypeInt;
    }

    public void setModeTypeInt(int modeTypeInt) {
        this.modeTypeInt = modeTypeInt;
    }
*/

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public String getExpYear() {
        return expYear;
    }

    public String getModeType() {
        return modeType;
    }

    public void setModeType(String modeType) {
        this.modeType = modeType;
    }

    public String getEgiftUserId() {
        return egiftUserId;
    }

    public void setEgiftUserId(String egiftUserId) {
        this.egiftUserId = egiftUserId;
    }

    public String getEgiftUserTblId() {
        return egiftUserTblId;
    }

    public void setEgiftUserTblId(String egiftUserTblId) {
        this.egiftUserTblId = egiftUserTblId;
    }
}