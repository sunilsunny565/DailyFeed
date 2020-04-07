package com.assignment.dailyfeed.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.dailyfeed.R;
import com.assignment.dailyfeed.model.FeedItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import androidx.annotation.Nullable;

public class FeedAdapter extends BaseAdapter {
    private List<FeedItem> feedItems;
    private Context context;

    public FeedAdapter(Context context, List<FeedItem> feedItems) {
        this.context = context;
        this.feedItems = feedItems;
    }

    public List<FeedItem> getFeedItems() {
        return feedItems;
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
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_adapter, viewGroup, false);
        TextView feedTitle = view.findViewById(R.id.title);
        TextView feedDescription = view.findViewById(R.id.txt_description);
        ImageView feedImage = view.findViewById(R.id.img_feed_image);
        if (getItem(i) != null) {

            if (getItem(i).getTitle() != null) {
                feedTitle.setText(getItem(i).getTitle());
            }
            if (getItem(i).getDescription() != null) {
                feedDescription.setText(getItem(i).getDescription());
            }
            if (getItem(i).getImageHref() != null && !getItem(i).isBadImage()) {
                Glide.with(context)
                        .load(getItem(i).getImageHref())
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                if(getCount() > 0){
                                    feedImage.setVisibility(View.GONE);
                                    getItem(i).setBadImage(true);
                                }
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                feedImage.setVisibility(View.VISIBLE);
                                return false;
                            }
                        })
                        .into(feedImage);

            } else {
                feedImage.setVisibility(View.GONE);
            }
        }
        return view;

    }
}
