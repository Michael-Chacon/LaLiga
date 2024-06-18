package org.laLiga.jugador.application;

import org.laLiga.jugador.domain.entities.Jugador;
import org.laLiga.jugador.infraestructure.PlayerRepository;

import java.util.List;
import java.util.Optional;

public class PlayerService {
    private  final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void createPlayer(Jugador jugador){
        this.playerRepository.save(jugador);
    }

    public void updatePlayer(Jugador jugador){
        this.playerRepository.update(jugador);
    }

    public Optional<Jugador> getPlayerById(int id){
        return this.playerRepository.findById(id);
    }

    public void deletePlayer(int id){
        this.playerRepository.delete(id);
    }

    public List<Jugador> getAllPlayers(){
        return this.playerRepository.findAll();
    }
}
