package org.laLiga.servicios;

import org.laLiga.Validacion.Validacion;
import org.laLiga.abstraccion.Crud;
import org.laLiga.abstraccion.Repositorio;
import org.laLiga.modelo.CuerpoTecnico;
import org.laLiga.modelo.Equipo;
import org.laLiga.modelo.Jugador;

import java.util.List;

public class Informes {
    public void masGoles(List<Equipo> listaEquipos){
        Equipo resultado = null;
        for (Equipo equipos : listaEquipos) {
            if (resultado == null || equipos.getGf() > resultado.getGf()) {
                resultado = equipos;
            }
        }
        System.out.println("El " + resultado.getNombre() + " tiene " + resultado.getGf() + " goles");
    }

    public void masPuntos(List<Equipo> listaEquipos){
        Equipo resultado = null;
        for (Equipo equipos : listaEquipos) {
            if (resultado == null || equipos.getTp() > resultado.getTp()) {
                resultado = equipos;
            }
        }
        System.out.println("El " + resultado.getNombre() + " tiene " + resultado.getTp() + " puntos");
    }

    public void masPartidosGanados(List<Equipo> listaEquipos){
        Equipo resultado = null;
        for (Equipo equipos : listaEquipos) {
            if (resultado == null || equipos.getPg() > resultado.getPg()) {
                resultado = equipos;
            }
        }
        System.out.println("El " + resultado.getNombre() + " tiene " + resultado.getPg() + " partidos");
    }

    public int totalGoles(List<Equipo> arrayEquipos){
        int total = 0;
        for (Equipo equipos : arrayEquipos) {
            total += equipos.getGf();
        }
        System.out.println("El torneo tuvo " + total + " goles");
        return total;
    }

    public void promedio(List<Equipo> arrayEquipos){
        int totalEquipos = arrayEquipos.size();
        int totalGoles = this.totalGoles(arrayEquipos);
        float total = totalGoles/totalEquipos;

        System.out.println("El torneo tuvo un promedio de " + total + " goles por equipo");

    }

    public void jugadorMasGoles(List<Jugador> jugadores, AdministrarEquipo objeto){
        Jugador resultado = null;
        for (Jugador jugador: jugadores){
            if (resultado == null || jugador.getGolesAnotados() > resultado.getGolesAnotados()){
                resultado = jugador;
            }
        }
        Equipo equipo = objeto.buscarPorId(resultado.getIdEquipo());
        System.out.println("El jugador " + resultado.getNombre().concat(" ").concat(resultado.getApellido()) + " del " + equipo.getNombre() +
                " hizo " + resultado.getGolesAnotados() + " goles.");
    }

    public void masTarjetasRojas(List<Jugador> jugadores, AdministrarEquipo objeto){
        Jugador resultado = null;
        for (Jugador jugador: jugadores){
            if (resultado == null || jugador.getTarjetasRojas() > resultado.getTarjetasRojas()){
                resultado = jugador;
            }
        }
        Equipo equipo = objeto.buscarPorId(resultado.getIdEquipo());
        System.out.println("El jugador " + resultado.getNombre().concat(" ").concat(resultado.getApellido()) + " del " + equipo.getNombre() +
                " tiene " + resultado.getTarjetasRojas() + " tarjetas rojas.");
    }

    public void masTarjetasAmarillas(List<Jugador> jugadores, AdministrarEquipo objeto){
        Jugador resultado = null;
        for (Jugador jugador: jugadores){
            if (resultado == null || jugador.getTarjetasAmarillas() > resultado.getTarjetasAmarillas()){
                resultado = jugador;
            }
        }
        Equipo equipo = objeto.buscarPorId(resultado.getIdEquipo());
        System.out.println("El jugador " + resultado.getNombre().concat(" ").concat(resultado.getApellido()) + " del " + equipo.getNombre() +
                " tiene " + resultado.getTarjetasAmarillas() + " tarjetas amarillas.");
    }

    public void plantillaJugadores(AdministrarEquipo repo, Repositorio<Jugador> jugador){
        System.out.println("Listado de equipos:");
        System.out.println("_________________________________");
        System.out.println(String.format("| %4s | %-20s |", "ID", "EQUIPO"));
        for (Equipo equipo: repo.listar()){
            System.out.println("_________________________________");
            System.out.println(String.format("| %4s | %-20s |", equipo.getId(), equipo.getNombre()));
        }
        System.out.println("_________________________________");

        int id = Validacion.validarInt("Elige el equipo por el id: ");
        Equipo equipo = repo.buscarPorId(id);
        System.out.println("Jugadores del " + equipo.getNombre());
        System.out.println("______________________________________________________________________");
        System.out.println(String.format("| %-22s | %-7s | %-30s |", "NOMBRE", "DORSAL","POSICIÓN"));
        for (Jugador j: jugador.listar()){
            if (j.getIdEquipo() == equipo.getId()){
                System.out.println("______________________________________________________________________");
                System.out.println(String.format("| %-22s | %-7s | %-30s |", j.getNombre().concat(" ").concat(j.getApellido()), j.getDorsal(), j.getPosicionJuego()));
            }
        }
        System.out.println("______________________________________________________________________");
    }

    public void plantillaCuerpoTecnico(AdministrarEquipo repo, Repositorio<CuerpoTecnico> cuerpoTecnico){
        System.out.println("Listado de equipos:");
        System.out.println("_________________________________");
        System.out.println(String.format("| %4s | %-20s |", "ID", "EQUIPO"));
        for (Equipo equipo: repo.listar()){
            System.out.println("_________________________________");
            System.out.println(String.format("| %4s | %-20s |", equipo.getId(), equipo.getNombre()));
        }
        System.out.println("_________________________________");
        int id = Validacion.validarInt("Elige el equipo por el id: ");
        Equipo equipo = repo.buscarPorId(id);
        System.out.println("\nCuerpo técnico del " + equipo.getNombre());
        System.out.println("_________________________________________________");
        System.out.printf(String.format("| %-20s | %-22s |\n", "NOMBRE", "ROL"));
        for (CuerpoTecnico cu: cuerpoTecnico.listar()){
            if (cu.getIdEquipo() == equipo.getId()){
                System.out.println("_________________________________________________");
                System.out.printf(String.format("| %-20s | %-22s |\n", cu.getNombre().concat(" ").concat(cu.getApellido()), cu.getRol()));
            }
        }
        System.out.println("_________________________________________________");
    }
}
