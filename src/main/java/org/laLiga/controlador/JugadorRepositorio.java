package org.laLiga.controlador;

import org.laLiga.abstraccion.Repositorio;
import org.laLiga.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;

public class JugadorRepositorio extends Repositorio<Jugador> {

    List<Jugador> listaJugadores = new ArrayList<>();

    public JugadorRepositorio() {
    }

    @Override
    public void addObject(Jugador valor) {
        this.listaJugadores.add(valor);
    }

    @Override
    public List<Jugador> listar() {
        return this.listaJugadores;
    }

    @Override
    public Jugador buscarPorId(int id) {
        Jugador resultado = null;
        for (Jugador jugador: listaJugadores){
            if (jugador.getId() == id){
                resultado = jugador;
            }
        }
        return resultado;
    }

    @Override
    public int ultimoId() {
        return this.listaJugadores.size() + 1;
    }
}
