package com.example.monitorapp.model;

public class Sensor {
    private String nombre;
    private String descripcion;
    private Float ideal;
    private Ubicacion ubicacion;
    private Tipo tipo;

    public Sensor() {}

    public Sensor(String nombre, String descripcion, Float ideal, Ubicacion ubicacion, Tipo tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ideal = ideal;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
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

    public Float getIdeal() {
        return ideal;
    }

    public void setIdeal(Float ideal) {
        this.ideal = ideal;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() { return this.nombre; }
}
