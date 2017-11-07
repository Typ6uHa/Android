package com.example.aizat.networkapp.api;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Aizat on 30.10.2017.
 */

public interface RetrofitService {

    @GET("/feed/subscriptions")
    Call<ResponseBody> getData();
}
