package com.apcachef.model.RenewPayments;

public class RenewPaymentRequest {

      int user_id;
      int amount;
      int plan_dura;
      int mode_type;
      String currency;
      String card_no;
      String exp_month;
      String exp_year;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPlan_dura() {
        return plan_dura;
    }

    public void setPlan_dura(int plan_dura) {
        this.plan_dura = plan_dura;
    }

    public int getMode_type() {
        return mode_type;
    }

    public void setMode_type(int mode_type) {
        this.mode_type = mode_type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getExp_month() {
        return exp_month;
    }

    public void setExp_month(String exp_month) {
        this.exp_month = exp_month;
    }

    public String getExp_year() {
        return exp_year;
    }

    public void setExp_year(String exp_year) {
        this.exp_year = exp_year;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    String cvv;


    public RenewPaymentRequest(int user_id, int amount, int plan_dura, int mode_type,
                               String currency, String card_no, String exp_month,
                               String exp_year, String cvv) {
        this.user_id = user_id;
        this.amount = amount;
        this.plan_dura = plan_dura;
        this.mode_type = mode_type;
        this.currency = currency;
        this.card_no = card_no;
        this.exp_month = exp_month;
        this.exp_year = exp_year;
        this.cvv = cvv;
    }






   /* {
        "user_id":51,
            "amount":123,
            "plan_dura":2,
            "currency":"inr",
            "card_no":"4242424242424242",
            "exp_month":"12",
            "exp_year":"2021",
            "cvv":"314",
            "mode_type":2
    }*/
}
