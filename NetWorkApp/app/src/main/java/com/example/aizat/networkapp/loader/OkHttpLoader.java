package com.example.aizat.networkapp.loader;

import android.content.Context;
import android.util.Log;

import com.example.aizat.networkapp.api.OkHttpService;

import java.io.IOException;

/**
 * Created by Aizat on 30.10.2017.
 */

public class OkHttpLoader extends BaseAsyncTaskLoader<String>{

    private static final String LOG_TAG = "OkHttpLoader";

    private String url;

    public OkHttpLoader(Context context,String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected String onLoad() {
        try{
            return  new OkHttpService().load(url);
        } catch (IOException e){
            Log.e(LOG_TAG, e.getMessage());
        }
        return null;
    }
}
