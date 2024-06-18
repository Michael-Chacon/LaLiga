package org.laLiga.cuerpoTecnico.application;

import org.laLiga.cuerpoTecnico.infraestructure.CoachingStaffRepository;
import org.laLiga.cuerpoTecnico.domain.entities.CuerpoTecnico;

import java.util.List;
import java.util.Optional;

public class CoachingStaffServices {

    private CoachingStaffRepository coachingStaffRepository;

    public CoachingStaffServices(CoachingStaffRepository coachingStaffRepository) {
        this.coachingStaffRepository = coachingStaffRepository;
    }


    public void createCoachingStaff(CuerpoTecnico cuerpoTecnico){
        this.coachingStaffRepository.save(cuerpoTecnico);
    }

    public void updateCoachingStaff(CuerpoTecnico cuerpoTecnico){
        this.coachingStaffRepository.update(cuerpoTecnico);
    }

    public Optional<CuerpoTecnico> getCoachingStaffById(int id){
        return this.coachingStaffRepository.findById(id);
    }

    public void deleteCoachingStaff(int id){
        this.coachingStaffRepository.delete(id);
    }

    public List<CuerpoTecnico> getAllCoachingStaff(){
        return this.coachingStaffRepository.findAll();
    }
}
