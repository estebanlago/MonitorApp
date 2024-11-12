package com.example.monitorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.monitorapp.datos.Repositorio;
import com.example.monitorapp.model.Ubicacion;
import java.util.List;

public class CrearUbicacionActivity extends AppCompatActivity {

    private EditText nombreUbicacionEditText;
    private EditText descripcionUbicacionEditText;
    private Button ingresarUbicacionButton;
    private List<Ubicacion> ubicaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_ubicacion);

        ubicaciones = Repositorio.getInstance().ubicaciones;

        nombreUbicacionEditText = findViewById(R.id.ingresarUbicacionEditText);
        descripcionUbicacionEditText = findViewById(R.id.ingresarDescripcionEditText);
        ingresarUbicacionButton = findViewById(R.id.finalizarUbicacionButton);

        ingresarUbicacionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreUbicacionEditText.getText().toString();
                String descripcion = descripcionUbicacionEditText.getText().toString();
                Ubicacion nuevaUbicacion = new Ubicacion(nombre, descripcion);
                ubicaciones.add(nuevaUbicacion);
                Toast.makeText(CrearUbicacionActivity.this, "Ingreso exitoso", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}

