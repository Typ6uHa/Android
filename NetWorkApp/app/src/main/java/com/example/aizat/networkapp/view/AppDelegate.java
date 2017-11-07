package com.example.aizat.networkapp.view;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.aizat.networkapp.api.RetrofitService;

import retrofit2.Retrofit;

/**
 * Created by Aizat on 30.10.2017.
 */

public class AppDelegate extends Application {

private RetrofitService retrofit;

    @Override
    public void onCreate() {
        super.onCreate();


        retrofit = new Retrofit.Builder()
                .baseUrl(Const.URL_GET1)
                .build()
                .create(RetrofitService.class);
    }

    @NonNull
    public RetrofitService getRetrofit() {
        return retrofit;
    }
}
