package com.example.aizat.homework6;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aizat.homework6.model.AlarmEvent;

import java.util.List;

/**
 * Created by Aizat on 15.10.2017.
 */

public class AlarmClockAdapter extends RecyclerView.Adapter <AlarmClockHolder> {

    private List <AlarmEvent> alarmEventList;

    private OnItemClickListener onItemClickListener;

    public AlarmClockAdapter(List<AlarmEvent> alarmEventList, OnItemClickListener onItemClickListener) {
        this.alarmEventList = alarmEventList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public AlarmClockHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm_clock_list,parent,false);
        return new AlarmClockHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(AlarmClockHolder holder, int position) {
        AlarmEvent alarmEvent = alarmEventList.get(position);

        if (alarmEvent.getaSwitch().isChecked()){
            holder.on_off.setText("Выключене");
        } else {
            holder.on_off.setText("Надо сделать");
        }

    }

    @Override
    public int getItemCount() {
        return alarmEventList.size();
    }
}
