package com.assignment.dailyfeed.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeedResponse {
    public static String BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";
    @GET("facts.json")
    Call<FeedModel> getFeeds();
}
