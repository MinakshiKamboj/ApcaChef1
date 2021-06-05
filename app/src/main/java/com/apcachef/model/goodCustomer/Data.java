package com.apcachef.model.goodCustomer;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("topics")
	private List<TopicsItem> topics;

	public void setTopics(List<TopicsItem> topics){
		this.topics = topics;
	}

	public List<TopicsItem> getTopics(){
		return topics;
	}
}