package org.laLiga.controlador;

import org.laLiga.abstraccion.Repositorio;
import org.laLiga.plantel.Jugador;

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
        return null;
    }

    @Override
    public int ultimoId() {
        return this.listaJugadores.size() + 1;
    }
}
