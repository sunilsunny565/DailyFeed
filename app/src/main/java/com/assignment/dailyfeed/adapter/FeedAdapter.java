package com.assignment.dailyfeed.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.assignment.dailyfeed.BR;
import com.assignment.dailyfeed.model.FeedItem;
import com.assignment.dailyfeed.viewmodel.FeedItemViewModel;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public class FeedAdapter extends BaseAdapter {
    private int layoutId;
    private List<FeedItem> feeds;
    private FeedItemViewModel viewModel;
    private LayoutInflater inflater;

    public FeedAdapter(@LayoutRes int layoutId, FeedItemViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    @Override
    public int getCount() {
        return feeds == null ? 0 : feeds.size();
    }

    public void setFeedItems(List<FeedItem> feeds) {
        this.feeds = feeds;
    }

    @Override
    public FeedItem getItem(int i) {
        return feeds.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if (inflater == null) {
            inflater = ((Activity) parent.getContext()).getLayoutInflater();
        }
        // Perform the binding

        ViewDataBinding binding = DataBindingUtil.getBinding(view);

        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
        }
        binding.setVariable(BR.viewModel, viewModel);
        binding.setVariable(BR.position, i);
        binding.executePendingBindings();

        // Return the bound view
        return binding.getRoot();
    }
}
