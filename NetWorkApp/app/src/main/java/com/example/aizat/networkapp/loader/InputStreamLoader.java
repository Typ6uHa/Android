package com.example.aizat.networkapp.loader;

import android.content.Context;
import android.util.Log;

import com.example.aizat.networkapp.api.InputStreamService;

import java.io.IOException;

/**
 * Created by Aizat on 30.10.2017.
 */

public class InputStreamLoader extends BaseAsyncTaskLoader<String> {

    private static  final String LOG_TAG = "InputStreamLoader";

    private String url;

    public InputStreamLoader(Context context, String url){
        super(context);
        this.url = url;
    }
    @Override
    protected String onLoad() {
        try{
            return new InputStreamService().load(url);
        } catch (IOException e){
            Log.e(LOG_TAG, e.getMessage());
        }
        return null;
    }
}
