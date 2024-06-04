package org.laLiga.team.jugadores;

import org.laLiga.abstraccion.Persona;

import java.util.Date;

public class Jugador extends Persona {
    int dorsal;
    String posicionJuego;
    String nacionalidad;
    Date fechaIngreso;
    int golesAnotados;
    int tarjetasRojas;
    int tarjetasAmarillas;

    public Jugador() {
    }

    public Jugador(int id, String nombre, String apellido, int edad, int dorsal, String posicionJuego, String nacionalidad, Date fechaIngreso) {
        super(id, nombre, apellido, edad);
        this.dorsal = dorsal;
        this.posicionJuego = posicionJuego;
        this.nacionalidad = nacionalidad;
        this.fechaIngreso = fechaIngreso;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getPosicionJuego() {
        return posicionJuego;
    }

    public void setPosicionJuego(String posicionJuego) {
        this.posicionJuego = posicionJuego;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getGolesAnotados() {
        return golesAnotados;
    }

    public void setGolesAnotados(int golesAnotados) {
        this.golesAnotados = golesAnotados;
    }

    public int getTarjetasRojas() {
        return tarjetasRojas;
    }

    public void setTarjetasRojas(int tarjetasRojas) {
        this.tarjetasRojas = tarjetasRojas;
    }

    public int getTarjetasAmarillas() {
        return tarjetasAmarillas;
    }

    public void setTarjetasAmarillas(int tarjetasAmarillas) {
        this.tarjetasAmarillas = tarjetasAmarillas;
    }
}
