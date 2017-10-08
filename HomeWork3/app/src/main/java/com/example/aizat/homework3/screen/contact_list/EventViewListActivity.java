package com.example.aizat.homework3.screen.contact_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.aizat.homework3.screen.event_pager.EventPagerActivity;
import com.example.aizat.homework3.R;
import com.example.aizat.homework3.model.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Aizat on 21.09.2017.
 */

public class EventViewListActivity extends AppCompatActivity implements OnItemClickListener{

    private List <Event> events;

    RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;

    EventListAdapter eventListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view_holder);

        events = getEvents();

        linearLayoutManager = new LinearLayoutManager(this);

        eventListAdapter = new EventListAdapter(events,this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(eventListAdapter);
    }

    @Override
    public void onClick(int position) {
        startActivity(EventPagerActivity.makeIntent(this, position, (ArrayList<Event>) events));
    }

    private List<Event> getEvents() {

        List <Event> events = new ArrayList<>();

        events.add(new Event(0,R.mipmap.ic_launcher_round,"проснулся","Сегодня проснулся в 0 часов. Было солнечено",new Date()));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"проснулся","Сегодня проснулся в 1 часов. Было солнечено",new Date()));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"проснулся","Сегодня проснулся в 2 часов. Было солнечено",new Date()));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"проснулся","Сегодня проснулся в 3 часов. Было солнечено",new Date()));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"проснулся","Сегодня проснулся в 4 часов. Было солнечено",new Date()));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"проснулся","Сегодня проснулся в 5 часов. Было солнечено",new Date()));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"проснулся","Сегодня проснулся в 6 часов. Было солнечено",new Date()));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"проснулся","Сегодня проснулся в 7 часов. Было солнечено",new Date()));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"проснулся","Сегодня проснулся в 8 часов. Было солнечено",new Date()));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"проснулся","Сегодня проснулся в 9 часов. Было солнечено",new Date()));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"проснулся","Сегодня проснулся в 10 часов. Было солнечено",new Date()));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"проснулся","Сегодня проснулся в 11 часов. Было солнечено",new Date()));
        return events;
    }
}
