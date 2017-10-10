package com.example.aizat.homework5.screen.contact_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.aizat.homework5.screen.event_pager.EventPagerActivity;
import com.example.aizat.homework5.R;
import com.example.aizat.homework5.model.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventViewListActivity extends AppCompatActivity implements OnItemClickListener{

    private List <Event> events;

    RecyclerView recyclerView;

    private Toolbar mToolbar;

    private LinearLayoutManager linearLayoutManager;

    EventListAdapter eventListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view_holder);

        events = getEvents();

        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
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

        events.add(new Event(0,R.mipmap.ic_launcher_round,"Ух какое событие","Надо позвонить маме",new Date(),"8(903)388-69-94"));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"Ух какое событие1","Надо позвонить бабуле",new Date(),"8(965)584-80-83"));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"Ух какое событие2","Надо позвонить папе",new Date(),"8(903)388-69-93"));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"Ух какое событие3","позвоню ка себе",new Date(),"8(960)045-08-12"));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"Ух какое событие4","позвоню на левый номер",new Date(),"8(800)555-35-35"));
        events.add(new Event(0,R.mipmap.ic_launcher_round,"Ух какое событие5","позвоню на левый номер",new Date(),"8(800)555-35-35"));
        return events;
    }
}
