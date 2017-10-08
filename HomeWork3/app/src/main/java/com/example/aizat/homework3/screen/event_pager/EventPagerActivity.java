package com.example.aizat.homework3.screen.event_pager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.aizat.homework3.R;
import com.example.aizat.homework3.custom_view.PagerIndicator;
import com.example.aizat.homework3.model.Event;

import java.util.ArrayList;

/**
 * Created by Aizat on 21.09.2017.
 */

public class EventPagerActivity extends AppCompatActivity {

    private static final String KEY_POSITION = "position";

    private static final String KEY_EVENTS = "event";

    private ViewPager viewPager;

    private EventPagerAdapter adapter;

    private ArrayList<Event> events;

    private PagerIndicator pagerIndicator;


    public static Intent makeIntent (Context context,int position, ArrayList<Event> events){
        Intent intent = new Intent(context, EventPagerActivity.class);
        intent.putExtra(KEY_POSITION, position);
        intent.putParcelableArrayListExtra(KEY_EVENTS,events);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_pager);

        events = getIntent().getParcelableArrayListExtra(KEY_EVENTS);

        adapter = new EventPagerAdapter(getSupportFragmentManager(), events);

        pagerIndicator = (PagerIndicator) findViewById(R.id.pager_indicator);
        pagerIndicator.setItemCount(events.size(),getIntent().getIntExtra(KEY_POSITION,0));

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(getIntent().getIntExtra(KEY_POSITION,0));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                pagerIndicator.setCurrentPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
