package com.example.aizat.shrifti;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Aizat on 13.11.2017.
 */

public class AppDelegate extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/DigitalStripCyrillic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
