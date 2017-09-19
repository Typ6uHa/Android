package com.example.aizat.homework2;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by Aizat on 19.09.2017.
 */

public class FinishFragment extends Fragment {

    public static FinishFragment newInstance() {
        Bundle args = new Bundle();
        //args.putString();
        FinishFragment fragment = new FinishFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
