package com.assignment.dailyfeed.net;

import android.content.Context;
import android.util.Log;

import com.assignment.dailyfeed.model.Feed;
import com.assignment.dailyfeed.model.FeedModel;
import com.assignment.dailyfeed.model.FeedResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Interactor implements GetDataContract.Interactor{
    private GetDataContract.onGetDataListener mOnGetDatalistener;
    private List<FeedModel> feeds = new ArrayList<>();
    public static String BASE_URL = "https://dl.dropboxusercontent.com";

    public Interactor(GetDataContract.onGetDataListener mOnGetDatalistener) {
        this.mOnGetDatalistener = mOnGetDatalistener;
    }

    @Override
    public void initRetrofitCall(Context context, String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        FeedResponse request = retrofit.create(FeedResponse.class);
        retrofit2.Call<Feed> call = request.getFeeds();
        call.enqueue(new retrofit2.Callback<Feed>() {
            @Override
            public void onResponse(retrofit2.Call<Feed> call, retrofit2.Response<Feed> response) {
                Feed jsonResponse = response.body();
                feeds = jsonResponse.getFeeds();
                if(feeds != null){
                    Log.d("Data", "Refreshed");
                    mOnGetDatalistener.onSuccess("List Size: " + feeds.size(), feeds);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Feed> call, Throwable t) {
                Log.v("Error", t.getMessage());
                mOnGetDatalistener.onFailure(t.getMessage());
            }
        });
    }
}
