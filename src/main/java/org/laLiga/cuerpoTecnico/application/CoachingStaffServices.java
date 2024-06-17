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


    public void createTeam(CuerpoTecnico cuerpoTecnico){
        this.coachingStaffRepository.save(cuerpoTecnico);
    }

    public void updateTeam(CuerpoTecnico cuerpoTecnico){
        this.coachingStaffRepository.update(cuerpoTecnico);
    }

    public Optional<CuerpoTecnico> getTeamById(int id){
        return this.coachingStaffRepository.findById(id);
    }

    public void deletePais(int id){
        this.coachingStaffRepository.delete(id);
    }

    public List<CuerpoTecnico> getAllTeams(){
        return this.coachingStaffRepository.findAll();
    }
}
