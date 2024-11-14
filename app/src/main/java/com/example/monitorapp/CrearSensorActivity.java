package com.example.monitorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.monitorapp.datos.Repositorio;
import com.example.monitorapp.model.Ubicacion;
import com.example.monitorapp.model.Sensor;
import com.example.monitorapp.model.Tipo;
import java.util.List;

public class CrearSensorActivity extends AppCompatActivity {

    private EditText nombreSensorEditText;
    private EditText descripcionSensorEditText;
    private EditText temperaturaIdealSensorEditText;
    private Spinner  ubicacionSensorSpinner;
    private Spinner tipoSensorSpinner;
    private Button ingresarSensorButton;

    private List<Sensor> sensores;
    private List<Ubicacion> ubicaciones;
    private List<Tipo> tipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_sensor);

        sensores = Repositorio.getInstance().sensores;
        ubicaciones = Repositorio.getInstance().ubicaciones;
        tipos = Repositorio.getInstance().tipos;

        nombreSensorEditText = findViewById(R.id.ingresarSensorEditText);
        descripcionSensorEditText = findViewById(R.id.ingresarDescripcionSensorEditText);
        temperaturaIdealSensorEditText = findViewById(R.id.ingresarTemperaturaIdealEditText);
        ubicacionSensorSpinner = findViewById(R.id.ubicacionSpinner);
        tipoSensorSpinner = findViewById(R.id.tipoSensorSpinner);
        ingresarSensorButton = findViewById(R.id.finalizarSensorButton);

        ArrayAdapter<Ubicacion> ubicacionAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, ubicaciones);
        ArrayAdapter<Tipo> tipoAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, tipos);

        ubicacionSensorSpinner.setAdapter(ubicacionAdapter);
        tipoSensorSpinner.setAdapter(tipoAdapter);

        ingresarSensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreSensorEditText.getText().toString().strip().toUpperCase();
                String descripcion = descripcionSensorEditText.getText().toString().strip().toUpperCase();
                Ubicacion ubicacion = (Ubicacion) ubicacionSensorSpinner.getSelectedItem();
                Tipo tipo = (Tipo) tipoSensorSpinner.getSelectedItem();
                if (nombre.isEmpty()) {
                    Toast.makeText(CrearSensorActivity.this, "Por favor, ingrese un nombre para el sensor.", Toast.LENGTH_LONG).show();
                    return;
                } else if (nombre.length() < 5 ) {
                    Toast.makeText(CrearSensorActivity.this, "El nombre debe tener por lo menos 5 caracteres.", Toast.LENGTH_LONG).show();
                } else if (temperaturaIdealSensorEditText.getText().length() == 0 ) {
                    Toast.makeText(CrearSensorActivity.this, "Por favor, defina la temperatura ideal.", Toast.LENGTH_LONG).show();
                    return;
                } else if (nombreYaExiste(nombre)){
                    Toast.makeText(CrearSensorActivity.this, "El nombre del sensor ya existe.", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    float temperaturaIdeal = Float.parseFloat(temperaturaIdealSensorEditText.getText().toString());
                    Sensor nuevoSensor = new Sensor(nombre, descripcion, temperaturaIdeal, ubicacion, tipo);
                    sensores.add(nuevoSensor);
                    Toast.makeText(CrearSensorActivity.this, "Ingreso exitoso del sensor.", Toast.LENGTH_LONG).show();
                    finish();
            }}
        });
    }
    private boolean nombreYaExiste(String nombre) {
        for (Sensor sensor : sensores) {
            if (sensor.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }
}


