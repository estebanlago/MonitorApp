package com.example.monitorapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorapp.R;
import com.example.monitorapp.model.Sensor;
import com.example.monitorapp.model.Registro;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import java.util.List;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.ViewHolder> {

    private List<Registro> registros;

    public HistorialAdapter(List<Registro> registros) {
        this.registros = registros;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_historial, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String fechaActual = dateFormat.format(registros.get(position).getInstante());

        holder.getTextViewRegistroInstante().setText(fechaActual);

        float temperaturaLector = registros.get(position).getLectura();
        String temperatureString = String.valueOf(temperaturaLector);

        holder.getTextViewRegistroLectura().setText(temperatureString);

        holder.getTextViewNombreSensor().setText(registros.get(position).getSensor().getNombre());
    }

    @Override
    public int getItemCount() {
        return registros.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewNombreSensor;
        private TextView textViewRegistroInstante;
        private TextView textViewRegistroLectura;

        public ViewHolder(View view) {
            super(view);
            textViewNombreSensor = view.findViewById(R.id.nombreSensorRegistro);
            textViewRegistroInstante= view.findViewById(R.id.instanteDelSensorTextView);
            textViewRegistroLectura = view.findViewById(R.id.lecturaSensorTextView);
        }

        public TextView getTextViewNombreSensor() {
            return textViewNombreSensor;
        }

        public TextView getTextViewRegistroInstante() {
            return textViewRegistroInstante;
        }

        public TextView getTextViewRegistroLectura() {
            return textViewRegistroLectura;
        }
    }

}