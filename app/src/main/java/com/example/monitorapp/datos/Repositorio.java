package com.example.monitorapp.datos;

import com.example.monitorapp.model.Registro;
import com.example.monitorapp.model.Sensor;
import com.example.monitorapp.model.Tipo;
import com.example.monitorapp.model.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class Repositorio {

    private static Repositorio instance = null;
    public List<Registro> registros;
    public List<Sensor> sensores;
    public List<Tipo> tipos;
    public List<Ubicacion> ubicaciones;

    public List<Ubicacion> ubicacionesFiltradas;

    protected Repositorio() {
        registros = new ArrayList<>();
        sensores = new ArrayList<>();
        tipos = new ArrayList<>();
        ubicaciones = new ArrayList<>();

        ubicacionesFiltradas = new ArrayList<>();

        tipos.add(new Tipo("TEMPERATURA"));
        tipos.add(new Tipo("HUMEDAD"));
    }

    public static synchronized Repositorio getInstance() {
        if (null == instance) {
            instance = new Repositorio();
        }
        return  instance;
    }
}