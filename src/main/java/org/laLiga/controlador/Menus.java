package org.laLiga.controlador;

import org.laLiga.modelo.Equipo;
import org.laLiga.plantel.Jugador;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menus {

    AdministrarEquipo repo = new AdministrarEquipo();
    JugadorRepositorio jugador = new JugadorRepositorio();
    CuMedicoRepositorio cuerpoMedico = new CuMedicoRepositorio();
    CuTecnicoRepositorio cuerpoTecnico = new CuTecnicoRepositorio();

    Scanner sc = new Scanner(System.in);

    public void registrarEquipo(){
        System.out.println("--------------------------");
        System.out.println("** Registro de equipos **");
        System.out.println("--------------------------\n");
        registro: while(true){
            int id = repo.ultimoid();
            System.out.println("Ingrese el nombre del equipo");
            String nombre = sc.nextLine();
            sc.nextLine();
            repo.crear(new Equipo(id, nombre, 0,0,0,0,0,0,0));
            System.out.println("quieres registrar otro equipo?(y/n)");
            String option = sc.next();
            if (option.equalsIgnoreCase("n")){
                break registro;
            }
        }
    }

    public void registrarPlantel(){
        System.out.println("----------------------------------");
        System.out.println("** Registrar miembros del plantel **");
        System.out.println("----------------------------------\n");
        System.out.println("Seleccione por el id al equipo para registra empleados en él");

        System.out.println("----------------------");
        System.out.println("| ID \t| NOMBRE");
        System.out.println("----------------------");
        List<Equipo> equipos = repo.listar();
        equipos.forEach(equipo -> {
            System.out.println("| " + equipo.getId() + " \t| " + equipo.getNombre());
        });

        System.out.print("Seleccione el id: ");
        int idEQuipo = sc.nextInt();

        Equipo equipoSeleccionado = repo.buscarPorId(idEQuipo);

        System.out.println("Que rol quiere registrar:\n\t1. Jugador\n\t2. Cuerpo tecnico\n\t3. Cuerpo medico");
        int rol = sc.nextInt();

        if (rol == 1){
            registrarJugador(equipoSeleccionado);
        } else if (rol == 2){
            registrarCuTecnico(equipoSeleccionado);
        } else if (rol == 3) {
            registrarCuMedico(equipoSeleccionado);
        }
    }

    public void registrarJugador(Equipo equipo){
        System.out.println("--------------------------------------------------------");
        System.out.println("** Registrar jugador en el " + equipo.getNombre() + " **");
        System.out.println("---------------------------------------------------------\n");
        String[] posiciones = {
                "Portero",
                "Defensa Central",
                "Lateral Derecho",
                "Lateral Izquierdo",
                "Defensa Central (Sweeper)",
                "Centrocampista Defensivo",
                "Centrocampista Central",
                "Centrocampista Ofensivo",
                "Extremo Derecho",
                "Extremo Izquierdo",
                "Delantero Centro",
                "Segundo Delantero",
                "Extremo Derecho (Delantero)",
                "Extremo Izquierdo (Delantero)"
        };


        registrarJugador: while (true){
            int idJugador = jugador.ultimoId();

            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            sc.nextLine();

            System.out.print("Apellidos: ");
            String apellido = sc.nextLine();
            sc.nextLine();

            System.out.print("Edad: ");
            int edad = sc.nextInt();

            System.out.print("Número del dorsal: ");
            int dorsal = sc.nextInt();

            System.out.println("Posiciones en el fútbol");
            System.out.println("----------------------");
            System.out.println("| ID \t| NOMBRE");
            System.out.println("----------------------");
            for (int i = 0; i < posiciones.length; i++){
                System.out.println("| " + i + " \t| " + posiciones[i]);
            }
            System.out.print("Seleccione la posición: ");
            String posicion = posiciones[sc.nextInt()];

            System.out.println("Nacionalidad: ");
            String nacionalidad = sc.next();

            LocalDate fechaIngreso = LocalDate.now();

            jugador.addObject(new Jugador(idJugador, nombre, apellido, edad, equipo.getId(), dorsal, posicion, nacionalidad, fechaIngreso));

            System.out.println("quieres registrar otro equipo?(y/n)");
            String option = sc.next();
            if (option.equalsIgnoreCase("n")){
                break registrarJugador;
            }
        }
    }

    public void registrarCuTecnico(Equipo equipo){

    }

    public void registrarCuMedico(Equipo equipo){

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
