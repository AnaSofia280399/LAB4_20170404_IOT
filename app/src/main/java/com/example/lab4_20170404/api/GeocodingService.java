package com.example.lab4_20170404.api;

import com.example.lab4_20170404.entity.Ciudad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeocodingService {
    @GET("/")
    Call<Ciudad> obtenerCiudad(
            @Query("q") String ciudad,
            @Query("limit") String limit,
            @Query("appid") String apikey
    );

}
