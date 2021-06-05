package com.apcachef.model.egift;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("img")
	private String img;

	@SerializedName("heading")
	private String heading;

	@SerializedName("courses_types")
	private List<CoursesTypesItem> coursesTypes;

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

	public void setHeading(String heading){
		this.heading = heading;
	}

	public String getHeading(){
		return heading;
	}

	public void setCoursesTypes(List<CoursesTypesItem> coursesTypes){
		this.coursesTypes = coursesTypes;
	}

	public List<CoursesTypesItem> getCoursesTypes(){
		return coursesTypes;
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