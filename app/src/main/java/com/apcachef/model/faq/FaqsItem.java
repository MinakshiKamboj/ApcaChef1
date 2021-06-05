package com.apcachef.model.faq;

import com.google.gson.annotations.SerializedName;

public class FaqsItem{

	@SerializedName("question")
	private String question;

	@SerializedName("answer")
	private String answer;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private String id;

	public void setQuestion(String question){
		this.question = question;
	}

	public String getQuestion(){
		return question;
	}

	public void setAnswer(String answer){
		this.answer = answer;
	}

	public String getAnswer(){
		return answer;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}