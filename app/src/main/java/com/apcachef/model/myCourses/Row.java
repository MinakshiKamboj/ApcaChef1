package com.apcachef.model.myCourses;

import com.google.gson.annotations.SerializedName;

public class Row{

	@SerializedName("img")
	private String img;

	@SerializedName("video_url")
	private String videoUrl;

	@SerializedName("description")
	private String description;

	@SerializedName("title")
	private String title;

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
}