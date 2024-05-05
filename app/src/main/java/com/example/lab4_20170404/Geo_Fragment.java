package com.example.lab4_20170404;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
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

import com.example.lab4_20170404.Recycler.GeoAdapter;
import com.example.lab4_20170404.api.GeocodingService;
import com.example.lab4_20170404.databinding.FragmentGeoBinding;
import com.example.lab4_20170404.entity.Ciudad;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Geo_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Geo_Fragment extends Fragment {

    GeocodingService geocodingService;

    FragmentGeoBinding fragmentGeoBinding;
    //-----Sensor (prueba)
    SensorManager sensorManager;



    private List<Ciudad> ciudad_search = new ArrayList<>();
    //Lista de Ciudades ^


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Geo_Fragment() {
        // Empty public constructor
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
    public static Geo_Fragment newInstance(String param1, String param2) {
        Geo_Fragment fragment = new Geo_Fragment();
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

        fragmentGeoBinding = FragmentGeoBinding.inflate(inflater, container, false);


        //-------------------llamado a la Api
        geocodingService = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GeocodingService.class);
        //----------------------------------

        //---------SENSORES----------------

        sensorManager = (SensorManager) requireContext().getSystemService(SENSOR_SERVICE);


        
        //--------------------------------------





        NavController navController = NavHostFragment.findNavController(Geo_Fragment.this);
        fragmentGeoBinding.climaDeGeo.setOnClickListener(view -> {

            navController.navigate(R.id.action_nav_home_to_nav_gallery);
        });

        //ingreso de datos a buscar

        /*fragmentGeoBinding.GEOButtonSearch.setOnClickListener(view -> {
            fetchInfo(fragmentGeoBinding.GeobuscarCiudad.getQuery().toString());
        });
        // Inflate the layout for this fragment
        return fragmentGeoBinding.getRoot(); */

        fragmentGeoBinding.GEOButtonSearch.setOnClickListener(view -> {
            String query = fragmentGeoBinding.GeobuscarCiudad.getQuery().toString().trim(); // Obtiene el texto y elimina espacios al inicio y al final
            if (!query.isEmpty()) {
                fetchInfo(query);
            } else {
                // Muestra un mensaje de error si el campo está vacío
                Toast.makeText(view.getContext(), "Por favor, ingresa un nombre de ciudad para buscar.", Toast.LENGTH_LONG).show();
            }
        });
        return fragmentGeoBinding.getRoot();
    }


    private void checkSensorAvailability(SensorManager sensorManager) {
        if (sensorManager == null) {
            showToast("Su dispositivo no posee sensores :(");
            return;
        }

        checkIndividualSensor(sensorManager, Sensor.TYPE_ACCELEROMETER, "Acelerómetro");
        checkIndividualSensor(sensorManager, Sensor.TYPE_MAGNETIC_FIELD, "Magnetómetro");

        logAvailableSensors(sensorManager);
    }

    private void checkIndividualSensor(SensorManager sensorManager, int sensorType, String sensorName) {
        Sensor sensor = sensorManager.getDefaultSensor(sensorType);
        if (sensor != null) {
            showToast(sensorName + " Ok");
        } else {
            showToast("No cuenta con " + sensorName);
        }
    }

    private void logAvailableSensors(SensorManager sensorManager) {
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensorList) {
            Log.d("msg-test-sensorList", "sensorName: " + sensor.getName());
        }
    }




    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void fetchInfo(String Nombre_ciudad) {
        if (InternetAccess()) {
            geocodingService.obtenerCiudad(Nombre_ciudad, "1", "8dd6fc3be19ceb8601c2c3e811c16cf1").enqueue(new Callback<List<Ciudad>>() {
                @Override
                public void onResponse(Call<List<Ciudad>> call, Response<List<Ciudad>> response) {
                    if (response.isSuccessful()) {
                        List<Ciudad> ciudadList = response.body();
                        if (ciudadList != null && !ciudadList.isEmpty()) {
                            Ciudad ciudad = ciudadList.get(0);
                            ciudad_search.addAll(ciudadList);

                            GeoAdapter geoAdapter = new GeoAdapter();
                            geoAdapter.setListaCiudad(ciudad_search);
                            fragmentGeoBinding.recycleGeo.setAdapter(geoAdapter);
                            fragmentGeoBinding.recycleGeo.setLayoutManager(new LinearLayoutManager(getContext()));

                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Ciudad>> call, Throwable t) {
                    Log.e("fetchInfo", "Error solicitud: " + t.getMessage());
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