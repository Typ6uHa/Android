package com.example.aizat.alarmclock.screen.main.first;

import android.support.v7.widget.SwitchCompat;

import com.example.aizat.alarmclock.model.entity.AlarmItem;

/**
 * Created by Aizat on 22.10.2017.
 */

public interface OnItemClickListener {
    void onClick(int position);
    void onSwitchClick (int position, SwitchCompat switchCompat);
}
