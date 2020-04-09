package com.assignment.dailyfeed;

import com.assignment.dailyfeed.model.FeedItem;
import com.assignment.dailyfeed.model.FeedItems;
import com.assignment.dailyfeed.viewmodel.FeedItemViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    @Mock
    private Observer<FeedItem> observer;
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void test_returningCorrectObject(){
        List<FeedItem> feedItems = generateFeedItems();
        FeedItemViewModel viewModel = new FeedItemViewModel();
        viewModel.init();

        // When
        viewModel.getFeeds().setValue(feedItems);
        // then
        assertEquals(viewModel.getFeeds().getValue().size(), feedItems.size());
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