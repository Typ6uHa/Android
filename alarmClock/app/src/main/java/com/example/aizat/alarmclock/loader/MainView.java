package com.example.aizat.alarmclock.loader;

import com.example.aizat.alarmclock.model.entity.AlarmItem;

import java.util.List;

public interface MainView {
    void onPersonsLoaded(List<AlarmItem> alarmItems);
}