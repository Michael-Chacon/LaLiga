package org.laLiga.cuerpoTecnico.infraestructure;

import org.laLiga.cuerpoTecnico.domain.entities.CuerpoTecnico;

import java.util.List;
import java.util.Optional;

public interface CoachingStaffRepository {
    void save(CuerpoTecnico equipo);
    void update(CuerpoTecnico equipo);
    Optional<CuerpoTecnico> findById(int id);
    List<CuerpoTecnico> findAll();
    void delete(int id);
}
