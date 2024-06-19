package org.laLiga.equipo.adapter.in;

import org.laLiga.console.Console;
import org.laLiga.equipo.application.TeamServices;
import org.laLiga.equipo.domain.entities.Equipo;
import org.laLiga.equipo.infraestructure.TeamRepository;
import org.laLiga.jugador.application.PlayerService;
import org.laLiga.jugador.domain.entities.Jugador;
import org.laLiga.jugador.infraestructure.PlayerRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TeamConsoleAdapter {
    private final TeamServices teamServices;
    Console console = new Console();
    public TeamConsoleAdapter(TeamServices teamServices) {
        this.teamServices = teamServices;
    }

    public void menuEquipo(){
        Scanner sc = new Scanner(System.in);

        menuEquipo: while(true){
            System.out.println("Opciones de los equipos: ");
            System.out.println("\t1. Registrar un equipo");
            System.out.println("\t6. Salir");
            int choice = console.readInt("Selección una opción: ");

            switch (choice){
                case 1:
                    registrarEquipo();
                break;
                case 6:
                    break menuEquipo;
            }
        }
    }

    public void registrarEquipo(){
        System.out.println("--------------------------");
        System.out.println("** Registro de equipos **");
        System.out.println("--------------------------\n");
        registro: while(true){
            String nombre = console.readString("Ingrese el nombre del equipo: ");
            int aaa  = teamServices.createTeam(new Equipo(nombre, 0,0,0,0,0,0,0));
            String option = console.readString("quieres registrar otro equipo? (y/n): ");
            if (option.equalsIgnoreCase("n")){
                break registro;
            }
        }
    }

//    public void match(){
//        List<Equipo> obtenerEquipos = teamServices.getAllTeams();
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
//            List<Equipo> equipos = teamServices.getAllTeams();
//            equipos.sort((tp1, tp2) -> Integer.compare(tp1.getId(), tp2.getId()));
//            equipos.forEach(equipo -> {
//                System.out.println("| " + equipo.getId() + " \t| " + equipo.getNombre());
//            });
//            System.out.println("----------------------");
//            System.out.println();
//
//            int local = console.readInt("Escriba el id del equipo que jugó de local: ");
//            Optional<Equipo> equipoLocal = teamServices.getTeamById(local);
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
