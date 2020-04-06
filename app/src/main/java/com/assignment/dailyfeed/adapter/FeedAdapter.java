package com.assignment.dailyfeed.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.assignment.dailyfeed.R;
import com.assignment.dailyfeed.model.FeedItem;
import com.assignment.dailyfeed.model.FeedModel;

import java.util.List;

public class FeedAdapter extends BaseAdapter {
    public List<FeedItem> feedItems;
    public FeedAdapter(Context context,List<FeedItem>  feedItems){
        this.feedItems = feedItems;
    }
    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public FeedItem getItem(int i) {
        return feedItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_adapter,viewGroup,false);
        TextView textView = view.findViewById(R.id.title);
        if(getItem(i).getTitle() != null){
            textView.setText(getItem(i).getTitle());
        }
        return view;
    }
}
