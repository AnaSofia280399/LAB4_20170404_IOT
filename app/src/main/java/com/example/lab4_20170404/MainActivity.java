package com.example.lab4_20170404;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);

        //Ingreso a los fragments

        binding.botonIngresar.setOnClickListener(v -> {
            if (!internet_access()) {
                internet_off_dialog();
            } else {
                startActivity(new Intent(MainActivity.this, NavigationActivity.class));
            }
        });

        //------conexion api prueba....

        //createRetrofitService();


        //String movieId = getIntent().getStringExtra("idmb_peli");
        //http://api.openweathermap.org/geo/1.0/direct?q=London&limit=1&appid=8dd6fc3be19ceb8601c2c3e811c16cf1
        String nombre_Ciudad = "London";
        String apikey = "8dd6fc3be19ceb8601c2c3e811c16cf1";
        String limit = "1";




    }

    //Webservice

    public void createRetrofitService( ){
        geocodingService = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/geo/1.0/direct")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GeocodingService.class);

    }

    public boolean internet_access() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void internet_off_dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No hay conexión a Internet");
        builder.setMessage("Para utilizar esta aplicación es necesario tener una conexión a Internet.");
        builder.setPositiveButton("Configuración", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Abrir la configuración de red del dispositivo
                startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
            }
        });
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Cerrar la actividad o realizar alguna otra acción si se desea
                // Por ejemplo, puedes llamar a finish() para cerrar la actividad
                finish();
            }
        });
        builder.setCancelable(false); // Evita que se pueda cerrar el cuadro de diálogo con clic fuera de él
        builder.show();
    }



}