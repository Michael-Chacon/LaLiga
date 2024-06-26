package org.laLiga.console;

import org.laLiga.abstraccion.Repositorio;
import org.laLiga.cuerpoMedico.adapter.in.MedicalStaffConsoleAdapter;
import org.laLiga.cuerpoMedico.adapter.out.MedicalStaffMySQLRepository;
import org.laLiga.cuerpoMedico.application.MedicalStaffService;
import org.laLiga.cuerpoTecnico.adapter.in.CoachingStaffConsoleAdapter;
import org.laLiga.cuerpoTecnico.adapter.out.CoachingStaffMySQLRepository;
import org.laLiga.cuerpoTecnico.application.CoachingStaffServices;
import org.laLiga.equipo.adapter.in.TeamConsoleAdapter;
import org.laLiga.equipo.adapter.in.TeamMatchConsoleAdapter;
import org.laLiga.equipo.adapter.out.TeamMySQLRepository;
import org.laLiga.equipo.application.TeamServices;
import org.laLiga.cuerpoMedico.domain.entities.CuerpoMedico;
import org.laLiga.equipo.domain.entities.Equipo;
import org.laLiga.cuerpoTecnico.domain.entities.CuerpoTecnico;
import org.laLiga.jugador.adapter.in.PlayerConsoleAdapter;
import org.laLiga.jugador.adapter.out.PlayerMySQLRepository;
import org.laLiga.jugador.application.PlayerService;
import org.laLiga.jugador.domain.entities.Jugador;
import org.laLiga.servicios.*;

import java.util.List;
import java.util.Optional;

public class Menus {

    AdministrarEquipo team = new AdministrarEquipo();
    Repositorio<Jugador> jugador = new JugadorRepositorio();
    Repositorio<CuerpoMedico> cuerpoMedico = new CuMedicoRepositorio();
    Repositorio<CuerpoTecnico> cuerpoTecnico = new CuTecnicoRepositorio();
    Console console = new Console();
    PlayerConsole player = new PlayerConsole(jugador);
//    CoachingStaffConsole coachingStaff = new CoachingStaffConsole(cuerpoTecnico);
//    MedicalStaffConsole medicalStaff = new MedicalStaffConsole(cuerpoMedico);


//    TeamMatchConsoleAdapter teamMatch = new TeamMatchConsoleAdapter(teamServices);
//   Medical staff module
    MedicalStaffMySQLRepository medicalStaffMySQLRepository = new MedicalStaffMySQLRepository("jdbc:mysql://localhost:3306/laLiga", "root", "root");
    MedicalStaffService medicalStaffService = new MedicalStaffService(medicalStaffMySQLRepository);
    MedicalStaffConsoleAdapter medicalStaffConsoleAdapter = new MedicalStaffConsoleAdapter(medicalStaffService);
//   Coaching staff module
    CoachingStaffMySQLRepository coachingStaffMySQLRepository = new CoachingStaffMySQLRepository("jdbc:mysql://localhost:3306/laLiga", "root", "root");
    CoachingStaffServices coachingStaffServices = new CoachingStaffServices(coachingStaffMySQLRepository);
    CoachingStaffConsoleAdapter coachingStaffConsoleAdapter = new CoachingStaffConsoleAdapter(coachingStaffServices);
//  Player module
    PlayerMySQLRepository playerMySQLRepository = new PlayerMySQLRepository("jdbc:mysql://localhost:3306/laLiga", "root", "root");
    PlayerService playerService = new PlayerService(playerMySQLRepository);
    PlayerConsoleAdapter playerConsoleAdapter =  new PlayerConsoleAdapter(playerService);
    //    New objects
    TeamMySQLRepository teamMySQLRepository = new TeamMySQLRepository("jdbc:mysql://localhost:3306/laLiga", "root", "root");
    TeamServices teamServices = new TeamServices(teamMySQLRepository, playerMySQLRepository);
    TeamConsoleAdapter teamConsoleAdapter = new TeamConsoleAdapter(teamServices);

    public void showTeamMenu(){
        teamConsoleAdapter.menuEquipo();
    }

    public void registrarPlantel(){
        System.out.println("----------------------------------");
        System.out.println("** Registrar miembros del plantel **");
        System.out.println("----------------------------------\n");
        System.out.println("Seleccione por el id al equipo para registra empleados en él");

        List<Equipo> equipos = teamServices.getAllTeams();
        System.out.println("_______________________________");
        System.out.println(String.format("| %-5s | %-20s |", "ID", "NOMBRE"));
        equipos.forEach(equipo -> {
            System.out.println("_______________________________");
            System.out.println(String.format("| %-5s | %-20s |", equipo.getId(), equipo.getNombre()));
        });
        System.out.println("_______________________________");

        int idEQuipo = console.readInt("Seleccione el id: ");
        Optional<Equipo> equipoSeleccionado = teamServices.getTeamById(idEQuipo);
//        final int[] idd = new int[1];
//        equipoSeleccionado.ifPresentOrElse(
//                e -> {
//                    System.out.println("Si existe");
//                    idd[0] = e.getId();
//                },
//                () -> System.out.println("No existe")
//        );

        int rol = console.readInt("Que rol quiere registrar:\n\t1. Jugador\n\t2. Cuerpo técnico\n\t3. Cuerpo medico\n");

        if (rol == 1){
            playerConsoleAdapter.registrarJugador(equipoSeleccionado, console);
        } else if (rol == 2){
            coachingStaffConsoleAdapter.registrarCuTecnico(equipoSeleccionado, console);
        } else if (rol == 3) {
            medicalStaffConsoleAdapter.registrarCuMedico(equipoSeleccionado, console);
        }
    }
    
    

    public void fecha(){
        teamConsoleAdapter.match();
//        teamMatch.match(teamConsoleAdapter, playerConsoleAdapter, console);
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
    }

    public void informes(){
        List<Equipo> obtenerEquipos = team.listar();
        int totalEquipos = obtenerEquipos.size();
        if (totalEquipos < 1){
            System.out.println("\n-------------------------------------------------------");
            System.out.println("** No hay suficientes equipos para iniciar el torneo **");
            System.out.println("-------------------------------------------------------\n");
        }else{
            Informes informe = new Informes();
            System.out.println("Modulo de informes");

            informes: while (true){
                int informeSeleccionado = console.readInt("Seleccione el informe que quiere ver:\n\t1. Equipo más goleador\n\t2. Equipo con más Puntos" +
                        "\n\t3. Equipo con más partidos ganados\n\t4. Total de goles anotados por todos\n\t5. Promedio de goles anotados" +
                        "\n\t6. Jugador con más goles\n\t7. Jugador con más tarjetas amarillas\n\t8. Jugador con más tarjetas rojas" +
                        "\n\t9. Jugadores por equipo\n\t10. Cuerpo técnico por equipo\n");

                if (informeSeleccionado == 1){
                    informe.masGoles(team.listar());
                } else if (informeSeleccionado == 2) {
                    informe.masPuntos(team.listar());
                } else if (informeSeleccionado == 3) {
                    informe.masPartidosGanados(team.listar());
                } else if (informeSeleccionado == 4) {
                    informe.totalGoles(team.listar());
                } else if (informeSeleccionado == 5) {
                    informe.promedio(team.listar());
                } else if (informeSeleccionado == 6) {
                    informe.jugadorMasGoles(jugador.listar(), team);
                } else if (informeSeleccionado == 7) {
                    informe.masTarjetasAmarillas(jugador.listar(), team);
                } else if (informeSeleccionado == 8) {
                    informe.masTarjetasRojas(jugador.listar(), team);
                }else if(informeSeleccionado == 9){
                    informe.plantillaJugadores(team, jugador);
                }else if(informeSeleccionado == 10){
                    informe.plantillaCuerpoTecnico(team, cuerpoTecnico);
                }

                String option = console.readString("\nquieres seguir viendo más informes? (y/n): ");
                if (option.equalsIgnoreCase("n")){
                    break informes;
                }
            }
        }

    }

    public void mostrarEquipos(){
        List<Equipo> obtenerEquipos = team.listar();
        int totalEquipos = obtenerEquipos.size();

        if(totalEquipos == 0){
            System.out.println("\n----------------------------------");
            System.out.println("** No hay equipos registrados **");
            System.out.println("----------------------------------\n");
        }else {
            System.out.println("Listado de equipos");
            List<Equipo> equipos = team.listar();

            tablaPosiciones(equipos);
        }
    }

    public void tablaPosiciones(List<Equipo> equipos){
        int longitud = equipos.size();
        for (int i = 0; i < longitud; i++){
            for (int j = 0; j < longitud - i - 1; j++){
                if (equipos.get(j).getTp() < equipos.get(j + 1 ).getTp()){
                    Equipo temporal = equipos.get(j);
                    equipos.set(j, equipos.get(j + 1));
                    equipos.set(j + 1, temporal);
                }
            }
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("| ID \t| PJ \t| PG \t| PP \t| PE \t| GF \t| GC \t| TP \t|  NOMBRE \t\t|");
        System.out.println("--------------------------------------------------------------------------------");
        equipos.forEach(equipo -> {
            System.out.println("| " +equipo.getId() +" \t| "+  equipo.getPj() +" \t| "+ equipo.getPg() +" \t| "+ equipo.getPp() +" \t| "+ equipo.getPe() +" \t| "+ equipo.getGf() +" \t| "+ equipo.getGc() +" \t| "+ equipo.getTp() +" \t| "+equipo.getNombre());
        });
        System.out.println("--------------------------------------------------------------------------------");
    }
}
