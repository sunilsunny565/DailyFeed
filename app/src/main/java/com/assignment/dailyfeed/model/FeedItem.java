package com.assignment.dailyfeed.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedItem {
    @SerializedName("title")
    @Expose
    private String feedTitle;

    @SerializedName("description")
    @Expose
    private String feedDescription;

    @SerializedName("imageHref")
    @Expose
    private String feedImage;
    private boolean IsBadImage;

    //required for preventing loading bad url again and again
    public void setBadImage(boolean badImage) {
        IsBadImage = badImage;
    }

    public boolean isBadImage() {
        return IsBadImage;
    }

    public void setTitle(String title) {
        this.feedTitle = title;
    }

    public String getTitle() {
        return this.feedTitle;
    }

    public void setDescription(String description) {
        this.feedDescription = description;
    }

    public String getDescription() {
        return this.feedDescription;
    }

    public void setImageHref(String imageHref) {
        this.feedImage = imageHref;
    }

    public String getImageHref() {
        return this.feedImage;
    }
}
