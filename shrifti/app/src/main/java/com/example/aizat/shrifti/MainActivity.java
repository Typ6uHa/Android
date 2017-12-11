package com.example.aizat.shrifti;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

// TODO Picasso , GLide
public class MainActivity extends AppCompatActivity {

    public String [] name;

    public String [] photos;

    private RecyclerView recyclerView;

    private Adapter adapter;

    private LinearLayoutManager linearLayoutManager;

    private MaterialDialog materialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photos= getResources().getStringArray(R.array.URLs);
        name = getResources().getStringArray(R.array.name);

        recyclerView = findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        materialDialog = new MaterialDialog(MainActivity.this);

        materialDialog.setNegativeButton("Выйти из приложения", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        materialDialog.setPositiveButton("Загрузить изображения", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter = new Adapter(photos,name);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
                materialDialog.dismiss();
            }
        });
        materialDialog.show();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
