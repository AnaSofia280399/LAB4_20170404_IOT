package com.example.lab4_20170404;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lab4_20170404.Recycler.ClimaAdapter;
import com.example.lab4_20170404.api.WeatherService;
import com.example.lab4_20170404.databinding.FragmentClimaBinding;
import com.example.lab4_20170404.entity.Clima;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link climaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class climaFragment extends Fragment {

    FragmentClimaBinding climaBinding;

    private List<Clima> search_clima = new ArrayList<>();
    //Lista de Ciudades ^

    //------API
    WeatherService weatherService;

    //-----------

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public climaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment clima.
     */
    // TODO: Rename and change types and number of parameters
    public static climaFragment newInstance(String param1, String param2) {
        climaFragment fragment = new climaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        climaBinding= climaBinding.inflate(inflater, container, false);

        //----------Llamada Weather Service----

        weatherService = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherService.class);

        //-------------

       /* climaBinding.ClimaButtonSearch.setOnClickListener(view -> {
            fetchInfo(climaBinding.ClimaLatitudSearch.getQuery().toString(), climaBinding.buscarLongitud.getQuery().toString());
        }); */

        climaBinding.ClimaButtonSearch.setOnClickListener(view -> {
            String latitud = climaBinding.ClimaLatitudSearch.getQuery().toString().trim();
            String longitud = climaBinding.buscarLongitud.getQuery().toString().trim();

            if (!latitud.isEmpty() && !longitud.isEmpty()) {
                fetchInfo(latitud, longitud);
            } else {
                // Muestra un mensaje de error si alguno de los campos está vacío
                Toast.makeText(view.getContext(), "Por favor, ingresa valores de latitud y longitud.", Toast.LENGTH_LONG).show();
            }
        });




        NavController navController = NavHostFragment.findNavController(climaFragment.this);
        climaBinding.geoDeClima.setOnClickListener(view -> {

            navController.navigate(R.id.action_nav_gallery_to_nav_home);
        });




        // Inflate the layout for this fragment
        return climaBinding.getRoot();
    }

    public void fetchInfo(String lat, String lon) {
        if (InternetAccess()) {
            weatherService.getClima(lat, lon, "792edf06f1f5ebcaf43632b55d8b03fe").enqueue(new Callback<Clima>() {
                @Override
                public void onResponse(Call<Clima> call, Response<Clima> response) {
                    if (response.isSuccessful()) {
                        Clima clima = response.body();
                        if (clima != null) {
                            search_clima.add(clima);

                            ClimaAdapter climaAdapter = new ClimaAdapter();
                            climaAdapter.setListaClima(search_clima);
                            climaBinding.recycleClima.setAdapter(climaAdapter);
                            climaBinding.recycleClima.setLayoutManager(new LinearLayoutManager(getContext()));
                        }
                    }
                }

                @Override
                public void onFailure(Call<Clima> call, Throwable t) {
                    Log.e("fetchInfo", "Error al realizar la solicitud: " + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Log.d("fetchInfo", "No hay conexión a internet");
        }
    }

    public boolean InternetAccess() {
        Context context = getActivity();
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager != null) {
                NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isConnected();
            }
        }
        return false;
    }
}