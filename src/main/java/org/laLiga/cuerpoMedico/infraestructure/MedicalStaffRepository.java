package org.laLiga.cuerpoMedico.infraestructure;

import org.laLiga.cuerpoMedico.domain.entities.CuerpoMedico;

import java.util.List;
import java.util.Optional;

public interface MedicalStaffRepository {
    void save(CuerpoMedico cuerpoMedico);
    void update(CuerpoMedico cuerpoMedico);
    Optional<CuerpoMedico> findById(int id);
    void delete(int id);
    List<CuerpoMedico> findAll();
}
