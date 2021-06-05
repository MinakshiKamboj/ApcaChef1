package com.apcachef.model.home;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CatesItem{

	@SerializedName("subs")
	private List<SubsItem> subs;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	public void setSubs(List<SubsItem> subs){
		this.subs = subs;
	}

	public List<SubsItem> getSubs(){
		return subs;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}