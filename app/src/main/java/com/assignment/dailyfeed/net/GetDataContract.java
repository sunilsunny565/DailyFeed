package com.assignment.dailyfeed.net;

import android.content.Context;

import com.assignment.dailyfeed.model.FeedModel;

import java.util.List;

public interface GetDataContract {
    interface View{
        void onGetDataSuccess(String message, List<FeedModel> list);
        void onGetDataFailure(String message);
    }
    interface Presenter{
        void getDataFromURL(Context context, String url);
    }
    interface Interactor{
        void initRetrofitCall(Context context, String url);

    }
    interface onGetDataListener{
        void onSuccess(String message, List<FeedModel> list);
        void onFailure(String message);
    }
}
