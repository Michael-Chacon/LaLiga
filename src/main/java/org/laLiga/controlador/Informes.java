package org.laLiga.controlador;

import org.laLiga.modelo.Equipo;

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

    public int promedio(List<Equipo> arrayEquipos){
        int totalEquipos = arrayEquipos.size();
        int totalGoles = this.totalGoles(arrayEquipos);
        int total = totalGoles/totalEquipos;
        return total;
    }
}
