package com.apcachef.model;

public class RenewStatusRequest {
    public RenewStatusRequest(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    String user_id;
    String code;

    public RenewStatusRequest(String code, String purchase_date) {
        this.code = code;
        this.purchase_date = purchase_date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    String purchase_date;
}
