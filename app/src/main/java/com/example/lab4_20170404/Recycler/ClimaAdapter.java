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
import com.example.lab4_20170404.entity.Clima;

import java.util.List;

public class ClimaAdapter extends RecyclerView.Adapter<ClimaAdapter.ClimaViewHolder> {

    private List<Clima> climaList;
    private Context context;

    @NonNull
    @Override
    public ClimaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext(); // Inicializar el contexto aquí
        View inflate = LayoutInflater.from(context).inflate(R.layout.clima_item, parent, false);
        return new ClimaViewHolder(inflate);


    }

    @Override
    public void onBindViewHolder(@NonNull ClimaViewHolder holder, int position) {

        //mandmos los datos a los cards de RC views
        try{

            Clima clima = climaList.get(position);

            holder.clima = clima;

            //asignamos los datos

            TextView recNombre_CiudadClima = holder.itemView.findViewById(R.id.recnombre_CiudadClima);
            TextView recClima_Min = holder.itemView.findViewById(R.id.recClima_Min);
            TextView recClima_Max = holder.itemView.findViewById(R.id.recClima_Max);
            TextView recClima_Temp = holder.itemView.findViewById(R.id.recClima_Temp);
            TextView recClima_Viento = holder.itemView.findViewById(R.id.recClima_Viento);

            //enviamos los datoss con los valores en comillas

            recNombre_CiudadClima.setText(clima.getName());
            recClima_Min.setText("Mín: "+clima.getMain().getTemp_min());
            recClima_Max.setText("Máx: "+clima.getMain().getTemp_max());
            recClima_Temp.setText(clima.getMain().getTemp());
            recClima_Viento.setText("Viento: "+clima.getWind().getDeg());

        }catch (Exception e) {
            Log.e("AdapterError", "Error at position " + position + ": " + e.getMessage());
        }



    }

    @Override
    public int getItemCount() {
        return climaList.size();
    }


    public class ClimaViewHolder extends RecyclerView.ViewHolder {

        Clima clima;

        public ClimaViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    public List<Clima> getListaClima() {
        return climaList;
    }

    public void setListaClima(List<Clima> climaList) {
        this.climaList = climaList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


}
