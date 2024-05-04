package com.example.lab4_20170404.api;

import com.example.lab4_20170404.entity.Clima;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("/data/2.5/weather?")
    Call<Clima> getClima(@Query("lat") String ciudad,
                         @Query("lon") String limit,
                         @Query("appid") String api
    );
}
