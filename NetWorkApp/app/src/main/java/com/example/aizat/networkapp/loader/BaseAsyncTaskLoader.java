package com.example.aizat.networkapp.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by Aizat on 30.10.2017.
 */

public abstract class BaseAsyncTaskLoader <R> extends AsyncTaskLoader {

    public BaseAsyncTaskLoader(Context context) {
        super(context);
    }

    protected abstract R onLoad();

    @Override
    public Object loadInBackground() {
        return onLoad();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
