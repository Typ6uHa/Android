package com.example.aizat.homework5.screen.event_pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.aizat.homework5.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aizat on 06.10.2017.
 */

public class EventPagerAdapter extends FragmentStatePagerAdapter{

    private List<Fragment> fragments;
    public EventPagerAdapter(FragmentManager fm, List<Event> events) {
        super(fm);
        fragments = makeFragments(events);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    private List<Fragment> makeFragments(List<Event> events){
        List<Fragment> result = new ArrayList<>();
        for (Event event: events){
            result.add(EventFragment.newInstance(event));
        }
        return result;
    }
}
