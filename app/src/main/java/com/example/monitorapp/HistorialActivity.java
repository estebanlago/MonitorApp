package com.example.monitorapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorapp.adapters.HistorialAdapter;
import com.example.monitorapp.adapters.SensoresAdapter;
import com.example.monitorapp.adapters.UbicacionesAdapter;
import com.example.monitorapp.datos.Repositorio;
import com.example.monitorapp.model.Ubicacion;
import com.example.monitorapp.model.Sensor;
import com.example.monitorapp.model.Registro;

import java.util.List;

public class HistorialActivity extends AppCompatActivity {

    private RecyclerView registrosRecyclerView;
    private HistorialAdapter historialAdapter;
    private List<Registro> registros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        registrosRecyclerView = findViewById(R.id.listarRegistrosRecyclerView);
        registrosRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        registros = Repositorio.getInstance().registros;
        historialAdapter = new HistorialAdapter(registros);
        registrosRecyclerView.setAdapter(historialAdapter);
    }
}