package com.assignment.dailyfeed.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.assignment.dailyfeed.R;
import com.assignment.dailyfeed.adapter.FeedAdapter;
import com.assignment.dailyfeed.model.FeedItem;
import com.assignment.dailyfeed.net.GetDataContract;
import com.assignment.dailyfeed.net.Presenter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class DailyFeedFragment extends Fragment implements GetDataContract.View {
    private ListView listView;
    private FeedAdapter feedAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View view;
    ProgressBar progressBar;

    @Override
    public void onGetDataSuccess(String appTitle, List<FeedItem> feedItems) {
        swipeRefreshLayout.setRefreshing(false);
        progressBar.setVisibility(View.GONE);
        if (appTitle != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(appTitle);
        }
        if (feedAdapter == null) {
            feedAdapter = new FeedAdapter(getContext(), feedItems);
            listView.setAdapter(feedAdapter);
        } else {
            feedAdapter.getFeedItems().addAll(feedItems);
            feedAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUIControls();
    }

    private void initUIControls() {
        Presenter mPresenter = new Presenter(this);
        mPresenter.getDataFromURL(getContext());
        listView = view.findViewById(R.id.list_item);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        swipeRefreshLayout = view.findViewById(R.id.srl_pull_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            feedAdapter.getFeedItems().clear();
            feedAdapter.notifyDataSetChanged();
            mPresenter.getDataFromURL(getContext());
            progressBar.setVisibility(View.VISIBLE);
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.daily_feed_fragment, null);
        return view;
    }

    @Override
    public void onGetDataFailure(String message) {
        progressBar.setVisibility(View.GONE);
    }
}
