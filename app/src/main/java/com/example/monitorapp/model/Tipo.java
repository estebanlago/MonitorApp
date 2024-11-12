package com.example.monitorapp.model;

public class Tipo {
    private String nombre;

    public Tipo() {}

    public Tipo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() { return this.nombre; }
}
