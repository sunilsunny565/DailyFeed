package com.assignment.dailyfeed.model;

import android.util.Log;

import com.assignment.dailyfeed.net.Api;

import java.util.ArrayList;
import java.util.List;

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

    public void setTitle(String title) {
        this.title = title;
    }

    private List<FeedItem> feedsList = new ArrayList<>();
    private MutableLiveData<List<FeedItem>> feeds = new MutableLiveData<>();

    public void fetchFeedItems(String title, List<FeedItem> feedItems) {
        for (FeedItem feedItem : feedItems) {
            if (feedItem.getDescription() != null && feedItem.getImageHref() != null && feedItem.getDescription() != null) {
                feedsList.add(feedItem);
            }
        }
        this.title = title;
        feeds.setValue(feedsList);
    }

    public MutableLiveData<List<FeedItem>> getFeeds() {
        return feeds;
    }

    public void fetchList() {
        Callback<FeedModel> callback = new Callback<FeedModel>() {
            @Override
            public void onResponse(Call<FeedModel> call, Response<FeedModel> response) {
                FeedModel feedResponse = response.body();
                if (feedResponse != null && feedResponse.getFeedItems() != null) {
                    fetchFeedItems(feedResponse.title, feedResponse.getFeedItems());
                }
            }

            @Override
            public void onFailure(Call<FeedModel> call, Throwable t) {
                Log.e("Test", t.getMessage(), t);
            }
        };

        Api.getApi().getFeedItems().enqueue(callback);
    }
}
