package com.apcachef.model.myCourses;

import com.google.gson.annotations.SerializedName;

public class SubsItem{

	@SerializedName("short_description")
	private String shortDescription;

	@SerializedName("img")
	private String img;

	@SerializedName("video_url")
	private String videoUrl;

	@SerializedName("category_id")
	private String categoryId;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("slug")
	private String slug;

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

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}
}