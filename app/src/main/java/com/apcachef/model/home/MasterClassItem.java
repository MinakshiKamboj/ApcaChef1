package com.apcachef.model.home;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MasterClassItem{

	@SerializedName("img")
	private String img;

	@SerializedName("chef_about")
	private String chefAbout;

	@SerializedName("video_url")
	private String videoUrl;

	@SerializedName("chef_id")
	private Object chefId;

	@SerializedName("topics")
	private List<TopicsItem> topics;

	@SerializedName("chef_name")
	private String chefName;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("chef_headline")
	private String chefHeadline;

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return img;
	}

	public void setChefAbout(String chefAbout){
		this.chefAbout = chefAbout;
	}

	public String getChefAbout(){
		return chefAbout;
	}

	public void setVideoUrl(String videoUrl){
		this.videoUrl = videoUrl;
	}

	public String getVideoUrl(){
		return videoUrl;
	}

	public void setChefId(Object chefId){
		this.chefId = chefId;
	}

	public Object getChefId(){
		return chefId;
	}

	public void setTopics(List<TopicsItem> topics){
		this.topics = topics;
	}

	public List<TopicsItem> getTopics(){
		return topics;
	}

	public void setChefName(String chefName){
		this.chefName = chefName;
	}

	public String getChefName(){
		return chefName;
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

	public void setChefHeadline(String chefHeadline){
		this.chefHeadline = chefHeadline;
	}

	public String getChefHeadline(){
		return chefHeadline;
	}
}