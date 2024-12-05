package com.example.monitorapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

public class EliminarSensorActivity extends AppCompatActivity {

    private Button ingresarSensorButton;
    private ImageView botonDropdownSensores;

    private List<Sensor> sensores;
    private List<Ubicacion> ubicaciones;
    private List<Tipo> tipos;
    private List<Registro> registros;

    private AutoCompleteTextView sensoresSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_sensor);

        sensores = Repositorio.getInstance().sensores;
        ubicaciones = new ArrayList<>();
        tipos = new ArrayList<>();
        registros = Repositorio.getInstance().registros;

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ingresarSensorButton = findViewById(R.id.eliminarApartadoSensorButton);
        botonDropdownSensores = findViewById(R.id.dropdownSensorImageView);

        sensoresSpinner = findViewById(R.id.nombreSensorActualAEliminarSpinner);

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
                            Toast.makeText(EliminarSensorActivity.this,"Error al obtener datos",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

        ingresarSensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sensorAEliminar = sensoresSpinner.getText().toString().trim().toUpperCase();

                if (sensorAEliminar.isEmpty()) {
                    Toast.makeText(EliminarSensorActivity.this, "Por favor, selecciona un sensor.", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                new AlertDialog.Builder(EliminarSensorActivity.this)
                        .setTitle("Confirmar eliminación")
                        .setMessage("¿Estás seguro de que quieres eliminar este sensor?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                db.collection("sensores")
                                        .whereEqualTo("nombre", sensorAEliminar)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    if (!task.getResult().isEmpty()) {
                                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                                            db.collection("sensores").document(document.getId()).delete()
                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void aVoid) {
                                                                            Toast.makeText(EliminarSensorActivity.this, "Sensor eliminado correctamente.", Toast.LENGTH_SHORT).show();
                                                                            finish();
                                                                            overridePendingTransition(0, 0);
                                                                            startActivity(getIntent());
                                                                            overridePendingTransition(0, 0);
                                                                        }
                                                                    })
                                                                    .addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            Toast.makeText(EliminarSensorActivity.this, "Error al eliminar el sensor.", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    });
                                                            break;
                                                        }
                                                    } else {
                                                        Toast.makeText(EliminarSensorActivity.this, "El sensor no existe.", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(EliminarSensorActivity.this, "Error al verificar la existencia del sensor.", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User clicked No, do nothing
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        botonDropdownSensores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensoresSpinner.showDropDown();
            }
        });
    }
}
