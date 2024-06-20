package org.laLiga.equipo.application;

import org.laLiga.equipo.domain.entities.Equipo;
import org.laLiga.equipo.infraestructure.TeamRepository;
import org.laLiga.jugador.application.PlayerService;
import org.laLiga.jugador.domain.entities.Jugador;
import org.laLiga.jugador.infraestructure.PlayerRepository;

import java.util.List;
import java.util.Optional;

public class TeamServices {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;


    public TeamServices(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public int createTeam(Equipo equipo){
        int resultado = this.teamRepository.save(equipo);
        return resultado;
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
    public List<Jugador> getAllPlayers(){
        return playerRepository.findAll();
    }
    public Optional<Jugador> getPlayerById(int id){
        return playerRepository.findById(id);
    }

    public void updatePlayer(Jugador jugador){
        playerRepository.update(jugador);
    }
}
