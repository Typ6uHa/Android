package com.example.aizat.alarmclock.screen.main.first.wakeUp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.widget.Toast;

import com.example.aizat.alarmclock.model.database.DatabaseHelper;
import com.example.aizat.alarmclock.model.entity.AlarmItem;

import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Aizat on 09.11.2017.
 */

public class onRestart extends BroadcastReceiver {

    private final int WEEK = 604800000;

    DatabaseHelper databaseHelper;
    List<AlarmItem> alarmItemList;
    Calendar calendar;
    @Override
    public void onReceive(Context context, Intent intent) {
        databaseHelper = new DatabaseHelper(context);
        alarmItemList = databaseHelper.selectAlarmItems();
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
                        Toast.makeText(context, calendar.getTime().toString(), Toast.LENGTH_SHORT).show();

                        Intent intent1 = new Intent(context, AlarmOff.class);
                        intent.putExtra("value", alarmItemList.get(i).getId());
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarmItemList.get(i).getId()+getDayOfWeekToInt((String) item), intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                        //  alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), WEEK,pendingIntent);
                    }
                }
            }
        }
    }
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
