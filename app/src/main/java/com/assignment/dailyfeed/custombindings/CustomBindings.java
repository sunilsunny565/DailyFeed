package com.assignment.dailyfeed.custombindings;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.assignment.dailyfeed.model.FeedItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

public class CustomBindings {
    @BindingAdapter("imageUrl")
    public static void bindRecyclerViewAdapter(ImageView imageView, FeedItem feedItem) {
        if (feedItem.getImageHref() != null && !feedItem.isBadImage()) {
            Glide.with(imageView)
                    .load(feedItem.getImageHref())
                    .listener(new RequestListener<Drawable>() {
                        //Required for hiding the ImageView if url failed to load
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            imageView.setVisibility(View.GONE);
                            feedItem.setBadImage(true);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            imageView.setVisibility(View.VISIBLE);
                            return false;
                        }
                    })
                    .into(imageView);

        } else {
            imageView.setVisibility(View.GONE);
        }
    }
}
