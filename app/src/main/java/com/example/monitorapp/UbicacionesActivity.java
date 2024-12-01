package com.example.monitorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.monitorapp.CrearUbicacionActivity;

public class UbicacionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_ubicaciones);

        Button agregarUbicacionButton = findViewById(R.id.agregarUbicacionButton);

        Button listarUbicacionesButton = findViewById(R.id.listarUbicacionesButton);

        Button modificarUbicacionButton = findViewById(R.id.modificarUbicacionButton);

        agregarUbicacionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UbicacionesActivity.this, CrearUbicacionActivity.class);
                startActivity(intent);
            }
        });

        listarUbicacionesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UbicacionesActivity.this, ListarUbicacionesActivity.class);
                startActivity(intent);
            }
        });

        modificarUbicacionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UbicacionesActivity.this, ModificarUbicacionActivity.class);
                startActivity(intent);
            }
        });
    }
}