package com.example.monitorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorapp.adapters.SensoresAdapter;
import com.example.monitorapp.adapters.UbicacionesAdapter;
import com.example.monitorapp.datos.Repositorio;
import com.example.monitorapp.model.Ubicacion;
import com.example.monitorapp.model.Sensor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListarSensoresActivity extends AppCompatActivity {

    private RecyclerView sensoresRecyclerView;
    private SensoresAdapter sensoresAdapter;
    private List<Sensor> sensores;

    private Button buscarSensorButton;
    private EditText buscarSensorEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_sensores);

        sensoresRecyclerView= findViewById(R.id.listarSensoresRecyclerView);
        sensoresRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        sensores = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
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
                            }
                            sensoresAdapter= new SensoresAdapter(sensores);
                            sensoresRecyclerView.setAdapter(sensoresAdapter);
                        } else {
                            Toast.makeText(ListarSensoresActivity.this,"Error al obtener datos",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

        sensoresAdapter= new SensoresAdapter(sensores);
        sensoresRecyclerView.setAdapter(sensoresAdapter);

        buscarSensorButton = findViewById(R.id.buscarSensorButton);
        buscarSensorEditText = findViewById(R.id.buscarSensorEditText);

        buscarSensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busquedaSensor = buscarSensorEditText.getText().toString().trim().toUpperCase();

                if (busquedaSensor.isEmpty()) {
                    Toast.makeText(ListarSensoresActivity.this, "Por favor, ingresa un nombre.", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.collection("sensores")
                        .whereEqualTo("nombre", busquedaSensor)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful() && !task.getResult().isEmpty()) {
                                    for (QueryDocumentSnapshot doc : task.getResult()) {
                                        if (doc != null) {
                                            sensores = Repositorio.getInstance().sensoresFiltrados;
                                            sensores.clear();
                                            Sensor sensor = doc.toObject(Sensor.class);
                                            sensores.add(sensor);
                                        }
                                    }
                                    Intent intent = new Intent(ListarSensoresActivity.this, ResultadoBusquedaSensorActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(ListarSensoresActivity.this, "No existen ubicaciones que coincidan.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}