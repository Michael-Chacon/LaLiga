package org.laLiga.jugador.infraestructure;

import org.laLiga.jugador.domain.entities.Jugador;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    void save(Jugador jugador);
    void update(Jugador jugador);
    Optional<Jugador> findById(int id);
    void delete(int id);
    List<Jugador> findAll();
}
