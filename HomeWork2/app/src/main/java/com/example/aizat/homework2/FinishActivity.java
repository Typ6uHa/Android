package com.example.aizat.homework2;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Aizat on 19.09.2017.
 */

public class FinishActivity extends FragmentHostActivity {

    private final String KEY = "key";

    @Override
    protected Fragment getFragment() {
        return FinishFragment.newInstance(getData());
    }

    private Bundle getData() {
        Intent intent = getIntent();
        // TODO дефонлтное значение(0) в константы
        int result = intent.getIntExtra(KEY,0);
        Bundle dataForFragment = new Bundle();
        dataForFragment.putInt(KEY,result);
        return dataForFragment;
    }
    @Override
    public void onBackPressed() {}
}
