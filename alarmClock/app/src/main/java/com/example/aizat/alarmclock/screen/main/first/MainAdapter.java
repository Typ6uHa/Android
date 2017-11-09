package com.example.aizat.alarmclock.screen.main.first;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.aizat.alarmclock.R;
import com.example.aizat.alarmclock.model.database.DatabaseHelper;
import com.example.aizat.alarmclock.model.entity.AlarmItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aizat on 21.10.2017.
 */

class MainAdapter extends RecyclerView.Adapter<MainViewHolder>{

    private List<AlarmItem> alarmItems;

    private OnItemClickListener onItemClickListener;

    public MainAdapter() {
        this.alarmItems = new ArrayList<>();
    }

    public MainAdapter(OnItemClickListener onItemClickListener) {
        this.alarmItems = new ArrayList<>();
        this.onItemClickListener = onItemClickListener;
    }

    public MainAdapter(List<AlarmItem> alarmItems,OnItemClickListener onItemClickListener) {
        this.alarmItems = alarmItems;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
        return new MainViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, final int position) {

        final AlarmItem alarmItem = alarmItems.get(position);
        holder.time.setText(String.valueOf(alarmItem.getTime()));
        holder.description.setText(String.valueOf(alarmItem.getDescription()));
        if (alarmItem.isSwitchedOn() == 1){
            holder.switchCompat.setChecked(true);
        } else {
            holder.switchCompat.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return alarmItems.size();
    }

    public void setAlarmItems(@NonNull List<AlarmItem> alarmItems){
        this.alarmItems = alarmItems;
        notifyDataSetChanged();
    }
    public AlarmItem getAlarmItem(int position){
        return alarmItems.get(position);
    }
}
