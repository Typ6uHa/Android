package com.example.aizat.weather;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Weather>{

    @BindView(R.id.time)
    TextView textView;
    @BindView(R.id.button_view)
    Button button_view;
    @BindView(R.id.temperature)
    TextView temperature;
    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);
        getLoaderManager().initLoader(Const.WEATHER_LOADER_ID, new Bundle(), MainActivity.this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder!=null){
            unbinder.unbind();
        }
    }

    @Override
    public Loader<Weather> onCreateLoader(int id, Bundle args) {
        return new WeatherLoader(this);
    }
    @OnClick(R.id.button_view)
    public void onUpdateClick(){
        getLoaderManager().initLoader(Const.WEATHER_LOADER_ID, new Bundle(), MainActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<Weather> loader, Weather data) {
        if(data!= null) {
            switch (loader.getId()) {
                case Const.WEATHER_LOADER_ID:
                    onNetworkLoadingSuccess(data);
                    break;
            }
        }
        else{
            onNetworkLoadingFailure();
        }
    }


    @Override
    public void onLoaderReset(Loader<Weather> loader) {

    }
    private void onNetworkLoadingSuccess(@Nullable Weather result) {
        if (result != null) {
            temperature.setText(String.valueOf((int) (result.getMain().getTemp()-273))+"°C");
            textView.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
            if(result.getMain().getTemp()-273 <= 0){
                Toast.makeText(this,"Одень шапку, дурачек",Toast.LENGTH_SHORT).show();
            }
        }
        getLoaderManager().destroyLoader(Const.WEATHER_LOADER_ID);
    }
    private void onNetworkLoadingFailure(){
        getLoaderManager().destroyLoader(Const.WEATHER_LOADER_ID);
        Toast.makeText(this, "Нет Интернета", Toast.LENGTH_LONG);
        Log.d("Нет интернета", "Нет Интернета");
    }
}
