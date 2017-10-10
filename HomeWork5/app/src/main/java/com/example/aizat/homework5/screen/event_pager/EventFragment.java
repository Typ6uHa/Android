package com.example.aizat.homework5.screen.event_pager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aizat.homework5.R;
import com.example.aizat.homework5.model.Event;

/**
 * Created by Aizat on 06.10.2017.
 */

public class EventFragment extends Fragment {

    private static final String KEY_EVENT = "event";

    public ImageView photoImageView;

    public TextView titleTextView;

    public TextView descriptionTextView;

    public FloatingActionButton floatingActionButton;

    public static EventFragment newInstance(Event event) {
        Bundle args = new Bundle();
        args.putParcelable(KEY_EVENT, event);
        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        final Event event = getArguments().getParcelable(KEY_EVENT);

        photoImageView = view.findViewById(R.id.image_view);
        photoImageView.setImageResource(event.getPhotoId());

        titleTextView = view.findViewById(R.id.title_view_name);
        titleTextView.setText(event.getTitle());

        descriptionTextView = view.findViewById(R.id.description_view_name);
        descriptionTextView.setText(event.getDescription());

        floatingActionButton = view.findViewById(R.id.phone_view);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel" + event.getNumber()));
                startActivity(intent);
            }
        });

        return view;
    }
}
