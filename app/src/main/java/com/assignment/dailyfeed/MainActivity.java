package com.assignment.dailyfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.assignment.dailyfeed.adapter.FeedAdapter;
import com.assignment.dailyfeed.model.FeedItem;
import com.assignment.dailyfeed.net.GetDataContract;
import com.assignment.dailyfeed.net.Presenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GetDataContract.View {
    private Presenter mPresenter;
    ListView listView;
    FeedAdapter feedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new Presenter(this);
        mPresenter.getDataFromURL(getApplicationContext(), "");
        listView = findViewById(R.id.list_item);
    }

    @Override
    public void onGetDataSuccess(String message, List<FeedItem> feedItems) {
        feedAdapter = new FeedAdapter(getApplicationContext(), feedItems);listView.setAdapter(feedAdapter);

    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
    }
}
