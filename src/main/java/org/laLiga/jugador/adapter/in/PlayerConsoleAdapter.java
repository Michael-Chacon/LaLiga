package org.laLiga.jugador.adapter.in;

import org.laLiga.console.Console;
import org.laLiga.equipo.domain.entities.Equipo;
import org.laLiga.jugador.application.PlayerService;
import org.laLiga.jugador.domain.entities.Jugador;

import java.util.Optional;

public class PlayerConsoleAdapter {

    private final PlayerService playerService;

    public PlayerConsoleAdapter(PlayerService playerService) {
        this.playerService = playerService;
    }

    public void registrarJugador(Optional<Equipo> equipo, Console console){
        System.out.println("--------------------------------------------------------");
        System.out.println("** Registrar player en el " + equipo.get().getNombre() + " **");
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

            String nombre = console.readString("Nombre: ");

            String apellido = console.readString("Apellidos: ");

            int edad = console.readInt("Edad: ");

            int dorsal = console.readInt("Número del dorsal: ");

            System.out.println("Posiciones en el fútbol");
            System.out.println("___________________________________________");
            System.out.println(String.format("| %-7s | %-30s |", "ID", "NOMBRE"));
            for (int i = 0; i < posiciones.length; i++){
                System.out.println("___________________________________________");
//                System.out.println("| " + i + " \t| " + posiciones[i]);
                System.out.println(String.format("| %-7s | %-30s |", i, posiciones[i]));
            }
            System.out.println("___________________________________________");

            int indice = console.readInt("Seleccione la posición: ");
            String posicion = posiciones[indice];

            String nacionalidad = console.readString("Nacionalidad: ");

            String fechaIngreso = console.readString("Fecha de ingreso, formato dd-mm-yyyy: ");

            playerService.createPlayer(new Jugador(nombre, apellido, edad, equipo.get().getId(), dorsal, posicion, nacionalidad, fechaIngreso));

            String option = console.readString("quieres registrar otro player? (y/n): ");
            if (option.equalsIgnoreCase("n")){
                break registrarJugador;
            }
        }
    }

//    public void recordPlayerGoal(int golesEquipo, Equipo team, Console console){
//        System.out.println("--------------------------");
//        System.out.println("** Registro de goles **");
//        System.out.println("--------------------------\n");
//        System.out.println("Listados de playeres del " + team.getNombre());
//
//        for (Jugador j: player.listar()){
//            if (j.getIdEquipo() == team.getId()){
//                System.out.println(j.getId() + ". " + j.getNombre().concat(j.getApellido()));
//            }
//        }
//        int totalGoles = golesEquipo;
//        registrarGoles: while (true){
//            int idJugador = console.readInt("Seleccione el id del player que hizo el gol: ");
//            Jugador goleador = player.buscarPorId(idJugador);
//            int goles = console.readInt("Cuantos goles hizo " + goleador.getNombre() + ": ");
//
//            if (goles > totalGoles){
//                System.out.println("Mal, La cantidad de goles que hizo un player no puede ser superior a los goles anotados en el partido");
//                continue registrarGoles;
//            } else if (goles <= totalGoles) {
//                totalGoles = totalGoles - goles;
//            }
//            goleador.setGolesAnotados(goleador.getGolesAnotados() + goles);
//            player.actualizarObjeto(goleador);
//            if (totalGoles == 0){
//                break registrarGoles;
//            }
//            System.out.println("Quedan " + totalGoles + " goles");
//        }
//    }

//    public void recordPlayerCard(Equipo team, Console console){
//        System.out.println("Listados de playeres del " + team.getNombre());
//        for (Jugador j: player.listar()){
//            if (j.getIdEquipo() == team.getId()){
//                System.out.println(j.getId() + ". " + j.getNombre().concat(j.getApellido()));
//            }
//        }
//        tarjetasLocal: while(true){
//            int idJugador = console.readInt("Seleccione el id del player que recibió la tarjeta: ");
//            Jugador goleador = player.buscarPorId(idJugador);
//            int color = console.readInt("Qué tarjeta recibió el player " + goleador.getNombre().concat(goleador.getApellido()) + "\n\t1. Amarilla\n\t2. Roja\n");
//            if (color == 1){
//                goleador.setTarjetasAmarillas(goleador.getTarjetasAmarillas() + 1 );
//                player.actualizarObjeto(goleador);
//            } else if (color == 2) {
//                goleador.setTarjetasRojas(goleador.getTarjetasRojas() + 1);
//                player.actualizarObjeto(goleador);
//            }
//
//            String option = console.readString("Vas a registrar más tarjetas? (y/n): ");
//            if (option.equalsIgnoreCase("n")){
//                break tarjetasLocal;
//            }
//        }
//    }
}
