package com.assignment.dailyfeed.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedModel {
    @SerializedName("rows")
    public List<FeedItem> feedItems;

    public void setFeedItems(List<FeedItem> feedItems) {
        this.feedItems = feedItems;
    }

    public List<FeedItem> getFeedItems() {
        return feedItems;
    }

    public class FeedItem {
        @SerializedName("title")
        private String feedTitle;
        @SerializedName("description")
        private String feedDescription;
        @SerializedName("imageHref")
        private String feedImage;

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
}
