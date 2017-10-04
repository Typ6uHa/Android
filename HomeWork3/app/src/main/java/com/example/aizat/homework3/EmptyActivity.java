package com.example.aizat.homework3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Aizat on 21.09.2017.
 */

public class EmptyActivity extends AppCompatActivity {

    // TODO Все ок, но не хватает работы с ViewPager и PagerIndicator. Да, пустые страницы , но они должны свайпаться (как в проекте на паре).

    public static Intent makeIntent (Context context){
        return new Intent (context, EmptyActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
    }
}
