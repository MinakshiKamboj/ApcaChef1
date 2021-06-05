package com.apcachef.model.detail;

import java.util.List;

import com.apcachef.model.home.Inr;
import com.apcachef.model.home.Myr;
import com.apcachef.model.home.Usd;
import com.google.gson.annotations.SerializedName;

public class Data{

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

	@SerializedName("usd")
	private Usd usd;

	@SerializedName("myr")
	private Myr myr;

	@SerializedName("inr")
	private Inr inr;

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

	public Usd getUsd() {
		return usd;
	}

	public void setUsd(Usd usd) {
		this.usd = usd;
	}

	public Myr getMyr() {
		return myr;
	}

	public void setMyr(Myr myr) {
		this.myr = myr;
	}

	public Inr getInr() {
		return inr;
	}

	public void setInr(Inr inr) {
		this.inr = inr;
	}
}