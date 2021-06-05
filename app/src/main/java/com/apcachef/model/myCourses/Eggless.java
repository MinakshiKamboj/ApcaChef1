package com.apcachef.model.myCourses;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Eggless{

	@SerializedName("tot_topic")
	private int totTopic;

	@SerializedName("tot_recipe")
	private int totRecipe;

	@SerializedName("topics")
	private List<TopicsItem> topics;

	@SerializedName("is_subscribed")
	private int isSubscribed;

	@SerializedName("row")
	private Row row;

	@SerializedName("tot_dura")
	private int totDura;

	public void setTotTopic(int totTopic){
		this.totTopic = totTopic;
	}

	public int getTotTopic(){
		return totTopic;
	}

	public void setTotRecipe(int totRecipe){
		this.totRecipe = totRecipe;
	}

	public int getTotRecipe(){
		return totRecipe;
	}

	public void setTopics(List<TopicsItem> topics){
		this.topics = topics;
	}

	public List<TopicsItem> getTopics(){
		return topics;
	}

	public void setIsSubscribed(int isSubscribed){
		this.isSubscribed = isSubscribed;
	}

	public int getIsSubscribed(){
		return isSubscribed;
	}

	public void setRow(Row row){
		this.row = row;
	}

	public Row getRow(){
		return row;
	}

	public void setTotDura(int totDura){
		this.totDura = totDura;
	}

	public int getTotDura(){
		return totDura;
	}
}