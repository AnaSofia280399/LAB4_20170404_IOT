package com.example.lab4_20170404;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab4_20170404.api.GeocodingService;
import com.example.lab4_20170404.databinding.ActivityMainBinding;
import com.example.lab4_20170404.entity.Ciudad;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    //------conexion api
    GeocodingService geocodingService;
    private static String TAG = "msg-mainAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        //------conexion api
        createRetrofitService();


        //String movieId = getIntent().getStringExtra("idmb_peli");
        //http://api.openweathermap.org/geo/1.0/direct?q=London&limit=1&appid=8dd6fc3be19ceb8601c2c3e811c16cf1
        String nombre_Ciudad = "London";
        String apikey = "8dd6fc3be19ceb8601c2c3e811c16cf1";
        String limit = "1";

        geocodingService.obtenerCiudad(nombre_Ciudad, limit, apikey).enqueue(new Callback<Ciudad>() {
            @Override
            public void onResponse(Call<Ciudad> call, Response<Ciudad> response) {
                if (response.isSuccessful()){
                    Ciudad body = response.body();
                }

            }

            @Override
            public void onFailure(Call<Ciudad> call, Throwable t) {
                Log.d(TAG, "algo pasó je!!!");
                t.printStackTrace();

            }
        });


    }

    //Webservice

    public void createRetrofitService( ){
        geocodingService = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/geo/1.0/direct")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GeocodingService.class);

    }



}