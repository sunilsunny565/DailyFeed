package com.assignment.dailyfeed;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.assignment.dailyfeed.fragment.DailyFeedFragment;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.frm_fragment_container).setVisibility(View.VISIBLE);
               navigateToDailyFeedFragment();
            }
        }, 2000);
    }

    private void navigateToDailyFeedFragment() {
        DailyFeedFragment dailyFeedFragment = new DailyFeedFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frm_fragment_container,dailyFeedFragment).commit();

    }
}
