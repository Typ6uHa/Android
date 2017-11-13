package com.example.aizat.shrifti;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

/**
 * Created by Aizat on 13.11.2017.
 */

public class Adapter extends RecyclerView.Adapter<Holder> {

    private String [] photos;

    private String [] name;

    private ViewGroup viewGroup;

    public Adapter(String [] photos, String [] name) {
        this.photos = photos;
        this.name = name;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);

        viewGroup = parent;

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Glide
                .with(viewGroup.getContext())
                .load(photos[position])
                .transition(DrawableTransitionOptions.withCrossFade(1000))
                .into(holder.imageView);
        holder.textView.setText(name[position]);
    }

    @Override
    public int getItemCount() {
        return photos.length;
    }
}
