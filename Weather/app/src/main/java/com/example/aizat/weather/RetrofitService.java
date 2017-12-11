package com.example.aizat.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Aizat on 21.11.2017.
 */

public interface RetrofitService {

    @GET("data/2.5/weather")
    Call<Weather> getWeather(@Query("q") String city, @Query("appid") String id);
}
