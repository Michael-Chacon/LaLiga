package org.laLiga.plantel;

import org.laLiga.abstraccion.Persona;
import org.laLiga.abstraccion.Plantel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Jugador extends Plantel<Jugador> {
    int dorsal;
    String posicionJuego;
    String nacionalidad;
    Date fechaIngreso;
    int golesAnotados;
    int tarjetasRojas;
    int tarjetasAmarillas;

    List<Jugador> arrayJugadores;

    public Jugador() {
        this.arrayJugadores = new ArrayList<>();
    }

    public Jugador(int id, String nombre, String apellidos, String rol, int edad, int idEquipo, int dorsal, String posicionJuego, String nacionalidad, Date fechaIngreso) {
        super(id, nombre, apellidos, rol, edad, idEquipo);
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

    @Override
    public void addObject(Jugador valor) {
        this.arrayJugadores.add(valor);
    }

    @Override
    public List<Jugador> listar() {
        return this.arrayJugadores;
    }

    @Override
    public <T> T buscarPorId(int id) {
        return null;
    }
}
