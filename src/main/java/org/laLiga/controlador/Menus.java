package org.laLiga.controlador;

import org.laLiga.modelo.Equipo;

import java.util.List;
import java.util.Scanner;

public class Menus {
    AdministrarEquipo repo = new AdministrarEquipo();
    Scanner sc = new Scanner(System.in);
    public void registrarEquipo(){
        System.out.println("--------------------------");
        System.out.println("** Registro de equipos **");
        System.out.println("--------------------------\n");
        registro: while(true){
            int id = repo.ultimoid();
            System.out.println("Ingrese el nombre del equipo");
            String nombre = sc.next();
            repo.crear(new Equipo(id, nombre, 0,0,0,0,0,0,0));

            System.out.println("quieres registrar otro equipo?(y/n)");
            String option = sc.next();
            if (option.equalsIgnoreCase("n")){
                break registro;
            }
        }
    }

    public void fecha(){
        List<Equipo> obtenerEquipos = repo.listar();
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
    }

    public void informes(){
        List<Equipo> obtenerEquipos = repo.listar();
        int totalEquipos = obtenerEquipos.size();
        if (totalEquipos < 1){
            System.out.println("\n-------------------------------------------------------");
            System.out.println("** No hay suficientes equipos para iniciar el torneo **");
            System.out.println("-------------------------------------------------------\n");
        }else{
            Informes informe = new Informes();
            System.out.println("Modulo de informes");

            informes: while (true){
                System.out.println("Seleccione el informe que quiere ver:\n\t1. Equipo más goleador\n\t2. Equipo con más Puntos" +
                        "\n\t3. Equipo con más partidos ganados\n\t4. Total de goles anotados por todos\n\t5. Promedio de goles anotados");
                int informeSeleccionado = sc.nextInt();
                if (informeSeleccionado == 1){
                    Equipo mejor = informe.masGoles(repo.listar());
                    System.out.println("El " + mejor.getNombre() + " tiene " + mejor.getGf() + " goles");
                } else if (informeSeleccionado == 2) {
                    Equipo puntos = informe.masPuntos(repo.listar());
                    System.out.println("El " + puntos.getNombre() + " tiene " + puntos.getTp() + " puntos");
                } else if (informeSeleccionado == 3) {
                    Equipo partidos = informe.masPartidosGanados(repo.listar());
                    System.out.println("El " + partidos.getNombre() + " tiene " + partidos.getPg() + " partidos");
                } else if (informeSeleccionado == 4) {
                    int goles = informe.totalGoles(repo.listar());
                    System.out.println("El torneo tuvo " + goles + " goles");
                } else if (informeSeleccionado == 5) {
                    float promedio = informe.promedio(repo.listar());
                    System.out.println("El torneo tuvo un promedio de " + promedio + " goles por equipo");
                }

                System.out.println("quieres seguir viendo más informes? (y/n)");
                String option = sc.next();
                if (option.equalsIgnoreCase("n")){
                    break informes;
                }
            }
        }

    }

    public void mostrarEquipos(){
        List<Equipo> obtenerEquipos = repo.listar();
        int totalEquipos = obtenerEquipos.size();

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
}
