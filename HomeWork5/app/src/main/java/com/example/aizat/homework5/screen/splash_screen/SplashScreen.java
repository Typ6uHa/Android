package com.example.aizat.homework5.screen.splash_screen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aizat.homework5.R;
import com.example.aizat.homework5.screen.contact_list.EventViewListActivity;

public class SplashScreen extends AppCompatActivity {

    private final int TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), EventViewListActivity.class);
                startActivity(intent);
                finish();
            }
        }),TIME);

    }
}
