package org.laLiga.servicios;

import org.laLiga.Validacion.Validacion;
import org.laLiga.abstraccion.Repositorio;
import org.laLiga.modelo.CuerpoMedico;
import org.laLiga.modelo.Equipo;
import org.laLiga.modelo.CuerpoTecnico;
import org.laLiga.modelo.Jugador;

import java.util.List;
import java.util.Scanner;

public class Menus {

    AdministrarEquipo repo = new AdministrarEquipo();
    Repositorio<Jugador> jugador = new JugadorRepositorio();
    Repositorio<CuerpoMedico> cuerpoMedico = new CuMedicoRepositorio();
    Repositorio<CuerpoTecnico> cuerpoTecnico = new CuTecnicoRepositorio();

    Scanner sc = new Scanner(System.in);

    public void registrarEquipo(){
        System.out.println("--------------------------");
        System.out.println("** Registro de equipos **");
        System.out.println("--------------------------\n");
        registro: while(true){
            int id = repo.ultimoid();
            System.out.println("Ingrese el nombre del equipo");
            String nombre = sc.nextLine();
            repo.crear(new Equipo(id, nombre, 0,0,0,0,0,0,0));
            System.out.print("quieres registrar otro equipo?(y/n)");
            String option = sc.nextLine();
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

        List<Equipo> equipos = repo.listar();
        System.out.println("_______________________________");
        System.out.println(String.format("| %-5s | %-20s |", "ID", "NOMBRE"));
        equipos.forEach(equipo -> {
            System.out.println("_______________________________");
            System.out.println(String.format("| %-5s | %-20s |", equipo.getId(), equipo.getNombre()));
        });
        System.out.println("_______________________________");

        int idEQuipo = Validacion.validarInt("Seleccione el id: ");
        Equipo equipoSeleccionado = repo.buscarPorId(idEQuipo);

        int rol = Validacion.validarInt("Que rol quiere registrar:\n\t1. Jugador\n\t2. Cuerpo tecnico\n\t3. Cuerpo medico\n");

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

            System.out.print("Apellidos: ");
            String apellido = sc.nextLine();

            int edad = Validacion.validarInt("Edad: ");

            int dorsal = Validacion.validarInt("Número del dorsal: ");

            System.out.println("Posiciones en el fútbol");
            System.out.println("___________________________________________");
//            System.out.println("| ID \t| NOMBRE");
            System.out.println(String.format("| %-7s | %-30s |", "ID", "NOMBRE"));
            for (int i = 0; i < posiciones.length; i++){
                System.out.println("___________________________________________");
//                System.out.println("| " + i + " \t| " + posiciones[i]);
                System.out.println(String.format("| %-7s | %-30s |", i, posiciones[i]));
            }
            System.out.println("___________________________________________");
            int indice = Validacion.validarInt("Seleccione la posición: ");
            String posicion = posiciones[indice];

            System.out.print("Nacionalidad: ");
            String nacionalidad = sc.nextLine();

            System.out.println("Fecha de ingreso, formato dd-mm-yyyy: ");
            String fechaIngreso = sc.nextLine();

            jugador.addObject(new Jugador(idJugador, nombre, apellido, edad, equipo.getId(), dorsal, posicion, nacionalidad, fechaIngreso));

            System.out.println("quieres registrar otro jugador?(y/n)");
            String option = sc.nextLine();
            if (option.equalsIgnoreCase("n")){
                break registrarJugador;
            }
        }
        for (Jugador jugador: jugador.listar()){
            System.out.println(jugador.toString());
        }
    }

    public void registrarCuTecnico(Equipo equipo){
        System.out.println("--------------------------------------------------------");
        System.out.println("** Registrar cuerpo técnico en el " + equipo.getNombre() + " **");
        System.out.println("---------------------------------------------------------\n");

        String[] roles = {"Técnico", "Asistente técnico", "Preparador físico"};

        registrarCuTecnico: while (true){
            int idCuTecnico = cuerpoTecnico.ultimoId();

            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Apellidos: ");
            String apellido = sc.nextLine();

            int edad = Validacion.validarInt("Edad: ");

            System.out.println("Roles: ");
            for (int i = 0; i < roles.length; i++){
                System.out.println(i + ". " + roles[i]);
            }
            int indice = Validacion.validarInt("Selecciona el rol por el número: ");
            String rol = roles[indice];

            cuerpoTecnico.addObject(new CuerpoTecnico(idCuTecnico, nombre, apellido, rol, edad, equipo.getId()));

            System.out.print("quieres registrar otro integrante en el plantel?(y/n): ");
            String option = sc.nextLine();
            if (option.equalsIgnoreCase("n")){
                break registrarCuTecnico;
            }
        }
        for (CuerpoTecnico cu: cuerpoTecnico.listar()){
            System.out.println(cu.toString());
        }
    }

    public void registrarCuMedico(Equipo equipo){
        System.out.println("--------------------------------------------------------");
        System.out.println("** Registrar cuerpo medico en el " + equipo.getNombre() + " **");
        System.out.println("---------------------------------------------------------\n");

        String[] roles = {"Médico", "Fisioterapeuta"};

        registrarCuMedico: while (true){
            int idCuMedico = cuerpoMedico.ultimoId();

            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Apellidos: ");
            String apellido = sc.nextLine();

            int edad = Validacion.validarInt("Edad: ");

            System.out.println("Roles: ");
            for (int i = 0; i < roles.length; i++){
                System.out.println(i + ". " + roles[i]);
            }
            int indice = Validacion.validarInt("Selecciona el rol por el número: ");
            String rol = roles[indice];

            cuerpoMedico.addObject(new CuerpoMedico(idCuMedico, nombre, apellido, rol, edad, equipo.getId()));

            System.out.print("quieres registrar otro integrante en el plantel? (y/n): ");
            String option = sc.nextLine();
            if (option.equalsIgnoreCase("n")){
                break registrarCuMedico;
            }
        }

        for (CuerpoMedico medico: cuerpoMedico.listar()){
            System.out.println(medico.toString());
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

            int local = Validacion.validarInt("Escriba el id del equipo que jugó de local: ");
            Equipo equipoLocal = repo.buscarPorId(local);
            int golesLocal = Validacion.validarInt("Cuantos goles hizo " + equipoLocal.getNombre()+ ": ");

            if (golesLocal > 0){
                System.out.println("--------------------------");
                System.out.println("** Registro de goles **");
                System.out.println("--------------------------\n");
                System.out.println("Listados de jugadores del " + equipoLocal.getNombre());
                for (Jugador j: jugador.listar()){
                    if (j.getIdEquipo() == equipoLocal.getId()){
                        System.out.println(j.getId() + ". " + j.getNombre().concat(j.getApellido()));
                    }
                }
                registrarGoles: while (true){
                    int idJugador = Validacion.validarInt("Seleccione el id del jugador que hizo el gol: ");
                    Jugador goleador = jugador.buscarPorId(idJugador);
                    int goles = Validacion.validarInt("Cuantos goles hizo " + goleador.getNombre() + ": ");

                    goleador.setGolesAnotados(goleador.getGolesAnotados() + goles);
                    jugador.actualizarObjeto(goleador);
                    System.out.print("Alguien más hizo gol? (y/n): ");
                    String option = sc.nextLine();
                    if (option.equalsIgnoreCase("n")){
                        break registrarGoles;
                    }
                }
            }

            System.out.println("¿Hubo tarjeta para algún jugador del "+ equipoLocal.getNombre() +" (y/n)");
            String opcion = sc.nextLine();
            if (opcion.equals("y")){
                System.out.println("Listados de jugadores del " + equipoLocal.getNombre());
                for (Jugador j: jugador.listar()){
                    if (j.getIdEquipo() == equipoLocal.getId()){
                        System.out.println(j.getId() + ". " + j.getNombre().concat(j.getApellido()));
                    }
                }
                tarjetasLocal: while(true){
                    int idJugador = Validacion.validarInt("Seleccione el id del jugador que recibió la tarjeta: ");
                    Jugador goleador = jugador.buscarPorId(idJugador);
                    int color = Validacion.validarInt("Qué tarjeta recibió el jugador " + goleador.getNombre().concat(goleador.getApellido()) + "\n\t1. Amarilla\n\t2. Roja\n");
                    if (color == 1){
                        goleador.setTarjetasAmarillas(goleador.getTarjetasAmarillas() + 1 );
                        jugador.actualizarObjeto(goleador);
                    } else if (color == 2) {
                        goleador.setTarjetasRojas(goleador.getTarjetasRojas() + 1);
                        jugador.actualizarObjeto(goleador);
                    }

                    System.out.print("Vas a registrar más tarjetas? (y/n): ");
                    String option = sc.nextLine();
                    if (option.equalsIgnoreCase("n")){
                        break tarjetasLocal;
                    }
                }
            }

            int visitante = Validacion.validarInt("Escriba el id del equipo que jugó de Visitante");
            Equipo equipoVisitante = repo.buscarPorId(visitante);
            int golesVisitante = Validacion.validarInt("Cuantos goles hizo " + equipoVisitante.getNombre()+ ": ");

            if (golesLocal > golesVisitante || golesLocal == golesVisitante) {
                repo.registraCombate(equipoLocal, equipoVisitante, golesLocal, golesVisitante);
            }else{
                repo.registraCombate(equipoVisitante, equipoLocal, golesVisitante, golesLocal);
            }

            if (golesVisitante > 0){
                System.out.println("--------------------------");
                System.out.println("** Registro de goles **");
                System.out.println("--------------------------\n");
                System.out.println("Listados de jugadores del " + equipoVisitante.getNombre());
                for (Jugador j: jugador.listar()){
                    if (j.getIdEquipo() == equipoVisitante.getId()){
                        System.out.println(j.getId() + ". " + j.getNombre().concat(j.getApellido()));
                    }
                }
                registrarGolesV: while (true){
                    int idJugador = Validacion.validarInt("Seleccione el id del jugador que hizo el gol: ");
                    Jugador goleador = jugador.buscarPorId(idJugador);
                    int goles = Validacion.validarInt("Cuantos goles hizo " + goleador.getNombre() + ": ");
                    goleador.setGolesAnotados(goleador.getGolesAnotados() + goles);
                    jugador.actualizarObjeto(goleador);
                    System.out.print("Alguien más hizo gol? (y/n): ");
                    String option = sc.nextLine();
                    if (option.equalsIgnoreCase("n")){
                        break registrarGolesV;
                    }
                }
            }

            System.out.print("¿Hubo tarjeta para algún jugador del "+ equipoVisitante.getNombre() +" (y/n): ");
            String opcionVisitante = sc.nextLine();
            if (opcionVisitante.equals("y")){
                System.out.println("Listados de jugadores del " + equipoVisitante.getNombre());
                for (Jugador j: jugador.listar()){
                    if (j.getIdEquipo() == equipoVisitante.getId()){
                        System.out.println(j.getId() + ". " + j.getNombre().concat(j.getApellido()));
                    }
                }
                tarjetasVisitante: while(true){
                    int idJugador = Validacion.validarInt("Seleccione el id del jugador que recibió la tarjeta: ");
                    Jugador goleador = jugador.buscarPorId(idJugador);
                    int color = Validacion.validarInt("Qué tarjeta recibió el jugador " + goleador.getNombre().concat(goleador.getApellido()) + "\n\t1. Amarilla\n\t2. Roja\n");
                    if (color == 1){
                        goleador.setTarjetasAmarillas(goleador.getTarjetasAmarillas() + 1 );
                        jugador.actualizarObjeto(goleador);
                    } else if (color == 2) {
                        goleador.setTarjetasRojas(goleador.getTarjetasRojas() + 1);
                        jugador.actualizarObjeto(goleador);
                    }

                    System.out.print("Vas a registrar más tarjetas? (y/n): ");
                    String option = sc.nextLine();
                    if (option.equalsIgnoreCase("n")){
                        break tarjetasVisitante;
                    }
                }
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
                int informeSeleccionado = Validacion.validarInt("Seleccione el informe que quiere ver:\n\t1. Equipo más goleador\n\t2. Equipo con más Puntos" +
                        "\n\t3. Equipo con más partidos ganados\n\t4. Total de goles anotados por todos\n\t5. Promedio de goles anotados" +
                        "\n\t6. Jugador con más goles\n\t7. Jugador con más tarjetas amarillas\n\t8. Jugador con más tarjetas rojas" +
                        "\n\t9. Jugadores por equipo\n\t10. Cuerpo técnico por equipo\n");
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
                } else if (informeSeleccionado == 6) {
                    Jugador jugadorMasGoles = informe.jugadorMasGoles(jugador.listar());
                    Equipo equipo = repo.buscarPorId(jugadorMasGoles.getIdEquipo());
                    System.out.println("El jugador " + jugadorMasGoles.getNombre().concat(" ").concat(jugadorMasGoles.getApellido()) + " del " + equipo.getNombre() +
                            " hizo " + jugadorMasGoles.getGolesAnotados() + " goles.");
                } else if (informeSeleccionado == 7) {
                    Jugador jugadorTarjetas = informe.masTarjetasAmarillas(jugador.listar());
                    Equipo equipo = repo.buscarPorId(jugadorTarjetas.getIdEquipo());
                    System.out.println("El jugador " + jugadorTarjetas.getNombre().concat(" ").concat(jugadorTarjetas.getApellido()) + " del " + equipo.getNombre() +
                            " tiene " + jugadorTarjetas.getTarjetasAmarillas() + " tarjetas amarillas.");
                } else if (informeSeleccionado == 8) {
                    Jugador jugadorTarjetas = informe.masTarjetasRojas(jugador.listar());
                    Equipo equipo = repo.buscarPorId(jugadorTarjetas.getIdEquipo());
                    System.out.println("El jugador " + jugadorTarjetas.getNombre().concat(" ").concat(jugadorTarjetas.getApellido()) + " del " + equipo.getNombre() +
                            " tiene " + jugadorTarjetas.getTarjetasRojas() + " tarjetas rojas.");
                }else if(informeSeleccionado == 9){
                    System.out.println("Listado de equipos:");
                    System.out.println("_________________________________");
                    System.out.println(String.format("| %4s | %-20s |", "ID", "EQUIPO"));
                    for (Equipo equipo: repo.listar()){
                        System.out.println("_________________________________");
                        System.out.println(String.format("| %4s | %-20s |", equipo.getId(), equipo.getNombre()));
                    }
                    System.out.println("_________________________________");

                    int id = Validacion.validarInt("Elige el equipo por el id: ");
                    Equipo equipo = repo.buscarPorId(id);
                    System.out.println("Jugadores del " + equipo.getNombre());
                    System.out.println("______________________________________________________________________");
                    System.out.println(String.format("| %-22s | %-7s | %-30s |", "NOMBRE", "DORSAL","POSICIÓN"));
                    for (Jugador j: jugador.listar()){
                        if (j.getIdEquipo() == equipo.getId()){
                            System.out.println("______________________________________________________________________");
                            System.out.println(String.format("| %-22s | %-7s | %-30s |", j.getNombre().concat(" ").concat(j.getApellido()), j.getDorsal(), j.getPosicionJuego()));
                        }
                    }
                    System.out.println("______________________________________________________________________");
                }else if(informeSeleccionado == 10){
                    System.out.println("Listado de equipos:");
                    System.out.println("_________________________________");
                    System.out.println(String.format("| %4s | %-20s |", "ID", "EQUIPO"));
                    for (Equipo equipo: repo.listar()){
                        System.out.println("_________________________________");
                        System.out.println(String.format("| %4s | %-20s |", equipo.getId(), equipo.getNombre()));
                    }
                    System.out.println("_________________________________");
                    int id = Validacion.validarInt("Elige el equipo por el id: ");
                    Equipo equipo = repo.buscarPorId(id);
                    System.out.println("\nCuerpo técnico del " + equipo.getNombre());
                    System.out.println("_________________________________________________");
                    System.out.printf(String.format("| %-20s | %-22s |\n", "NOMBRE", "ROL"));
                    for (CuerpoTecnico cu: cuerpoTecnico.listar()){
                        if (cu.getIdEquipo() == equipo.getId()){
                            System.out.println("_________________________________________________");
                            System.out.printf(String.format("| %-20s | %-22s |\n", cu.getNombre().concat(" ").concat(cu.getApellido()), cu.getRol()));
                        }
                    }
                    System.out.println("_________________________________________________");

                }

                System.out.print("\nquieres seguir viendo más informes? (y/n): ");
                String option = sc.nextLine();
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

//        equipos.sort((tp1, tp2) -> Integer.compare(tp2.getTp(), tp1.getTp()));

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("| ID \t| PJ \t| PG \t| PP \t| PE \t| GF \t| GC \t| TP \t|  NOMBRE \t\t|");
        System.out.println("--------------------------------------------------------------------------------");
        equipos.forEach(equipo -> {
            System.out.println("| " +equipo.getId() +" \t| "+  equipo.getPj() +" \t| "+ equipo.getPg() +" \t| "+ equipo.getPp() +" \t| "+ equipo.getPe() +" \t| "+ equipo.getGf() +" \t| "+ equipo.getGc() +" \t| "+ equipo.getTp() +" \t| "+equipo.getNombre());
        });
        System.out.println("--------------------------------------------------------------------------------");

    }
}
