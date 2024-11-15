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

    protected Repositorio() {
        registros = new ArrayList<>();
        sensores = new ArrayList<>();
        tipos = new ArrayList<>();
        ubicaciones = new ArrayList<>();

        tipos.add(new Tipo("TEMPERATURA"));
        tipos.add(new Tipo("HUMEDAD"));

        ubicaciones.add(new Ubicacion("INVERNADERO N-1", "2 METROS CUADRADOS"));
        ubicaciones.add(new Ubicacion("INVERNADERO N-2", "8 METROS CUADRADOS"));
        ubicaciones.add(new Ubicacion("INVERNADERO N-3", "16 METROS CUADRADOS"));

        sensores.add(new Sensor("SENSOR #1", "MONITOREA LA TEMPERATURA", 4.0f, ubicaciones.get(0), tipos.get(0)));
        sensores.add(new Sensor("SENSOR #2", "MONITOREA LA HUMEDAD", 1.0f, ubicaciones.get(1), tipos.get(1)));
    }

    public static synchronized Repositorio getInstance() {
        if (null == instance) {
            instance = new Repositorio();
        }
        return  instance;
    }
}