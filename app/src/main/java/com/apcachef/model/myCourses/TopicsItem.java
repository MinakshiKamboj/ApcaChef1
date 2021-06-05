package com.apcachef.model.myCourses;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TopicsItem{

	@SerializedName("recipes")
	private List<RecipesItem> recipes;

	@SerializedName("img")
	private Object img;

	@SerializedName("video_url")
	private Object videoUrl;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	public void setRecipes(List<RecipesItem> recipes){
		this.recipes = recipes;
	}

	public List<RecipesItem> getRecipes(){
		return recipes;
	}

	public void setImg(Object img){
		this.img = img;
	}

	public Object getImg(){
		return img;
	}

	public void setVideoUrl(Object videoUrl){
		this.videoUrl = videoUrl;
	}

	public Object getVideoUrl(){
		return videoUrl;
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