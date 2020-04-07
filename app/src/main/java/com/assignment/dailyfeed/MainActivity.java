package com.assignment.dailyfeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.assignment.dailyfeed.adapter.FeedAdapter;
import com.assignment.dailyfeed.model.FeedItem;
import com.assignment.dailyfeed.net.GetDataContract;
import com.assignment.dailyfeed.net.Presenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GetDataContract.View {
    private ListView listView;
    private FeedAdapter feedAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Presenter mPresenter = new Presenter(this);
        mPresenter.getDataFromURL(getApplicationContext());
        listView = findViewById(R.id.list_item);
        swipeRefreshLayout = findViewById(R.id.srl_pull_to_refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            feedAdapter.getFeedItems().clear();
            feedAdapter.notifyDataSetChanged();
            mPresenter.getDataFromURL(getApplicationContext());
        });
    }

    @Override
    public void onGetDataSuccess(String message, List<FeedItem> feedItems) {
        swipeRefreshLayout.setRefreshing(false);
        if(feedAdapter == null)
        {
        feedAdapter = new FeedAdapter(getApplicationContext(), feedItems);
        listView.setAdapter(feedAdapter);
        }else{
            feedAdapter.getFeedItems().addAll(feedItems);
            feedAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
    }
}
