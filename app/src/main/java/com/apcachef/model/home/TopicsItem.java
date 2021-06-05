package com.apcachef.model.home;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.apcachef.model.detail.RecipeItem;
import com.google.gson.annotations.SerializedName;

public class TopicsItem implements Parcelable {

	@SerializedName("recipes")
	private List<RecipeItem> recipes;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	protected TopicsItem(Parcel in) {
		id = in.readString();
		title = in.readString();
	}

	public static final Creator<TopicsItem> CREATOR = new Creator<TopicsItem>() {
		@Override
		public TopicsItem createFromParcel(Parcel in) {
			return new TopicsItem(in);
		}

		@Override
		public TopicsItem[] newArray(int size) {
			return new TopicsItem[size];
		}
	};

	public void setRecipes(List<RecipeItem> recipes){
		this.recipes = recipes;
	}

	public List<RecipeItem> getRecipes(){
		return recipes;
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(id);
		parcel.writeString(title);
	}
}