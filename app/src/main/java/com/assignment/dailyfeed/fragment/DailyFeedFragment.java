package com.assignment.dailyfeed.fragment;

import android.os.Bundle;
import android.view.View;

import com.assignment.dailyfeed.R;
import com.assignment.dailyfeed.databinding.DailyFeedFragmentBinding;
import com.assignment.dailyfeed.viewmodel.FeedItemViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class DailyFeedFragment extends Fragment{
    private FeedItemViewModel viewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupBindings(savedInstanceState);
    }

    private void setupBindings(Bundle savedInstanceState) {
        DailyFeedFragmentBinding activityBinding = DataBindingUtil.setContentView(getActivity(), R.layout.daily_feed_fragment);
        viewModel = new ViewModelProvider(this).get(FeedItemViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        activityBinding.setModel(viewModel);
        setupListUpdate();
    }
    private void setupListUpdate() {
        viewModel.loading.set(View.VISIBLE);
        viewModel.fetchList();
        viewModel.getFeeds().observe(getActivity(), feedItems -> {
            viewModel.loading.set(View.GONE);
            if (feedItems.size() == 0) {
                viewModel.showEmpty.set(View.VISIBLE);
            } else {
                viewModel.showEmpty.set(View.GONE);
                viewModel.setFeedsInAdapter(feedItems);
                if(viewModel.getTitle() != null) {
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(viewModel.getTitle());
                }
                viewModel.isLoading.set(false);
            }
        });
    }

}
