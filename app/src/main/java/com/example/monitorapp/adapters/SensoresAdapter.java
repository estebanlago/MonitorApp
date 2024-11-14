package com.example.monitorapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorapp.R;
import com.example.monitorapp.model.Sensor;

import java.util.List;

public class SensoresAdapter extends RecyclerView.Adapter<SensoresAdapter.ViewHolder> {

    private List<Sensor> sensores;

    public SensoresAdapter(List<Sensor> sensores) {
        this.sensores = sensores;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_sensores, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextViewNombre().setText(sensores.get(position).getNombre());
        holder.getTextViewDescripcion().setText(sensores.get(position).getDescripcion());

        float idealTemperature = sensores.get(position).getIdeal();
        String temperatureString = String.valueOf(idealTemperature);
        holder.getTextViewTemperatura().setText(temperatureString);

        holder.getTextViewUbicacion().setText(sensores.get(position).getUbicacion().getNombre());
        holder.getTextViewTipoSensor().setText(sensores.get(position).getTipo().getNombre());
    }

    @Override
    public int getItemCount() {
        return sensores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewNombre;
        private TextView textViewDescripcion;
        private TextView textViewTemperatura;
        private TextView textViewUbicacion;
        private TextView textViewTipoSensor;

        public ViewHolder(View view) {
            super(view);
            textViewNombre = view.findViewById(R.id.nombreSensor);
            textViewDescripcion = view.findViewById(R.id.descripcionSensor);
            textViewTemperatura = view.findViewById(R.id.temperaturaDelSensorTextView);
            textViewUbicacion = view.findViewById(R.id.ubicacionDelSensorTextView);
            textViewTipoSensor = view.findViewById(R.id.tipoDelSensorTextView);
        }

        public TextView getTextViewNombre() {
            return textViewNombre;
        }

        public TextView getTextViewDescripcion() {
            return textViewDescripcion;
        }

        public TextView getTextViewTemperatura() {
            return textViewTemperatura;
        }

        public TextView getTextViewUbicacion() {
            return textViewUbicacion;
        }

        public TextView getTextViewTipoSensor() {
            return textViewTipoSensor;
        }
    }

}