package org.laLiga;
import org.laLiga.controlador.Menus;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menus menu = new Menus();
        int control = 0;

        principal: while(true){
            System.out.println("----------");
            System.out.println("| LA LIGA |");
            System.out.println("----------");

            System.out.println("Options: \n\t1. Registrar equipo\n\t2. Registrar juego\n\t3. Informes\n\t4. Tabla\n\t0. Salir");
            control = sc.nextInt();

            if (control == 1){
                menu.registrarEquipo();
            }else if (control == 2){
                menu.fecha();
            } else if (control == 3) {
                menu.informes();
            } else if (control == 4){
                menu.mostrarEquipos();
            }            else if (control == 0){
                break;
            }
        }
        System.out.println("-----------------");
        System.out.println("** Estás fuera **");
        System.out.println("-----------------");

    }
}