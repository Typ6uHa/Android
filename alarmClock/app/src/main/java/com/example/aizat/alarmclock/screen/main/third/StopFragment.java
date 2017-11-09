package com.example.aizat.alarmclock.screen.main.third;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.aizat.alarmclock.R;
import com.example.aizat.alarmclock.screen.base.BaseFragment;
import com.example.aizat.alarmclock.screen.main.first.MainActivity;
import com.example.aizat.alarmclock.screen.main.first.wakeUp.RingtonePlayingService;
import com.example.aizat.alarmclock.screen.main.second.SecondActivity;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by Aizat on 09.11.2017.
 */

public class StopFragment extends BaseFragment {

    private Button button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stop,container,false);

        button = view.findViewById(R.id.stop);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent service_intent = new Intent(getContext(),RingtonePlayingService.class);
                service_intent.putExtra("key","alarm off");
                getContext().startService(service_intent);

                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public static StopFragment newInstance() {
        Bundle args = new Bundle();
        StopFragment fragment = new StopFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
