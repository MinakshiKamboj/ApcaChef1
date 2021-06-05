package com.apcachef.model;

public class RenewStatus {
    int  status;
    String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RenewStatusRequest getData() {
        return data;
    }

    public void setData(RenewStatusRequest data) {
        this.data = data;
    }

    RenewStatusRequest data;


    public RenewStatus(int status, String msg, RenewStatusRequest data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

}
