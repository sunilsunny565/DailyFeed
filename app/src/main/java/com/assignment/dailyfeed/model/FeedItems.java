package com.assignment.dailyfeed.model;

import com.assignment.dailyfeed.net.Api;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedItems extends BaseObservable {
    private String title;

    public String getTitle() {
        return title;
    }

    private List<FeedItem> feedsList = new ArrayList<>();
    private MutableLiveData<List<FeedItem>> feeds = new MutableLiveData<>();
    private MutableLiveData<String> status = new MutableLiveData<>();

    //   This method is required for adding all null parameter objects.
    private void AddFeedItems(String title, List<FeedItem> feedItems) {
        for (FeedItem feedItem : feedItems) {
            if (feedItem.getTitle() != null || feedItem.getImageHref() != null || feedItem.getDescription() != null) {
                feedsList.add(feedItem);
            }
        }
        this.title = title;
        feeds.setValue(feedsList);
    }

    public MutableLiveData<List<FeedItem>> getFeeds() {
        return feeds;
    }

    // Method for fetching the feed items from APi
    public void fetchList() {
        Callback<FeedModel> callback = new Callback<FeedModel>() {
            @Override
            public void onResponse(@NonNull Call<FeedModel> call, Response<FeedModel> response) {
                FeedModel feedResponse = response.body();
                if (feedResponse != null && feedResponse.getFeedItems() != null) {
                    AddFeedItems(feedResponse.title, feedResponse.getFeedItems());
                }
            }

            @Override
            public void onFailure(@NonNull Call<FeedModel> call, Throwable t) {
                status.setValue(t.getMessage());
            }
        };

        Api.getApi().getFeedItems().enqueue(callback);
    }

    public MutableLiveData<String> getStatus() {
        return status;
    }
}
