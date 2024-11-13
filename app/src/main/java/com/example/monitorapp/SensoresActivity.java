package com.example.monitorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.monitorapp.CrearUbicacionActivity;

public class SensoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_sensores);

        Button agregarUbicacionButton = findViewById(R.id.agregarNuevoSensorButton);

        Button listarUbicacionesButton = findViewById(R.id.listarSensoresButton);

        agregarUbicacionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SensoresActivity.this, CrearSensorActivity.class);
                startActivity(intent);
            }
        });

        listarUbicacionesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SensoresActivity.this, ListarSensoresActivity.class);
                startActivity(intent);
            }
        });
    }
}
