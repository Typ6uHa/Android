package com.example.aizat.shrifti;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Aizat on 13.11.2017.
 */

public class Holder extends RecyclerView.ViewHolder{

    @BindView(R.id.image_view) ImageView imageView;

    @BindView(R.id.text_view) TextView textView;

    public Holder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

}
