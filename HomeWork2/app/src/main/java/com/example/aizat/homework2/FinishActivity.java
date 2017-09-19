package com.example.aizat.homework2;

import android.app.Fragment;

/**
 * Created by Aizat on 19.09.2017.
 */

public class FinishActivity extends FragmentHostActivity {
    @Override
    protected Fragment getFragment() {
        return FinishFragment.newInstance();
    }
}
