package com.example.lab4_20170404.Recycler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4_20170404.R;
import com.example.lab4_20170404.entity.Ciudad;
import com.example.lab4_20170404.entity.Clima;

import java.util.List;

public class GeoAdapter extends RecyclerView.Adapter<GeoAdapter.GeoViewHolder> {

    private Context context;

    private List<Ciudad> ciudadList;

    @NonNull
    @Override
    public GeoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext(); // Inicializar el contexto aquí
        View inflate = LayoutInflater.from(context).inflate(R.layout.geo_item, parent, false);
        return new GeoViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull GeoViewHolder holder, int position) {
        //mandamos los datos a los cards de RC views

        try{

            Ciudad ciudad = ciudadList.get(position);

            holder.ciudad = ciudad;

            //asignamos los datos

            TextView recNombre_Ciudad = holder.itemView.findViewById(R.id.recnombre_Ciudad);
            TextView recCiudad_Long = holder.itemView.findViewById(R.id.recGEO_long);
            TextView recCiudad_Lat = holder.itemView.findViewById(R.id.recGEO_lat);

            //enviamos los datoss con los valores en comillas

            recNombre_Ciudad.setText(ciudad.getName());
            recCiudad_Lat.setText("Latitud: "+ciudad.getLat());
            recCiudad_Long.setText("Longitud: "+ciudad.getLon());


        } catch (Exception e) {
        Log.e("AdapterError", "Error at position " + position + ": " + e.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return ciudadList != null ? ciudadList.size() : 0;    }

    public class GeoViewHolder extends RecyclerView.ViewHolder {

        Ciudad ciudad;

        public GeoViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    public List<Ciudad> getListaCiudad() {
        return ciudadList;
    }

    public void setListaCiudad(List<Ciudad> ciudadList) {
        this.ciudadList = ciudadList;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
