package com.apcachef.model.detail;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TopicsItem{

	@SerializedName("course_id")
	private String courseId;

	@SerializedName("video_url")
	private String videoUrl;

	@SerializedName("recipe")
	private List<RecipeItem> recipe;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	public void setCourseId(String courseId){
		this.courseId = courseId;
	}

	public String getCourseId(){
		return courseId;
	}

	public void setVideoUrl(String videoUrl){
		this.videoUrl = videoUrl;
	}

	public String getVideoUrl(){
		return videoUrl;
	}

	public void setRecipe(List<RecipeItem> recipe){
		this.recipe = recipe;
	}

	public List<RecipeItem> getRecipe(){
		return recipe;
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