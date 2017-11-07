package com.example.aizat.alarmclock.screen.main.first;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.TextView;

import com.example.aizat.alarmclock.R;

/**
 * Created by Aizat on 21.10.2017.
 */

class MainViewHolder extends RecyclerView.ViewHolder {

    public TextView time;

    public TextView description;

    public SwitchCompat switchCompat;

    public MainViewHolder(View itemView, final OnItemClickListener onItemClickListener) {
        super(itemView);

        time = itemView.findViewById(R.id.time);

        description = itemView.findViewById(R.id.description);

        switchCompat = itemView.findViewById(R.id.switch_main);

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
