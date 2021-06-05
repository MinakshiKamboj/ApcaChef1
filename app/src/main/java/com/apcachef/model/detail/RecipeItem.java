package com.apcachef.model.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RecipeItem implements Parcelable {

	@SerializedName("duration")
	private String duration;

	@SerializedName("course_id")
	private String courseId;

	@SerializedName("img")
	private Object img;

	@SerializedName("video_url")
	private String videoUrl;

	@SerializedName("section_id")
	private String sectionId;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setImg(Object img) {
        this.img = img;
    }

    public Object getImg() {
        return img;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
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
        parcel.writeString(description);
        parcel.writeString(videoUrl);
        parcel.writeString(courseId);
        parcel.writeString(duration);
        parcel.writeString(sectionId);
    }

    public RecipeItem(Parcel parcel) {
        id = parcel.readString();
        title = parcel.readString();
        description = parcel.readString();
        videoUrl = parcel.readString();
        courseId = parcel.readString();
        duration = parcel.readString();
        sectionId = parcel.readString();
        //img = parcel.readString();
    }

    public static final Parcelable.Creator<RecipeItem> CREATOR = new Parcelable.Creator<RecipeItem>() {
        public RecipeItem createFromParcel(Parcel parcel) {
            return new RecipeItem(parcel);
        }

        public RecipeItem[] newArray(int size) {
            return new RecipeItem[size];
        }
    };

}