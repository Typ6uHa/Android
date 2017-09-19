package com.example.aizat.homework2;

import android.app.Fragment;
import android.widget.Button;

public class HomeActivity extends FragmentHostActivity {

    protected Fragment getFragment () {
        return HomeFragment.newInstance();
    }
}
