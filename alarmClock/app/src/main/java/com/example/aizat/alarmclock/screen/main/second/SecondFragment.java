package com.example.aizat.alarmclock.screen.main.second;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.aizat.alarmclock.R;
import com.example.aizat.alarmclock.model.database.DatabaseHelper;
import com.example.aizat.alarmclock.model.entity.AlarmItem;
import com.example.aizat.alarmclock.model.table.AlarmItemTable;
import com.example.aizat.alarmclock.screen.base.BaseFragment;
import com.example.aizat.alarmclock.screen.main.first.MainActivity;

import java.sql.Blob;

/**
 * Created by Aizat on 21.10.2017.
 */

public class SecondFragment extends BaseFragment {

    private final String ALARM_ITEM_REQUEST = "fsfasad";

    private FloatingActionButton floatingActionButton;

    private TimePicker timePicker;

    private DatabaseHelper databaseHelper;

    private AlarmItem alarmItem;

    private Calendar calendar;

    public static SecondFragment newInstance(Bundle data) {
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new DatabaseHelper(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second,container,false);

        alarmItem = getArguments().getParcelable(ALARM_ITEM_REQUEST);

        calendar = Calendar.getInstance();

        timePicker = view.findViewById(R.id.time_picker);
        timePicker.setIs24HourView(true);

        floatingActionButton = view.findViewById(R.id.floating_action_bar1);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                    calendar.set(Calendar.MINUTE, timePicker.getMinute());

                    int hour = timePicker.getHour();
                    int minute = timePicker.getMinute();

                    String hour_string = String.valueOf(hour);
                    String minute_string = String.valueOf(minute);

                    if (minute < 10) {
                        minute_string = "0" + String.valueOf(minute);
                    }
                    if (hour < 10){
                        hour_string = "0" + String.valueOf(hour);
                    }
                    String time = hour_string + ":" + minute_string;


                    if (alarmItem == null) {
                        databaseHelper.insertAlarmItem(new AlarmItem(time, getDays(), 0, (int) System.currentTimeMillis()));
                    } else {
                            alarmItem.setTime(time);
                            alarmItem.setDescription(getDays());
                            alarmItem.setSwitchedOn(1);
                            databaseHelper.updateAlarmItem(alarmItem);
                        }
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);

            }
        });

        return view;
    }
    private String getDays(){
        String answer = "'";

        CheckBox checkBox1 = getView().findViewById(R.id.monday);
        CheckBox checkBox2 = getView().findViewById(R.id.tuesday);
        CheckBox checkBox3 = getView().findViewById(R.id.wednesday);
        CheckBox checkBox4 = getView().findViewById(R.id.Thursday);
        CheckBox checkBox5 = getView().findViewById(R.id.friday);
        CheckBox checkBox6 = getView().findViewById(R.id.saturday);
        CheckBox checkBox7 = getView().findViewById(R.id.sunday);

        if (checkBox1.isChecked()){
            answer += checkBox1.getText();
        }
        if (checkBox2.isChecked()){
            answer += " "+checkBox2.getText();
        }
        if (checkBox3.isChecked()){
            answer += " "+checkBox3.getText();
        }
        if (checkBox4.isChecked()){
            answer += " "+checkBox4.getText();
        }
        if (checkBox5.isChecked()){
            answer += " "+checkBox5.getText();
        }
        if (checkBox6.isChecked()){
            answer += " "+checkBox6.getText();
        }
        if (checkBox7.isChecked()){
            answer += " "+checkBox7.getText();
        }

        return answer+"'";
    }
}
