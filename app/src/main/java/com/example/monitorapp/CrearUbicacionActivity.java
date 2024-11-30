package com.example.monitorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ingresarUbicacionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreUbicacionEditText.getText().toString().strip().toUpperCase();
                String descripcion = descripcionUbicacionEditText.getText().toString().strip().toUpperCase();

                if (nombre.isEmpty()) {
                    Toast.makeText(CrearUbicacionActivity.this, "Por favor, ingrese un nombre para la ubicación.", Toast.LENGTH_LONG).show();
                    return;
                } else if (nombre.length() < 5 || nombre.length() > 15) {
                    Toast.makeText(CrearUbicacionActivity.this, "El nombre debe tener entre 5 y 15 caracteres.", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    db.collection("ubicaciones")
                            .whereEqualTo("nombre", nombre)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        QuerySnapshot querySnapshot = task.getResult();
                                        if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                            Toast.makeText(CrearUbicacionActivity.this, "El nombre de ubicación ya existe. Pruebe con otro nombre.", Toast.LENGTH_LONG).show();
                                        } else {
                                            Ubicacion nuevaUbicacion = new Ubicacion(nombre, descripcion);
                                            db.collection("ubicaciones").document().set(nuevaUbicacion)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(CrearUbicacionActivity.this, "Ingreso exitoso de la ubicación.", Toast.LENGTH_LONG).show();
                                                            finish();
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(CrearUbicacionActivity.this, "Error al ingresar la ubicación.", Toast.LENGTH_LONG).show();
                                                        }
                                                    });
                                        }
                                    } else {
                                        Toast.makeText(CrearUbicacionActivity.this, "Error al verificar la disponibilidad del nombre.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
