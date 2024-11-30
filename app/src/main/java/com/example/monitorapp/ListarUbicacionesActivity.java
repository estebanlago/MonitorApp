package com.example.monitorapp;

import android.os.Bundle;
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
    }
}