package com.apcachef.model.detail;

import com.google.gson.annotations.SerializedName;

public class Row{

	@SerializedName("short_description")
	private String shortDescription;

	@SerializedName("img")
	private String img;

	@SerializedName("code")
	private String code;

	@SerializedName("line_usd")
	private String lineUsd;

	@SerializedName("line_myr")
	private String lineMyr;

	@SerializedName("description")
	private String description;

	@SerializedName("title")
	private String title;

	@SerializedName("line_sm")
	private String lineSm;

	@SerializedName("video_url")
	private String videoUrl;

	@SerializedName("category_id")
	private String categoryId;

	@SerializedName("price")
	private String price;

	@SerializedName("name")
	private String name;

	@SerializedName("description_new")
	private String descriptionNew;

	@SerializedName("id")
	private String id;

	@SerializedName("line_inr")
	private String lineInr;

	@SerializedName("slug")
	private String slug;

	public String getChef_about() {
		return chef_about;
	}

	@SerializedName("chef_about")
	private String chef_about;

	public void setShortDescription(String shortDescription){
		this.shortDescription = shortDescription;
	}

	public String getShortDescription(){
		return shortDescription;
	}

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setLineUsd(String lineUsd){
		this.lineUsd = lineUsd;
	}

	public String getLineUsd(){
		return lineUsd;
	}

	public void setLineMyr(String lineMyr){
		this.lineMyr = lineMyr;
	}

	public String getLineMyr(){
		return lineMyr;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setLineSm(String lineSm){
		this.lineSm = lineSm;
	}

	public String getLineSm(){
		return lineSm;
	}

	public void setVideoUrl(String videoUrl){
		this.videoUrl = videoUrl;
	}

	public String getVideoUrl(){
		return videoUrl;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescriptionNew(String descriptionNew){
		this.descriptionNew = descriptionNew;
	}

	public String getDescriptionNew(){
		return descriptionNew;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setLineInr(String lineInr){
		this.lineInr = lineInr;
	}

	public String getLineInr(){
		return lineInr;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}
}