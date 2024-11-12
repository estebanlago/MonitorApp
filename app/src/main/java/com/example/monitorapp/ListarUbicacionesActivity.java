package com.example.monitorapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorapp.adapters.UbicacionesAdapter;
import com.example.monitorapp.datos.Repositorio;
import com.example.monitorapp.model.Ubicacion;

import java.util.List;

public class ListarUbicacionesActivity extends AppCompatActivity {

    private RecyclerView ubicacionesRecyclerView;
    private UbicacionesAdapter ubicacionesAdapter;
    private List<Ubicacion> ubicaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ubicaciones);

        ubicacionesRecyclerView = findViewById(R.id.listarUbicacionesRecyclerView);
        ubicacionesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ubicaciones = Repositorio.getInstance().ubicaciones;
        ubicacionesAdapter = new UbicacionesAdapter(ubicaciones);
        ubicacionesRecyclerView.setAdapter(ubicacionesAdapter);
    }
}