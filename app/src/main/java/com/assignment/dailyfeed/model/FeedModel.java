package com.assignment.dailyfeed.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedModel {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("rows")
    @Expose
    public List<FeedItem> feedItems;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setFeedItems(List<FeedItem> feedItems) {
        this.feedItems = feedItems;
    }

    public List<FeedItem> getFeedItems() {
        return feedItems;
    }
}


