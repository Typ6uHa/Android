package com.example.aizat.alarmclock.screen.main.first;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aizat.alarmclock.R;
import com.example.aizat.alarmclock.model.database.DatabaseHelper;
import com.example.aizat.alarmclock.model.entity.AlarmItem;
import com.example.aizat.alarmclock.screen.base.BaseFragment;
import com.example.aizat.alarmclock.screen.main.first.wakeUp.AlarmOff;
import com.example.aizat.alarmclock.screen.main.second.SecondActivity;

/**
 * Created by Aizat on 20.10.2017.
 */

class MainFragment extends BaseFragment implements OnItemClickListener{

    private final String ALARM_ITEM_REQUEST = "fsfasad";

    private FloatingActionButton floatingActionButton;

    private RecyclerView recyclerView;

    private MainAdapter adapter;

    private LinearLayoutManager linearLayoutManager;

    private DatabaseHelper databaseHelper;

    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private Calendar calendar;

    public static MainFragment newInstance() {
        Bundle data = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(data);
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
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_main, container,false);

        floatingActionButton = view.findViewById(R.id.floating_action_bar);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SecondActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new MainAdapter(this);
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

    @Override
    public void onClick(int position) {
        Toast.makeText(getContext(),String.valueOf(adapter.getAlarmItem(position).getId()),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(),SecondActivity.class);
        intent.putExtra(ALARM_ITEM_REQUEST,adapter.getAlarmItem(position));
        startActivity(intent);
    }

    @Override
    public void onSwitchClick(int position, SwitchCompat switchCompat) {
        AlarmItem alarmItem = adapter.getAlarmItem(position);

        if(switchCompat.isChecked()){
            adapter.getAlarmItem(position).setSwitchedOn(1);

            calendar = Calendar.getInstance();
            String hours = alarmItem.getTime().substring(0,2);
            String minute = alarmItem.getTime().substring(3,5);

            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hours));
            calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
            calendar.set(Calendar.SECOND, 0);

            Calendar now = Calendar.getInstance();{
                if(now.after(calendar))
                    calendar.add(Calendar.HOUR_OF_DAY, 24);
            }
            Toast.makeText(getContext(),calendar.getTime().toString(),Toast.LENGTH_SHORT).show();

            Intent intent = new Intent (getContext(),AlarmOff.class);
            pendingIntent = PendingIntent.getBroadcast(getContext(),position,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

        }else{
            adapter.getAlarmItem(position).setSwitchedOn(0);
            alarmManager.cancel(pendingIntent);
        }
        databaseHelper.updateSwitch(alarmItem);
    }

// gregorianCalendar
}

