package com.apcachef.model.myCourses;

import java.util.List;

import com.apcachef.model.home.MasterClassItem;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("master_chef_series")
	private List<Object> masterChefSeries;

	@SerializedName("master_class_img")
	private String masterClassImg;

	@SerializedName("master_class")
	private List<MasterClassItem> masterClass;

	@SerializedName("pastry")
	private Pastry pastry;

	@SerializedName("eggless")
	private Eggless eggless;

	@SerializedName("culinary")
	private Culinary culinary;

	@SerializedName("shorts")
	private List<ShortsItem> shorts;

	public void setMasterChefSeries(List<Object> masterChefSeries){
		this.masterChefSeries = masterChefSeries;
	}

	public List<Object> getMasterChefSeries(){
		return masterChefSeries;
	}

	public void setMasterClassImg(String masterClassImg){
		this.masterClassImg = masterClassImg;
	}

	public String getMasterClassImg(){
		return masterClassImg;
	}

	public void setMasterClass(List<MasterClassItem> masterClass){
		this.masterClass = masterClass;
	}

	public List<MasterClassItem> getMasterClass(){
		return masterClass;
	}

	public void setPastry(Pastry pastry){
		this.pastry = pastry;
	}

	public Pastry getPastry(){
		return pastry;
	}

	public void setEggless(Eggless eggless){
		this.eggless = eggless;
	}

	public Eggless getEggless(){
		return eggless;
	}

	public void setCulinary(Culinary culinary){
		this.culinary = culinary;
	}

	public Culinary getCulinary(){
		return culinary;
	}

	public void setShorts(List<ShortsItem> shorts){
		this.shorts = shorts;
	}

	public List<ShortsItem> getShorts(){
		return shorts;
	}
}