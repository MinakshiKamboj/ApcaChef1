package com.apcachef.model.MasterChefSeries;

import com.google.gson.annotations.SerializedName;

public class MasterChefSeriesItem {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("chef_name")
    private String chef_name;

   @SerializedName("description")
    private String description;

    public String getChef_name() {
        return chef_name;
    }

    public String getDescription() {
        return description;
    }

    public String getVideo_url() {
        return video_url;
    }

    @SerializedName("video_url")
    private String video_url;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @SerializedName("img")
    private String img;



        }
