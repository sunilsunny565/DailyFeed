package com.assignment.dailyfeed.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedModel {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rows")
    @Expose
    private List<FeedItem> feedItems;

    List<FeedItem> getFeedItems() {
        return feedItems;
    }

    String getTitle() {
        return title;
    }
}


