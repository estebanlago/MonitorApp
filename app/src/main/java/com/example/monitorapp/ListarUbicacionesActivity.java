package com.example.monitorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorapp.adapters.UbicacionesAdapter;
import com.example.monitorapp.datos.Repositorio;
import com.example.monitorapp.model.Ubicacion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListarUbicacionesActivity extends AppCompatActivity {

    private RecyclerView ubicacionesRecyclerView;
    private UbicacionesAdapter ubicacionesAdapter;
    private List<Ubicacion> ubicaciones;

    private Button buscarUbicacionButton;
    private EditText buscarUbicacionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ubicaciones);

        ubicacionesRecyclerView = findViewById(R.id.listarUbicacionesRecyclerView);
        ubicacionesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ubicaciones = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
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
                            }
                            ubicacionesAdapter = new UbicacionesAdapter(ubicaciones);
                            ubicacionesRecyclerView.setAdapter(ubicacionesAdapter);
                        } else {
                            Toast.makeText(ListarUbicacionesActivity.this,"Error al obtener datos",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

        ubicacionesAdapter = new UbicacionesAdapter(ubicaciones);
        ubicacionesRecyclerView.setAdapter(ubicacionesAdapter);

        buscarUbicacionButton = findViewById(R.id.buscarUbicacionButton);
        buscarUbicacionEditText = findViewById(R.id.buscarUbicacionEditText);

        buscarUbicacionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busquedaUbicacion = buscarUbicacionEditText.getText().toString().trim();

                if (busquedaUbicacion.isEmpty()) {
                    Toast.makeText(ListarUbicacionesActivity.this, "Por favor, ingresa un nombre.", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.collection("ubicaciones")
                        .whereEqualTo("nombre", busquedaUbicacion)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful() && !task.getResult().isEmpty()) {
                                    Toast.makeText(ListarUbicacionesActivity.this, "Location found!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ListarUbicacionesActivity.this, "No existen ubicaciones con el nombre ingresado.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}