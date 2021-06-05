package com.apcachef.model.faq;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("faqs")
	private List<FaqsItem> faqs;

	public void setFaqs(List<FaqsItem> faqs){
		this.faqs = faqs;
	}

	public List<FaqsItem> getFaqs(){
		return faqs;
	}
}