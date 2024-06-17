package org.laLiga;
import org.laLiga.console.Console;
import org.laLiga.console.Menus;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        Menus menu = new Menus();
        int control = 0;

        principal: while(true){
            System.out.println("----------");
            System.out.println("| LA LIGA |");
            System.out.println("----------");

            System.out.println("Options: \n\t1. Registrar equipo\n\t2. Registrar plantel\n\t3. Registrar juego\n\t4. Informes\n\t5. Tabla\n\t0. Salir");
            control = console.readInt("Seleccione una opción: ");
            if (control == 1){
                menu.showTeamMenu();
            }else if(control == 2){
                menu.registrarPlantel();
            }else if (control == 3){
                menu.fecha();
            } else if (control == 4) {
                menu.informes();
            } else if (control == 5){
                menu.mostrarEquipos();
            } else if (control == 0){
                break;
            }
        }
        System.out.println("-----------------");
        System.out.println("** Estás fuera **");
        System.out.println("-----------------");

    }
}