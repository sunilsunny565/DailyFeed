package com.assignment.dailyfeed.net;

import android.content.Context;
import android.util.Log;

import com.assignment.dailyfeed.model.FeedItem;
import com.assignment.dailyfeed.model.FeedModel;
import com.assignment.dailyfeed.model.FeedResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.assignment.dailyfeed.model.FeedResponse.BASE_URL;

public class Interactor implements GetDataContract.Interactor {
    private GetDataContract.onGetDataListener mOnGetDatalistener;
    private List<FeedItem> feeds = new ArrayList<>();

    public List<FeedItem> getFeeds() {
        return feeds;
    }

    public Interactor(GetDataContract.onGetDataListener mOnGetDatalistener) {
        this.mOnGetDatalistener = mOnGetDatalistener;
    }
    public void fetchFeedItems(String title, List<FeedItem> feedItems) {
        for (FeedItem feedItem : feedItems) {
            if (feedItem.getDescription() != null && feedItem.getImageHref() != null && feedItem.getDescription() != null) {
                feeds.add(feedItem);
            }
        }
        if (feeds != null) {
            mOnGetDatalistener.onSuccess(title, feeds);
        }
    }

    @Override
    public void initRetrofitCall(Context context) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        FeedResponse request = retrofit.create(FeedResponse.class);
        retrofit2.Call<FeedModel> call = request.getFeeds();
        call.enqueue(new retrofit2.Callback<FeedModel>() {
            @Override
            public void onResponse(retrofit2.Call<FeedModel> call, retrofit2.Response<FeedModel> response) {
                FeedModel jsonResponse = response.body();
                if (jsonResponse != null && jsonResponse.getFeedItems() != null) {
                    fetchFeedItems(jsonResponse.title, jsonResponse.getFeedItems());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<FeedModel> call, Throwable t) {
                Log.v("Error", t.getMessage());
                mOnGetDatalistener.onFailure(t.getMessage());
            }
        });
    }
}
