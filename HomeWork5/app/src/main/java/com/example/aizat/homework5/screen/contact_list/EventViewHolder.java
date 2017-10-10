package com.example.aizat.homework5.screen.contact_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aizat.homework5.R;

public class EventViewHolder extends RecyclerView.ViewHolder {

    public ImageView photoImageView;

    public TextView titleTextView;

    public TextView descriptionTextView;

    public TextView dataTextView;

    public EventViewHolder(View itemView, final OnItemClickListener onItemClickListener) {
        super(itemView);

        photoImageView = itemView.findViewById(R.id.image_view);

        titleTextView = itemView.findViewById(R.id.title_view_name);

        descriptionTextView = itemView.findViewById(R.id.description_view_name);

        dataTextView = itemView.findViewById(R.id.data_view_name);

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
