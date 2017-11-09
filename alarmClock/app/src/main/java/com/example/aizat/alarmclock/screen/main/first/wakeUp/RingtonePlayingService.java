package com.example.aizat.alarmclock.screen.main.first.wakeUp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.aizat.alarmclock.R;

/**
 * Created by Aizat on 08.11.2017.
 */

public class RingtonePlayingService extends Service{

    MediaPlayer media_song;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String value = intent.getStringExtra("key");
        if (value.equals("alarm on")) {
            media_song = MediaPlayer.create(this, R.raw.song);
            media_song.start();
        }else {
            media_song.stop();
            media_song.reset();
        }
        return START_NOT_STICKY;
    }
}
