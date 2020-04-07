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

public class Interactor implements GetDataContract.Interactor{
    private GetDataContract.onGetDataListener mOnGetDatalistener;
    private List<FeedItem> feeds = new ArrayList<>();


    public Interactor(GetDataContract.onGetDataListener mOnGetDatalistener) {
        this.mOnGetDatalistener = mOnGetDatalistener;
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
                feeds = jsonResponse.getFeedItems();
                if(feeds != null){
                    Log.d("Data", "Refreshed");
                    mOnGetDatalistener.onSuccess("List Size: " + feeds.size(), feeds);
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