package com.example.aizat.homework2;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Aizat on 19.09.2017.
 */

public class QuestionsActivity extends FragmentHostActivity {

    @Override
    protected Fragment getFragment() {
        return QuestionFragment.newInstance();
    }
}
