package com.example.monitorapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorapp.adapters.SensoresAdapter;
import com.example.monitorapp.adapters.UbicacionesAdapter;
import com.example.monitorapp.datos.Repositorio;
import com.example.monitorapp.model.Ubicacion;
import com.example.monitorapp.model.Sensor;

import java.util.List;

public class ListarSensoresActivity extends AppCompatActivity {

    private RecyclerView sensoresRecyclerView;
    private SensoresAdapter sensoresAdapter;
    private List<Sensor> sensores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_sensores);

        sensoresRecyclerView= findViewById(R.id.listarSensoresRecyclerView);
        sensoresRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        sensores = Repositorio.getInstance().sensores;
        sensoresAdapter= new SensoresAdapter(sensores);
        sensoresRecyclerView.setAdapter(sensoresAdapter);
    }
}