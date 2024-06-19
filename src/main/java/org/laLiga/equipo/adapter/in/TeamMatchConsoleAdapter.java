package org.laLiga.equipo.adapter.in;

import org.laLiga.console.Console;
import org.laLiga.equipo.application.TeamServices;
import org.laLiga.equipo.domain.entities.Equipo;
import org.laLiga.equipo.infraestructure.TeamRepository;
import org.laLiga.jugador.infraestructure.PlayerRepository;

import java.util.List;

public class TeamMatchConsoleAdapter {
    private final TeamServices teamServices;

    public TeamMatchConsoleAdapter(TeamServices teamServices) {
        this.teamServices = teamServices;
    }


//    public void match(TeamRepository team, PlayerRepository player, Console console){
//        List<Equipo> obtenerEquipos = team.listar();
//        int totalEquipos = obtenerEquipos.size();
//
//        if (totalEquipos <= 1){
//            System.out.println("\n-------------------------------------------------------");
//            System.out.println("** No hay suficientes equipos para iniciar el torneo **");
//            System.out.println("-------------------------------------------------------\n");
//        }else{
//            System.out.println("--------------------------");
//            System.out.println("** Registro de partidos **");
//            System.out.println("--------------------------\n");
//            System.out.println("----------------------");
//            System.out.println("| ID \t| NOMBRE");
//            System.out.println("----------------------");
//            List<Equipo> equipos = team.listar();
//            equipos.sort((tp1, tp2) -> Integer.compare(tp1.getId(), tp2.getId()));
//            equipos.forEach(equipo -> {
//                System.out.println("| " + equipo.getId() + " \t| " + equipo.getNombre());
//            });
//            System.out.println("----------------------");
//            System.out.println();
//
//            int local = console.readInt("Escriba el id del equipo que jugó de local: ");
//            Equipo equipoLocal = team.buscarPorId(local);
//            int golesLocal = console.readInt("Cuantos goles hizo " + equipoLocal.getNombre() + ": ");
//
//            if (golesLocal > 0){
//                player.recordPlayerGoal(golesLocal, equipoLocal, console);
//            }
//
//            String opcion = console.readString("¿Hubo tarjeta para algún jugador del "+ equipoLocal.getNombre() +" (y/n)");
//            if (opcion.equals("y")){
//                player.recordPlayerCard(equipoLocal, console);
//            }
//
//            int visitante = console.readInt("Escriba el id del equipo que jugó de Visitante: ");
//            Equipo equipoVisitante = team.buscarPorId(visitante);
//            int golesVisitante = console.readInt("Cuantos goles hizo " + equipoVisitante.getNombre()+ ": ");
//
//            if (golesLocal > golesVisitante || golesLocal == golesVisitante) {
//                team.registraCombate(equipoLocal, equipoVisitante, golesLocal, golesVisitante);
//            }else{
//                team.registraCombate(equipoVisitante, equipoLocal, golesVisitante, golesLocal);
//            }
//
//            if (golesVisitante > 0){
//                player.recordPlayerGoal(golesVisitante, equipoVisitante, console);
//            }
//
//            String opcionVisitante = console.readString("¿Hubo tarjeta para algún jugador del "+ equipoVisitante.getNombre() +" (y/n): ");
//            if (opcionVisitante.equals("y")){
//                player.recordPlayerCard(equipoVisitante, console);
//            }
//        }
//    }
}
