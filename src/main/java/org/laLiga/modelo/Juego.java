package org.laLiga.modelo;

import java.util.Date;

public class Juego {
    int idJuego;
    int idLocal;
    int idVisitante;
    int golesLocal;
    int golesVisitante;
    Date fechaJugo;

    public Juego() {
    }

    public Juego(int idJuego, int idLocal, int idVisitante, int golesLocal, int golesVisitante, Date fechaJugo) {
        this.idJuego = idJuego;
        this.idLocal = idLocal;
        this.idVisitante = idVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.fechaJugo = fechaJugo;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdVisitante() {
        return idVisitante;
    }

    public void setIdVisitante(int idVisitante) {
        this.idVisitante = idVisitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public Date getFechaJugo() {
        return fechaJugo;
    }

    public void setFechaJugo(Date fechaJugo) {
        this.fechaJugo = fechaJugo;
    }
}
