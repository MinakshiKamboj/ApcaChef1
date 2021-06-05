package com.apcachef.model.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ChefsItem implements Parcelable {

    @SerializedName("img")
    private String img;

    @SerializedName("first_name")
    private String firstName;

	@SerializedName("last_name")
	private String lastName;

    @SerializedName("about")
    private String about;

    @SerializedName("id")
    private String id;

    @SerializedName("message")
    private String message;

    @SerializedName("headline")
    private String headline;

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAbout(String about) {
        this.about = about;
    }

    public String getAbout() {
        return about;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getHeadline() {
        return headline;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(headline);
        parcel.writeString(about);
        parcel.writeString(img);
    }

    public ChefsItem(Parcel parcel) {
		firstName = parcel.readString();
		lastName = parcel.readString();
        headline = parcel.readString();
        about = parcel.readString();
        img = parcel.readString();
    }

    public static final Parcelable.Creator<ChefsItem> CREATOR = new Parcelable.Creator<ChefsItem>() {
        public ChefsItem createFromParcel(Parcel parcel) {
            return new ChefsItem(parcel);
        }

        public ChefsItem[] newArray(int size) {
            return new ChefsItem[size];
        }
    };

}