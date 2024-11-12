package com.example.monitorapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorapp.R;
import com.example.monitorapp.model.Ubicacion;

import java.util.List;

public class UbicacionesAdapter extends RecyclerView.Adapter<UbicacionesAdapter.ViewHolder> {

    private List<Ubicacion> ubicaciones;

    public UbicacionesAdapter(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_ubicaciones, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextViewNombre().setText(ubicaciones.get(position).getNombre());
        holder.getTextViewDescripcion().setText(ubicaciones.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return ubicaciones.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewNombre;
        private TextView textViewDescripcion;

        public ViewHolder(View view) {
            super(view);
            textViewNombre = view.findViewById(R.id.nombreUbicacion);
            textViewDescripcion = view.findViewById(R.id.descripcionUbicacion);
        }

        public TextView getTextViewNombre() {
            return textViewNombre;
        }

        public TextView getTextViewDescripcion() {
            return textViewDescripcion;
        }
    }

}