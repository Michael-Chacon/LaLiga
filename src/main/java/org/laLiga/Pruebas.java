package org.laLiga;

import org.laLiga.team.jugadores.Jugador;

public class Pruebas {
    public static void main(String[] args) {
        Jugador ronaldo = new Jugador(1, "Cristiano", "Ronaldo", 40);
        System.out.println(ronaldo.getNombre() + " " + ronaldo.getApellido());

    }
}
