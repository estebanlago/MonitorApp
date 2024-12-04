package com.example.monitorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.text.SimpleDateFormat;

import com.example.monitorapp.adapters.SensoresAdapter;
import com.example.monitorapp.datos.Repositorio;
import com.example.monitorapp.model.Ubicacion;
import com.example.monitorapp.model.Sensor;
import com.example.monitorapp.model.Registro;
import com.example.monitorapp.model.Tipo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ModificarSensorActivity extends AppCompatActivity {

    private EditText nombreSensorEditText;
    private EditText descripcionSensorEditText;
    private EditText temperaturaIdealSensorEditText;
    private Spinner  ubicacionSensorSpinner;
    private Spinner tipoSensorSpinner;
    private Button ingresarSensorButton;

    private List<Sensor> sensores;
    private List<Ubicacion> ubicaciones;
    private List<Tipo> tipos;
    private List<Registro> registros;

    private AutoCompleteTextView sensoresSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_sensor);

        sensores = Repositorio.getInstance().sensores;
        ubicaciones = new ArrayList<>();
        tipos = new ArrayList<>();
        registros = Repositorio.getInstance().registros;

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        nombreSensorEditText = findViewById(R.id.modificarNombreSensorEditText);
        descripcionSensorEditText = findViewById(R.id.modificarDescripcionSensorEditText);
        temperaturaIdealSensorEditText = findViewById(R.id.modificarTemperaturaIdealEditText);
        ubicacionSensorSpinner = findViewById(R.id.modificarUbicacionSpinner);
        tipoSensorSpinner = findViewById(R.id.modificarTipoSensorSpinner);
        ingresarSensorButton = findViewById(R.id.finalizarModifcarSensorButton);

        sensoresSpinner = findViewById(R.id.nombreSensorActualSpinner);

        sensores = new ArrayList<>();

        ArrayAdapter<Sensor> sensoresAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, sensores);
        ArrayAdapter<Ubicacion> ubicacionAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, ubicaciones);
        ArrayAdapter<Tipo> tipoAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, tipos);

        sensoresSpinner.setAdapter(sensoresAdapter);
        db.collection("sensores").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                if (doc != null) {
                                    Sensor sensor = doc.toObject(Sensor.class);
                                    sensores.add(sensor);
                                }
                                sensoresAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(ModificarSensorActivity.this,"Error al obtener datos",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

        ubicacionSensorSpinner.setAdapter(ubicacionAdapter);
        db.collection("ubicaciones").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                if (doc != null) {
                                    Ubicacion ubicacion = doc.toObject(Ubicacion.class);
                                    ubicaciones.add(ubicacion);
                                }
                                ubicacionAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(ModificarSensorActivity.this,"Error al obtener datos",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

        tipoSensorSpinner.setAdapter(tipoAdapter);
        db.collection("tipos").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                if (doc != null) {
                                    Tipo tipo = doc.toObject(Tipo.class);
                                    tipos.add(tipo);
                                }
                                tipoAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(ModificarSensorActivity.this,"Error al obtener datos",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });


        ingresarSensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreSensorEditText.getText().toString().strip().toUpperCase();
                String descripcion = descripcionSensorEditText.getText().toString().strip().toUpperCase();
                Ubicacion ubicacion = (Ubicacion) ubicacionSensorSpinner.getSelectedItem();
                Tipo tipo = (Tipo) tipoSensorSpinner.getSelectedItem();
                if (nombre.isEmpty()) {
                    Toast.makeText(ModificarSensorActivity.this, "Por favor, ingrese un nombre para el sensor.", Toast.LENGTH_LONG).show();
                    return;
                } else if (nombre.length() < 5 || nombre.length() > 15) {
                    Toast.makeText(ModificarSensorActivity.this, "El nombre debe tener entre 5 y 15 caracteres.", Toast.LENGTH_LONG).show();
                } else if (temperaturaIdealSensorEditText.getText().length() == 0 ) {
                    Toast.makeText(ModificarSensorActivity.this, "Por favor, defina la temperatura ideal.", Toast.LENGTH_LONG).show();
                    return;
                } else if (nombreYaExiste(nombre)){
                    Toast.makeText(ModificarSensorActivity.this, "El nombre del sensor ya existe.", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    float temperaturaIdeal = 0;
                    try {
                        temperaturaIdeal = Float.parseFloat(temperaturaIdealSensorEditText.getText().toString());
                        if (temperaturaIdeal <= 0) {
                            Toast.makeText(ModificarSensorActivity.this, "La temperatura ideal debe ser un número positivo.", Toast.LENGTH_LONG).show();
                            return;
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(ModificarSensorActivity.this, "Por favor, ingrese un valor numérico válido para la temperatura ideal.", Toast.LENGTH_LONG).show();
                        return;
                    }

                    Date fechaActual = new Date();

                    db.collection("sensores")
                            .whereEqualTo("nombre", nombre)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        QuerySnapshot querySnapshot = task.getResult();
                                        if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                            Toast.makeText(ModificarSensorActivity.this, "El nombre de la ubicación ya existe.", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Sensor nuevoSensor = new Sensor(nombre, descripcion, Float.parseFloat(temperaturaIdealSensorEditText.getText().toString()), ubicacion, tipo);
                                            db.collection("sensores").document().set(nuevoSensor)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(ModificarSensorActivity.this, "Ingreso exitoso de la ubicación.", Toast.LENGTH_SHORT).show();
                                                            finish();
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(ModificarSensorActivity.this, "Error al ingresar la ubicación.", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                        }
                                    } else {
                                        Toast.makeText(ModificarSensorActivity.this, "Error al verificar la disponibilidad del nombre.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                    Toast.makeText(ModificarSensorActivity.this, "Ingreso exitoso del sensor.", Toast.LENGTH_LONG).show();
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


