package com.apcachef.model.home;

import com.google.gson.annotations.SerializedName;

public class TestimonialItem{

	@SerializedName("img")
	private String img;

	@SerializedName("name")
	private String name;

	@SerializedName("headline")
	private String headline;

	@SerializedName("desc")
	private String desc;

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setHeadline(String headline){
		this.headline = headline;
	}

	public String getHeadline(){
		return headline;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
	}
}