package com.example.monitorapp.model;

public class Sensor {
    private String nombre;
    private String descripcion;
    private Float ideal;
    private Ubicacion ubicacion;
    private Tipo tipo;
    private Registro registro;

    public Sensor() {}

    public Sensor(String nombre, String descripcion, Float ideal, Ubicacion ubicacion, Tipo tipo, Registro registro) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ideal = ideal;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.registro = registro;
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

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    @Override
    public String toString() { return this.nombre; }
}
