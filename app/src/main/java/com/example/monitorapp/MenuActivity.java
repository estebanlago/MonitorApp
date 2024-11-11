package com.example.monitorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_menu);

        Button ubicacionButton = findViewById(R.id.gestionarUbicacionButton);
        Button sensorButton = findViewById(R.id.gestionarSensorButton);
        Button historialButton = findViewById(R.id.gestionarHistorialButton);

        sensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SensoresActivity.class);
                startActivity(intent);
            }
        });

        ubicacionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, UbicacionesActivity.class);
                startActivity(intent);
            }
        });

        historialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, HistorialActivity.class);
                startActivity(intent);
            }
        });
    }
}