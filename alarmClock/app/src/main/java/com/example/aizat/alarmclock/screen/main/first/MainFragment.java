package com.example.aizat.alarmclock.screen.main.first;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.aizat.alarmclock.R;
import com.example.aizat.alarmclock.model.database.DatabaseHelper;
import com.example.aizat.alarmclock.model.entity.AlarmItem;
import com.example.aizat.alarmclock.screen.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aizat on 20.10.2017.
 */

class MainFragment extends BaseFragment {

    private RecyclerView recyclerView;

    private MainAdapter adapter;

    private LinearLayoutManager linearLayoutManager;

    private DatabaseHelper databaseHelper;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        databaseHelper = new DatabaseHelper(getActivity());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_main, container,false);

        List<AlarmItem> alarmItems = new ArrayList<>();

        alarmItems.add(new AlarmItem("13:00","'понедельник, среда, четверг'",1));
        alarmItems.add(new AlarmItem("13:00","'понедельник, среда, четверг'",1));
        alarmItems.add(new AlarmItem("13:00","'понедельник, среда, четверг'",1));
        alarmItems.add(new AlarmItem("13:00","'понедельник, среда, четверг'",1));

        for(int i = 0; i < alarmItems.size();i++){
            databaseHelper.insertAlarmItem(alarmItems.get(i));
        }

        linearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new MainAdapter(alarmItems);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                adapter.setAlarmItems(databaseHelper.selectAlarmItems());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if(item.getItemId() == R.id.item_clear){
           new Handler().post(new Runnable() {
               @Override
               public void run() {
                   databaseHelper.clearAlarmItems();
                   adapter.setAlarmItems(databaseHelper.selectAlarmItems());
               }
           });
       }
       return true;
    }
}

