package com.example.aizat.homework2;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Aizat on 19.09.2017.
 */

public class FinishFragment extends Fragment {

    private final String KEY = "key";
    private static final String TAG_NEXT_STEP = " TAG";

    private Button restart;
    private Button exit;

    private TextView textView;

    public static FinishFragment newInstance(Bundle args) {

        FinishFragment fragment = new FinishFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finish,container, false);

        restart = view.findViewById(R.id.repeat);
        exit = view.findViewById(R.id.goOut);
        textView = view.findViewById(R.id.result);
        final int count = getArguments().getInt("key");
        final int questions = getResources().getIntArray(R.array.right_answers).length;
        textView.setText(String.valueOf(count)+" / "+questions);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllertDialogExit fragment = new AllertDialogExit(count,questions);
                fragment.show(getFragmentManager(),TAG_NEXT_STEP);
            }
        });
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllertDialogRestart fragment = new AllertDialogRestart(count,questions);
                fragment.show(getFragmentManager(),TAG_NEXT_STEP);
            }
        });


        return view;
    }
    public void onBackPressed(){

    }
}
