package com.example.aizat.weather;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Lenovo on 19.11.2017.
 */

public class WeatherLoader extends AsyncTaskLoader<Weather> {
    public WeatherLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Weather loadInBackground() {
        RetrofitService service = ((AppDelegate) getContext().getApplicationContext()).getRetrofit();
        Call<Weather> weatherCall = service.getWeather("Kazan", Const.API_KEY);
        Weather weather = null;
        try {
            Response<Weather> response = weatherCall.execute();
            if (response.errorBody() != null) {                                //обработка ошибки
                Toast.makeText(getContext(), "Не удалось получить погоду", Toast.LENGTH_SHORT).show();
                System.out.println("ERROR BODY");
            } else {
                weather = response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return weather;
    }
}
