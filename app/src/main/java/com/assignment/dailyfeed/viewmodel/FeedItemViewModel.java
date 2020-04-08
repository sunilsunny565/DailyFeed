package com.assignment.dailyfeed.viewmodel;

import android.view.View;

import com.assignment.dailyfeed.R;
import com.assignment.dailyfeed.adapter.FeedAdapter;
import com.assignment.dailyfeed.model.FeedItem;
import com.assignment.dailyfeed.model.FeedItems;

import java.util.List;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FeedItemViewModel extends ViewModel {
    private FeedItems feedItems;
    public ObservableInt loading;
    public ObservableInt showEmpty;
    private FeedAdapter adapter;
    public ObservableBoolean isLoading;

    public void init() {
        feedItems = new FeedItems();
        adapter = new FeedAdapter(R.layout.list_item_adapter, this);
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
        isLoading = new ObservableBoolean();
    }
    public void fetchList() {
        feedItems.fetchList();
    }
    public void onRefresh() {
        isLoading.set(true);
        if( getFeeds().getValue() != null &&  getFeeds().getValue().size() > 0){
            getFeeds().getValue().clear();
            adapter.notifyDataSetChanged();
        }
        loading.set(View.VISIBLE);
        fetchList();
    }
    public String getTitle(){ return feedItems.getTitle();}

    public MutableLiveData<List<FeedItem>> getFeeds() {
        return feedItems.getFeeds();
    }

    public FeedAdapter getAdapter() {
        return adapter;
    }

    public void setFeedsInAdapter(List<FeedItem> feeds) {
        this.adapter.setFeedItems(feeds);
        this.adapter.notifyDataSetChanged();
    }

}
