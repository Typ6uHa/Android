package com.example.aizat.alarmclock.screen.main.first;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
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

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Aizat on 20.10.2017.
 */

class MainFragment extends BaseFragment implements OnItemClickListener{

    private final int WEEK = 604800000;

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
        sendNotification(databaseHelper.selectAlarmItems());
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
        String[] alertDays = alarmItem.getDescription().split(" ");

        Locale ru = new Locale("ru");
        SimpleDateFormat dayFormatter = new SimpleDateFormat("EEEE",ru);
        String todayText = dayFormatter.format(new Date(System.currentTimeMillis()));

        if(switchCompat.isChecked()){
            adapter.getAlarmItem(position).setSwitchedOn(1);
//                for (CharSequence item : alertDays) {
//                    calendar = Calendar.getInstance();
//                    String hours = alarmItem.getTime().substring(0, 2);
//                    String minute = alarmItem.getTime().substring(3, 5);
//
//                    calendar.set(Calendar.DAY_OF_WEEK, getDayOfWeekToInt((String) item));
//                    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hours));
//                    calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
//                    calendar.set(Calendar.SECOND, 0);
//
//                    Calendar now = Calendar.getInstance();
//                        if (now.after(calendar) && item.equals(todayText)){
//                            calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+7);
//                        }
//                    Toast.makeText(getContext(), calendar.getTime().toString(), Toast.LENGTH_SHORT).show();
//
//                    Intent intent = new Intent(getContext(), AlarmOff.class);
//                    intent.putExtra("value", alarmItem.getId());
//                    pendingIntent = PendingIntent.getBroadcast(getContext(), alarmItem.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                    alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
//                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//                   // alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),WEEK, pendingIntent);
//            }
        }else{
            adapter.getAlarmItem(position).setSwitchedOn(0);
            if (pendingIntent != null) {
                alarmManager.cancel(pendingIntent);
            }
        }
        databaseHelper.updateSwitch(alarmItem);
    }

    private void sendNotification(List<AlarmItem> alarmItemList){
        Locale ru = new Locale("ru");
        SimpleDateFormat dayFormatter = new SimpleDateFormat("EEEE",ru);
        String todayText = dayFormatter.format(new Date(System.currentTimeMillis()));
        for (int i = 0; i < alarmItemList.size(); i++){
            if (alarmItemList.get(i).isSwitchedOn() == 1) {
                String[] alertDays = alarmItemList.get(i).getDescription().split(" ");
                for (CharSequence item : alertDays) {
                    if (!item.equals("")) {
                        calendar = Calendar.getInstance();
                        String hours = alarmItemList.get(i).getTime().substring(0, 2);
                        String minute = alarmItemList.get(i).getTime().substring(3, 5);

                        calendar.set(Calendar.DAY_OF_WEEK, getDayOfWeekToInt((String) item));
                        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hours));
                        calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
                        calendar.set(Calendar.SECOND, 0);

                        Calendar now = Calendar.getInstance();
                        if (now.after(calendar) && item.equals(todayText)) {
                            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 7);
                        }
                        Toast.makeText(getContext(), calendar.getTime().toString(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getContext(), AlarmOff.class);
                        intent.putExtra("value", alarmItemList.get(i).getId());
                        pendingIntent = PendingIntent.getBroadcast(getContext(), alarmItemList.get(i).getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                      //  alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), WEEK,pendingIntent);
                    }
                }
            }
        }
    }
// gregorianCalendar
    private int getDayOfWeekToInt(String day){
        if (day.equals("понедельник")) {
            return 2;
        }
        if (day.equals("вторник")) {
            return 3;
        }
        if (day.equals("среда")) {
            return 4;
        }
        if (day.equals("четверг")) {
            return 5;
        }
        if (day.equals("пятница")) {
            return 6;
        }
        if (day.equals("суббота")) {
            return 7;
        }
        if (day.equals("воскресенье")) {
            return 1;
        } else {
            return 999999999;
        }
    }
}

