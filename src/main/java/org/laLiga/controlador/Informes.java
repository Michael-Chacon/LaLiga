package org.laLiga.controlador;

import org.laLiga.modelo.Equipo;
import org.laLiga.modelo.Jugador;

import java.util.List;

public class Informes {
    public Equipo masGoles(List<Equipo> listaEquipos){
        Equipo resultado = null;
        for (Equipo equipos : listaEquipos) {
            if (resultado == null || equipos.getGf() > resultado.getGf()) {
                resultado = equipos;
            }
        }
        return resultado;
    }

    public Equipo masPuntos(List<Equipo> listaEquipos){
        Equipo resultado = null;
        for (Equipo equipos : listaEquipos) {
            if (resultado == null || equipos.getTp() > resultado.getTp()) {
                resultado = equipos;
            }
        }
        return resultado;
    }

    public Equipo masPartidosGanados(List<Equipo> listaEquipos){
        Equipo resultado = null;
        for (Equipo equipos : listaEquipos) {
            if (resultado == null || equipos.getPg() > resultado.getPg()) {
                resultado = equipos;
            }
        }
        return resultado;
    }

    public int totalGoles(List<Equipo> arrayEquipos){
        int total = 0;
        for (Equipo equipos : arrayEquipos) {
            total += equipos.getGf();
        }
        return total;
    }

    public float promedio(List<Equipo> arrayEquipos){
        int totalEquipos = arrayEquipos.size();
        int totalGoles = this.totalGoles(arrayEquipos);
        float total = totalGoles/totalEquipos;
        return total;
    }

    public Jugador jugadorMasGoles(List<Jugador> jugadores){
        Jugador resultado = null;
        for (Jugador jugador: jugadores){
            if (resultado == null || jugador.getGolesAnotados() > resultado.getGolesAnotados()){
                resultado = jugador;
            }
        }
        return resultado;
    }

    public Jugador masTarjetasRojas(List<Jugador> jugadores){
        Jugador resultado = null;
        for (Jugador jugador: jugadores){
            if (resultado == null || jugador.getTarjetasRojas() > resultado.getTarjetasRojas()){
                resultado = jugador;
            }
        }
        return resultado;
    }

    public Jugador masTarjetasAmarillas(List<Jugador> jugadores){
        Jugador resultado = null;
        for (Jugador jugador: jugadores){
            if (resultado == null || jugador.getTarjetasAmarillas() > resultado.getTarjetasAmarillas()){
                resultado = jugador;
            }
        }
        return resultado;
    }
}
