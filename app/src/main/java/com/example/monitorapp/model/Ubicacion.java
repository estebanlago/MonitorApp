package com.example.monitorapp.model;

public class Ubicacion {
    private String nombre;
    private String descripcion;
    private Sensor sensor;

    public Ubicacion() {}

    public Ubicacion(String nombre, String descripcion, Sensor sensor) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sensor = sensor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
