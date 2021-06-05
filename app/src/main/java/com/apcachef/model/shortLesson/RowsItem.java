package com.apcachef.model.shortLesson;

import com.google.gson.annotations.SerializedName;

public class RowsItem{

	@SerializedName("seo_keyword")
	private Object seoKeyword;

	@SerializedName("img")
	private String img;

	@SerializedName("video_url")
	private String videoUrl;

	@SerializedName("seo_desc")
	private Object seoDesc;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("description")
	private String description;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("slug")
	private String slug;

	@SerializedName("status")
	private String status;

	public void setSeoKeyword(Object seoKeyword){
		this.seoKeyword = seoKeyword;
	}

	public Object getSeoKeyword(){
		return seoKeyword;
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

	public void setSeoDesc(Object seoDesc){
		this.seoDesc = seoDesc;
	}

	public Object getSeoDesc(){
		return seoDesc;
	}

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
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

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}