package com.assignment.dailyfeed;

import android.os.Bundle;
import android.os.Handler;

import com.assignment.dailyfeed.fragment.DailyFeedFragment;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        final Handler handler = new Handler();
        handler.postDelayed(this::navigateToDailyFeedFragment, 2000);
    }

    // Method for navigating to the DailyFeedFragment
    private void navigateToDailyFeedFragment() {
        DailyFeedFragment dailyFeedFragment = new DailyFeedFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frm_fragment_container, dailyFeedFragment).commit();
    }
}
