package com.apcachef.model.myCourses;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ShortsItem{

	@SerializedName("plan_type")
	private String planType;

	@SerializedName("img")
	private String img;

	@SerializedName("video_url")
	private String videoUrl;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("topics")
	private List<TopicsItem> topics;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	public void setPlanType(String planType){
		this.planType = planType;
	}

	public String getPlanType(){
		return planType;
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

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setTopics(List<TopicsItem> topics){
		this.topics = topics;
	}

	public List<TopicsItem> getTopics(){
		return topics;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}
}