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
    }

    @Override
    public int getItemCount() {
        return sensores.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewNombre;
        private TextView textViewDescripcion;

        public ViewHolder(View view) {
            super(view);
            textViewNombre = view.findViewById(R.id.nombreSensor);
            textViewDescripcion = view.findViewById(R.id.descripcionSensor);
        }

        public TextView getTextViewNombre() {
            return textViewNombre;
        }

        public TextView getTextViewDescripcion() {
            return textViewDescripcion;
        }
    }

}