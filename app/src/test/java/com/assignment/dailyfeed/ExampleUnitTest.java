package com.assignment.dailyfeed;

import com.assignment.dailyfeed.model.FeedItem;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_returningCorrectObject(){
        GetDataContract.onGetDataListener mOnGetDatalistener = new GetDataContract.onGetDataListener() {
            @Override
            public void onSuccess(String appTitle, List<FeedItem> list) {
                assertEquals(list.size(),9);
            }

            @Override
            public void onFailure(String message) {

            }
        };
        Interactor interactor = new Interactor(mOnGetDatalistener);
        interactor.fetchFeedItems("",generateFeedItems());
    }
    private List<FeedItem> generateFeedItems()
    {
        List<FeedItem> feedItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FeedItem feedItem = new FeedItem();
            if(i != 5) {
                feedItem.setTitle("FeedTitle" + i);
                feedItem.setDescription("description" + i);
                feedItem.setImageHref("image" + i);
                feedItems.add(feedItem);
            }
        }
        return feedItems;
    }


}