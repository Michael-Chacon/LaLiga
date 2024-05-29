package org.laLiga;
import org.laLiga.controlador.AdministrarEquipo;
import org.laLiga.modelo.Equipo;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
//        La clase AdministrarEquipo contiene los crud requeridos.
        AdministrarEquipo repo = new AdministrarEquipo();

        int control = 0;

        principal: while(true){
//          Las obtenerEquipos y totalEquipos son variables usadas para validar que existan equipos registrados.
            List<Equipo> obtenerEquipos = repo.listar();
            int totalEquipos = obtenerEquipos.size();

            System.out.println("----------");
            System.out.println("| LA LIGA |");
            System.out.println("----------");

            System.out.println("Options: \n\t1. Registrar equipo\n\t2. Registrar juego\n\t3. Informes\n\t4. Tabla\n\t0. Salir");
            control = sc.nextInt();

            if (control == 1){
                System.out.println("--------------------------");
                System.out.println("** Registro de equipos **");
                System.out.println("--------------------------\n");
                registro: while(true){
                    int id = repo.ultimoid();
                    System.out.println("Ingrese el nombre del equipo");
                    String nombre = sc.next();
                    repo.crear(new Equipo(id, nombre, 0,0,0,0,0,0,0));
                    System.out.println("quieres registrar otro equipo?\n\ts para salir\n\tp para registra otro equipo");
                    String option = sc.next();
                    if (option.equalsIgnoreCase("s")){
                        break registro;
                    }
                }
            }else if (control == 2){
                if (totalEquipos <= 1){
                    System.out.println("\n-------------------------------------------------------");
                    System.out.println("** No hay suficientes equipos para iniciar el torneo **");
                    System.out.println("-------------------------------------------------------\n");

                }else {
                    System.out.println("--------------------------");
                    System.out.println("** Registro de partidos **");
                    System.out.println("--------------------------\n");
                    System.out.println("----------------------");
                    System.out.println("| ID \t| NOMBRE");
                    System.out.println("----------------------");
                    List<Equipo> equipos = repo.listar();
                    equipos.forEach(equipo -> {
                        System.out.println("| " + equipo.getId() + " \t| " + equipo.getNombre());
                    });
                    System.out.println("----------------------");
                    System.out.println();

                    System.out.println("Escriba el id del equipo que jugó de local");
                    int local = sc.nextInt();
                    Equipo equipoLocal = repo.buscarPorId(local);
                    System.out.println("Cuantos goles hizo " + equipoLocal.getNombre()+ ": ");
                    int golesLocal = sc.nextInt();

                    System.out.println("Escriba el id del equipo que jugó de Visitante");
                    int visitante = sc.nextInt();
                    Equipo equipoVisitante = repo.buscarPorId(visitante);
                    System.out.println("Cuantos goles hizo " + equipoVisitante.getNombre()+ ": ");
                    int golesVisitante = sc.nextInt();

                    if (golesLocal > golesVisitante || golesLocal == golesVisitante) {
                        repo.registraCombate(equipoLocal, equipoVisitante, golesLocal, golesVisitante);
                    }else{
                        repo.registraCombate(equipoVisitante, equipoLocal, golesVisitante, golesLocal);
                    }
                }
            } else if (control == 3) {
                System.out.println("Modulo de informes");

                informes: while (true){
                    System.out.println("Seleccione el informe que quiere ver:\n\t1. Equipo más goleador\n\t2. Equipo con más Puntos" +
                            "\n\t3. Equipo con más partidos ganados\n\t4. Total de goles anotados por todos\n\t5. Promedio de goles anotados");
                    int informe = sc.nextInt();
                    if (informe == 1){
                        Equipo mejor = repo.estadisticas("goles", repo.listar());
                        System.out.println("El " + mejor.getNombre() + " tiene " + mejor.getGf() + " goles");
                    } else if (informe == 2) {
                        Equipo puntos = repo.estadisticas("puntos", repo.listar());
                        System.out.println("El " + puntos.getNombre() + " tiene " + puntos.getTp() + " puntos");
                    } else if (informe == 3) {
                        Equipo partidos = repo.estadisticas("partidos", repo.listar());
                        System.out.println("El " + partidos.getNombre() + " tiene " + partidos.getPg() + " partidos");
                    } else if (informe == 4) {
                        int goles = repo.totalGoles(repo.listar());
                        System.out.println("El torneo tuvo " + goles + " goles");
                    } else if (informe == 5) {
                        int promedio = repo.promedio(repo.listar());
                        System.out.println("El torneo tuvo un promedio de " + promedio + " goles por equipo");
                    }

                    System.out.println("quieres seguir viendo más informes?\n\ts para salir\n\tp para seguir viendo informes");
                    String option = sc.next();
                    if (option.equalsIgnoreCase("s")){
                        break informes;
                    }
                }
            } else if (control == 4){
                if(totalEquipos == 0){
                    System.out.println("\n----------------------------------");
                    System.out.println("** No hay equipos registrados **");
                    System.out.println("----------------------------------\n");
                }else {
                    System.out.println("Listado de equipos");
                    List<Equipo> equipos = repo.listar();
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("| ID \t| PJ \t| PG \t| PP \t| PE \t| GF \t| GC \t| TP \t|  NOMBRE \t\t|");
                    System.out.println("--------------------------------------------------------------------------------");
                    equipos.forEach(equipo -> {
                        System.out.println("| " +equipo.getId() +" \t| "+  equipo.getPj() +" \t| "+ equipo.getPg() +" \t| "+ equipo.getPp() +" \t| "+ equipo.getPe() +" \t| "+ equipo.getGf() +" \t| "+ equipo.getGc() +" \t| "+ equipo.getTp() +" \t| "+equipo.getNombre());
                    });
                    System.out.println("--------------------------------------------------------------------------------");
                }
            }
            else if (control == 0){
                break;
            }
        }
        System.out.println("-----------------");
        System.out.println("** Estás fuera **");
        System.out.println("-----------------");

    }
}