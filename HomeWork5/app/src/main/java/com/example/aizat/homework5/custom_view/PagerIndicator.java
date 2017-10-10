package com.example.aizat.homework5.custom_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.aizat.homework5.R;

/**
 * Created by Aizat on 06.10.2017.
 */

public class PagerIndicator extends LinearLayout {

    private int currentPosition;

    public PagerIndicator(Context context) {
        super(context);
        init();
    }

    public PagerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
    }

    public void setItemCount(int count, int currentPosition){
        removeAllViews();
        for (int i = 0; i < count; i++){
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.drawable.indicator_unactive);
            addView(imageView);
        }
        setCurrentPosition(currentPosition);
    }

    public void setCurrentPosition(int position) {
        if (position >= 0 && position < getChildCount()){
            ((ImageView)getChildAt(currentPosition)).setImageResource(R.drawable.indicator_unactive);
            currentPosition = position;
            ((ImageView)getChildAt(currentPosition)).setImageResource(R.drawable.indicator_active);
        }
    }
}
