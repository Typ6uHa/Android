package com.example.aizat.homework6;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by Aizat on 15.10.2017.
 */

public class AlarmClockHolder extends RecyclerView.ViewHolder {

    public TextView date;

    public TextView on_off;

    public Switch aSwitch;

    public AlarmClockHolder(View itemView, final OnItemClickListener onItemClickListener) {
        super(itemView);

        date = itemView.findViewById(R.id.digitalClock);

        on_off = itemView.findViewById(R.id.on_off);

        aSwitch = itemView.findViewById(R.id.switch_on_off);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener != null){
                    onItemClickListener.onClick(getAdapterPosition());
                }
            }
        });
    }
}
