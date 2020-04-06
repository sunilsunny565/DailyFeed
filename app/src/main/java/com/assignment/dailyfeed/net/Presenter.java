package com.assignment.dailyfeed.net;

import android.content.Context;

import com.assignment.dailyfeed.model.FeedItem;
import com.assignment.dailyfeed.model.FeedModel;

import java.util.List;

public class Presenter implements GetDataContract.Presenter, GetDataContract.onGetDataListener {
    private GetDataContract.View mGetDataView;
    private Interactor mInteractor;

    public Presenter(GetDataContract.View mGetDataView) {
        this.mGetDataView = mGetDataView;
        mInteractor = new Interactor(this);
    }

    @Override
    public void getDataFromURL(Context context, String url) {
        mInteractor.initRetrofitCall(context, url);
    }

    @Override
    public void onSuccess(String message, List<FeedItem> feedDatas) {
        mGetDataView.onGetDataSuccess(message, feedDatas);
    }

    @Override
    public void onFailure(String message) {
        mGetDataView.onGetDataFailure(message);
    }
}
