package org.laLiga.equipo.adapter.in;

import com.google.protobuf.OptionOrBuilder;
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

    public void match(){
        List<Equipo> obtenerEquipos = teamServices.getAllTeams();
        int totalEquipos = obtenerEquipos.size();

        if (totalEquipos <= 1){
            System.out.println("\n-------------------------------------------------------");
            System.out.println("** No hay suficientes equipos para iniciar el torneo **");
            System.out.println("-------------------------------------------------------\n");
        }else{
            System.out.println("--------------------------");
            System.out.println("** Registro de partidos **");
            System.out.println("--------------------------\n");
            System.out.println("----------------------");
            System.out.println("| ID \t| NOMBRE");
            System.out.println("----------------------");
            List<Equipo> equipos = teamServices.getAllTeams();
            equipos.sort((tp1, tp2) -> Integer.compare(tp1.getId(), tp2.getId()));
            equipos.forEach(equipo -> {
                System.out.println("| " + equipo.getId() + " \t| " + equipo.getNombre());
            });
            System.out.println("----------------------");
            System.out.println();

            Equipo equipoLocal = null;
            validarVacio : while (true){
                int local = console.readInt("Escriba el id del equipo que jugó de local: ");
                Optional<Equipo> getEquipo = teamServices.getTeamById(local);
                if(!getEquipo.isEmpty()){
                    equipoLocal = getEquipo.get();
                    break validarVacio;
                }else {
                    System.out.println("mal");
                }
            }
            System.out.println(equipoLocal.getNombre());

            int golesLocal = console.readInt("Cuantos goles hizo " + equipoLocal.getNombre() + ": ");

            if (golesLocal > 0){
                recordPlayerGoal(golesLocal, equipoLocal, console);
            }

            String opcion = console.readString("¿Hubo tarjeta para algún jugador del "+ equipoLocal.getNombre() +" (y/n)");
            if (opcion.equals("y")){
                recordPlayerCard(equipoLocal, console);
            }
//
            Equipo equipoVisitante = null;
            validarVaciov : while (true){
                int visitante = console.readInt("Escriba el id del equipo que jugó de Visitante: ");
                Optional<Equipo> getEquipo = teamServices.getTeamById(visitante);
                if(!getEquipo.isEmpty()){
                    equipoVisitante = getEquipo.get();
                    break validarVaciov;
                }else {
                    System.out.println("mal");
                }
            }
            int golesVisitante = console.readInt("Cuantos goles hizo " + equipoVisitante.getNombre()+ ": ");

            if (golesLocal > golesVisitante || golesLocal == golesVisitante) {
                registraCombate(equipoLocal, equipoVisitante, golesLocal, golesVisitante);
            }else{
                registraCombate(equipoVisitante, equipoLocal, golesVisitante, golesLocal);
            }

            if (golesVisitante > 0){
                recordPlayerGoal(golesVisitante, equipoVisitante, console);
            }

            String opcionVisitante = console.readString("¿Hubo tarjeta para algún jugador del "+ equipoVisitante.getNombre() +" (y/n): ");
            if (opcionVisitante.equals("y")){
                recordPlayerCard(equipoVisitante, console);
            }
        }
    }

    public void registraCombate(Equipo ganador, Equipo perdedor, int golesGanador, int golesPerdedor){
        if (golesGanador == golesPerdedor){
            ganador.setPj(ganador.getPj() + 1);
            ganador.setPe(ganador.getPe() + 1);
            ganador.setGf(ganador.getGf() + golesGanador);
            ganador.setGc(ganador.getGc() + golesPerdedor);
            ganador.setTp(ganador.getTp() + 1);

            perdedor.setPj(perdedor.getPj() + 1);
            perdedor.setPe(perdedor.getPe() + 1);
            perdedor.setGf(perdedor.getGf() + golesPerdedor);
            perdedor.setGc(perdedor.getGc() + golesGanador);
            perdedor.setTp(perdedor.getTp() + 1);

        }else {
            ganador.setPj(ganador.getPj() + 1);
            ganador.setPg(ganador.getPg() + 1);
            ganador.setGf(ganador.getGf() + golesGanador);
            ganador.setGc(ganador.getGc() + golesPerdedor);
            ganador.setTp(ganador.getTp() + 3);

            perdedor.setPj(perdedor.getPj() + 1);
            perdedor.setPp(perdedor.getPp() + 1);
            perdedor.setGf(perdedor.getGf() + golesPerdedor);
            perdedor.setGc(perdedor.getGc() + golesGanador);
        }
        teamServices.updateTeam(ganador);
        teamServices.updateTeam(perdedor);
    }


        public void recordPlayerCard(Equipo team, Console console){
        System.out.println("Listados de playeres del " + team.getNombre());
        for (Jugador j: teamServices.getAllPlayers()){
            if (j.getIdEquipo() == team.getId()){
                System.out.println(j.getId() + ". " + j.getNombre().concat(j.getApellido()));
            }
        }
        tarjetasLocal: while(true){
            Jugador goleador = null;
            regGoles: while (true){
                int idJugador = console.readInt("Seleccione el id del player que recibió la tarjeta: ");
                Optional<Jugador> goleadorr = teamServices.getPlayerById(idJugador);
                if(!goleadorr.isEmpty()){
                    goleador = goleadorr.get();
                    break regGoles;
                }else {
                    System.out.println("El jugador indicado no existe");
                }
            }
            int color = console.readInt("Qué tarjeta recibió el player " + goleador.getNombre().concat(goleador.getApellido()) + "\n\t1. Amarilla\n\t2. Roja\n");
            if (color == 1){
                goleador.setTarjetasAmarillas(goleador.getTarjetasAmarillas() + 1 );
                teamServices.updatePlayer(goleador);
            } else if (color == 2) {
                goleador.setTarjetasRojas(goleador.getTarjetasRojas() + 1);
                teamServices.updatePlayer(goleador);
            }

            String option = console.readString("Vas a registrar más tarjetas? (y/n): ");
            if (option.equalsIgnoreCase("n")){
                break tarjetasLocal;
            }
        }
    }
    public void recordPlayerGoal(int golesEquipo, Equipo team, Console console){
        System.out.println("--------------------------");
        System.out.println("** Registro de goles **");
        System.out.println("--------------------------\n");
        System.out.println("Listados de playeres del " + team.getNombre());

        for (Jugador j: teamServices.getAllPlayers()){
            if (j.getIdEquipo() == team.getId()){
                System.out.println(j.getId() + ". " + j.getNombre().concat(j.getApellido()));
            }
        }
        int totalGoles = golesEquipo;
        registrarGoles: while (true){
            Jugador goleador = null;
            regGoles: while (true){
            int idJugador = console.readInt("Seleccione el id del player que hizo el gol: ");
            Optional<Jugador> goleadorr = teamServices.getPlayerById(idJugador);
                if(!goleadorr.isEmpty()){
                    goleador = goleadorr.get();
                    break regGoles;
                }else {
                    System.out.println("El jugador indicado no existe");
                }
            }
            int goles = console.readInt("Cuantos goles hizo " + goleador.getNombre() + ": ");

            if (goles > totalGoles){
                System.out.println("Mal, La cantidad de goles que hizo un player no puede ser superior a los goles anotados en el partido");
                continue registrarGoles;
            } else if (goles <= totalGoles) {
                totalGoles = totalGoles - goles;
            }
            System.out.println(goles);
            goleador.setGolesAnotados(goleador.getGolesAnotados() + goles);
            teamServices.updatePlayer(goleador);
            if (totalGoles == 0){
                break registrarGoles;
            }
            System.out.println("Quedan " + totalGoles + " goles");
        }
    }
}
