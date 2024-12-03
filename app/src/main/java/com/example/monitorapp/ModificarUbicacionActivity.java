package com.example.monitorapp;

import android.content.Intent;
import android.R.layout;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.monitorapp.datos.Repositorio;
import com.example.monitorapp.model.Ubicacion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ModificarUbicacionActivity extends AppCompatActivity {

    private EditText nombreUbicacionEditText;
    private EditText descripcionUbicacionEditText;
    private Button modificarUbicacionButton;
    private List<Ubicacion> ubicaciones;
    private Ubicacion ubicacion;
    private ImageView botonDropdown1;

    private AutoCompleteTextView ubicacionesSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_ubicacion);

        nombreUbicacionEditText = findViewById(R.id.ingresarNuevaUbicacionEditText);
        descripcionUbicacionEditText = findViewById(R.id.ingresarNuevaDescripcionEditText);
        modificarUbicacionButton = findViewById(R.id.finalizarModificarUbicacionButton);
        botonDropdown1 = findViewById(R.id.imageView1);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ubicacionesSpinner = findViewById(R.id.nombreUbicacionSpinner);

        ubicaciones = new ArrayList<>();

        ArrayAdapter<Ubicacion> ubicacionAdapter = new ArrayAdapter<>(this, layout.simple_dropdown_item_1line, ubicaciones);

        ubicacionesSpinner.setAdapter(ubicacionAdapter);

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
                            Toast.makeText(ModificarUbicacionActivity.this,"Error al obtener datos",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

        modificarUbicacionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreUbicacionEditText.getText().toString().strip().toUpperCase();
                String descripcion = descripcionUbicacionEditText.getText().toString().strip().toUpperCase();
                String nombreAnterior = ubicacionesSpinner.getText().toString().strip().toUpperCase();

                if (nombre.isEmpty()) {
                    Toast.makeText(ModificarUbicacionActivity.this, "Por favor, ingrese un nombre para la ubicación.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (nombre.length() < 5 || nombre.length() > 15) {
                    Toast.makeText(ModificarUbicacionActivity.this, "El nombre debe tener entre 5 y 15 caracteres.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    db.collection("ubicaciones").whereEqualTo("nombre", nombreAnterior)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot doc : task.getResult()) {
                                            String id = doc.getId(); // Get the document ID
                                            db.collection("ubicaciones").document(id)
                                                    .update("nombre", nombre, "descripcion", descripcion)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Toast.makeText(ModificarUbicacionActivity.this, "Ubicación actualizada", Toast.LENGTH_SHORT).show();
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(ModificarUbicacionActivity.this, "Error al actualizar ubicación", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                        }
                                    } else {
                                        Toast.makeText(ModificarUbicacionActivity.this, "Ubicación no encontrada", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });

        botonDropdown1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubicacionesSpinner.showDropDown();
            }
        });
    }
}
