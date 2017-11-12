package com.example.aizat.alarmclock.screen.main.first.wakeUp;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.aizat.alarmclock.R;
import com.example.aizat.alarmclock.screen.main.first.MainActivity;
import com.example.aizat.alarmclock.screen.main.third.StopActivity;
import com.example.aizat.alarmclock.screen.main.third.StopFragment;

import static android.content.Context.ALARM_SERVICE;
import static java.security.AccessController.getContext;

/**
 * Created by Aizat on 08.11.2017.
 */

public class AlarmOff extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent service_intent = new Intent(context,RingtonePlayingService.class);
        service_intent.putExtra("key","alarm on");
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context,StopActivity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,intent.getIntExtra("value",99999),repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Проснись и пой)")
                .setContentText("Доброе утро")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL);

        context.startService(service_intent);

        Intent intent1 = new Intent(context.getApplicationContext(),StopActivity.class);
        context.startActivity(intent1);

        notificationManager.notify(1,builder.build());
    }
}
