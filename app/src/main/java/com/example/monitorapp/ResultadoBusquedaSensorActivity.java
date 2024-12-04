package com.example.monitorapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorapp.adapters.SensoresAdapter;
import com.example.monitorapp.adapters.UbicacionesAdapter;
import com.example.monitorapp.datos.Repositorio;
import com.example.monitorapp.model.Sensor;
import com.example.monitorapp.model.Ubicacion;

import java.util.List;

public class ResultadoBusquedaSensorActivity extends AppCompatActivity {

    private RecyclerView sensoresRecyclerView;
    private SensoresAdapter sensoresAdapter;
    private List<Sensor> sensores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_busqueda_sensor);

        sensoresRecyclerView = findViewById(R.id.listarSensoresFiltradosRecyclerView);
        sensoresRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        sensores = Repositorio.getInstance().sensoresFiltrados;
        sensoresAdapter = new SensoresAdapter(sensores);
        sensoresRecyclerView.setAdapter(sensoresAdapter);
    }
}