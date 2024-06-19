package org.laLiga.equipo.infraestructure;

import org.laLiga.equipo.domain.entities.Equipo;

import java.util.List;
import java.util.Optional;

public interface TeamRepository {

    int save(Equipo equipo);
    void update(Equipo equipo);
    Optional<Equipo> findById(int id);
    List<Equipo> findAll();
    void delete(int id);
}
