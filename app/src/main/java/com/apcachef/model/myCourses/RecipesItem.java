package com.apcachef.model.myCourses;

import com.google.gson.annotations.SerializedName;

public class RecipesItem{

	@SerializedName("duration")
	private String duration;

	@SerializedName("video_url")
	private String videoUrl;

	@SerializedName("section_id")
	private String sectionId;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	public void setDuration(String duration){
		this.duration = duration;
	}

	public String getDuration(){
		return duration;
	}

	public void setVideoUrl(String videoUrl){
		this.videoUrl = videoUrl;
	}

	public String getVideoUrl(){
		return videoUrl;
	}

	public void setSectionId(String sectionId){
		this.sectionId = sectionId;
	}

	public String getSectionId(){
		return sectionId;
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
}