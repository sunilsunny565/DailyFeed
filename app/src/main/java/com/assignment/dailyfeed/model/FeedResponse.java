package com.assignment.dailyfeed.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeedResponse {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<Feed> getFeeds();
}
