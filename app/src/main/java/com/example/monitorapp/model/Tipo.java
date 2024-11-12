package com.example.monitorapp.model;

public class Tipo {
    private String nombre;
    private Sensor sensor;

    public Tipo() {}

    public Tipo(String nombre, Sensor sensor) {
        this.nombre = nombre;
        this.sensor = sensor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() { return this.nombre; }
}
