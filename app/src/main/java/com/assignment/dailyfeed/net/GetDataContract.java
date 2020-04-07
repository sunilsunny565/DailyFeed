package com.assignment.dailyfeed.net;

import android.content.Context;

import com.assignment.dailyfeed.model.FeedItem;

import java.util.List;

public interface GetDataContract {
    interface View{
        void onGetDataSuccess(String appTitle, List<FeedItem> list);
        void onGetDataFailure(String message);
    }
    interface Presenter{
        void getDataFromURL(Context context);
    }
    interface Interactor{
        void initRetrofitCall(Context context);

    }
    interface onGetDataListener{
        void onSuccess(String appTitle, List<FeedItem> list);
        void onFailure(String message);
    }
}
