package com.example.monitorapp.model;

import java.util.Date;

public class Registro {
    private Date instante;
    private Float lectura;
    private Sensor sensor;

    public Registro() {}

    public Registro(Date instante, Float lectura, Sensor sensor) {
        this.instante = instante;
        this.lectura = lectura;
        this.sensor = sensor;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public Float getLectura() {
        return lectura;
    }

    public void setLectura(Float lectura) {
        this.lectura = lectura;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

}
