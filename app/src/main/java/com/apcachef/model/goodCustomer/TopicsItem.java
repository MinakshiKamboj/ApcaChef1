package com.apcachef.model.goodCustomer;

import java.util.List;

import com.apcachef.model.detail.RecipeItem;
import com.google.gson.annotations.SerializedName;

public class TopicsItem{

	@SerializedName("course_id")
	private Object courseId;

	@SerializedName("recipes")
	private List<RecipeItem> recipes;

	@SerializedName("img")
	private Object img;

	@SerializedName("video_url")
	private Object videoUrl;

	@SerializedName("description")
	private Object description;

	@SerializedName("id")
	private String id;

	@SerializedName("docu_url")
	private String docuUrl;

	@SerializedName("sort_by")
	private String sortBy;

	@SerializedName("title")
	private String title;

	@SerializedName("is_trial")
	private String isTrial;

	@SerializedName("views")
	private String views;

	public void setCourseId(Object courseId){
		this.courseId = courseId;
	}

	public Object getCourseId(){
		return courseId;
	}

	public void setRecipes(List<RecipeItem> recipes){
		this.recipes = recipes;
	}

	public List<RecipeItem> getRecipes(){
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

	public void setDescription(Object description){
		this.description = description;
	}

	public Object getDescription(){
		return description;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDocuUrl(String docuUrl){
		this.docuUrl = docuUrl;
	}

	public String getDocuUrl(){
		return docuUrl;
	}

	public void setSortBy(String sortBy){
		this.sortBy = sortBy;
	}

	public String getSortBy(){
		return sortBy;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setIsTrial(String isTrial){
		this.isTrial = isTrial;
	}

	public String getIsTrial(){
		return isTrial;
	}

	public void setViews(String views){
		this.views = views;
	}

	public String getViews(){
		return views;
	}
}