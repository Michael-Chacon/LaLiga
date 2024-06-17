package org.laLiga.equipo.application;

import org.laLiga.equipo.domain.entities.Equipo;
import org.laLiga.equipo.infraestructure.TeamRepository;

import java.util.List;
import java.util.Optional;

public class TeamServices {
    private final TeamRepository teamRepository;

    public TeamServices(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void createTeam(Equipo equipo){
        this.teamRepository.save(equipo);
    }

    public void updateTeam(Equipo equipo){
        this.teamRepository.update(equipo);
    }

    public Optional<Equipo> getTeamById(int id){
        return this.teamRepository.findById(id);
    }

    public void deletePais(int id){
        this.teamRepository.delete(id);
    }

    public List<Equipo> getAllTeams(){
        return this.teamRepository.findAll();
    }
}
