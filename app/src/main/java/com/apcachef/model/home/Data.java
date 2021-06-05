package com.apcachef.model.home;

import java.util.List;

import com.apcachef.model.MasterChefSeries.MasterChefSeriesItem;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("testimonial")
    private List<TestimonialItem> testimonial;

    @SerializedName("cates")
    private List<CatesItem> cates;

    @SerializedName("usd")
    private Usd usd;

    @SerializedName("myr")
    private Myr myr;

    @SerializedName("inr")
    private Inr inr;

    @SerializedName("chefs")
    private List<ChefsItem> chefs;

    @SerializedName("home_video")
    private String homeVideo;

    @SerializedName("master_class")
    private List<MasterClassItem> masterClass;

    public void setMasterChefSeriesItems(List<MasterChefSeriesItem> masterChefSeriesItems) {
        this.masterChefSeriesItems = masterChefSeriesItems;
    }

    public List<MasterChefSeriesItem> getMasterChefSeriesItems() {
        return masterChefSeriesItems;
    }

    @SerializedName("master_chef_series")
    private List<MasterChefSeriesItem> masterChefSeriesItems;

    public void setTestimonial(List<TestimonialItem> testimonial) {
        this.testimonial = testimonial;
    }

    public List<TestimonialItem> getTestimonial() {
        return testimonial;
    }

    public void setCates(List<CatesItem> cates) {
        this.cates = cates;
    }

    public List<CatesItem> getCates() {
        return cates;
    }

    public void setUsd(Usd usd) {
        this.usd = usd;
    }

    public Usd getUsd() {
        return usd;
    }

    public void setMyr(Myr myr) {
        this.myr = myr;
    }

    public Myr getMyr() {
        return myr;
    }

    public void setInr(Inr inr) {
        this.inr = inr;
    }

    public Inr getInr() {
        return inr;
    }

    public void setChefs(List<ChefsItem> chefs) {
        this.chefs = chefs;
    }

    public List<ChefsItem> getChefs() {
        return chefs;
    }

    public void setHomeVideo(String homeVideo) {
        this.homeVideo = homeVideo;
    }

    public String getHomeVideo() {
        return homeVideo;
    }

    public void setMasterClass(List<MasterClassItem> masterClass) {
        this.masterClass = masterClass;
    }

    public List<MasterClassItem> getMasterClass() {
        return masterClass;
    }

}