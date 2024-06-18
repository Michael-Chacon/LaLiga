package org.laLiga.equipo.adapter.in;

import org.laLiga.console.Console;
import org.laLiga.equipo.application.TeamServices;
import org.laLiga.equipo.domain.entities.Equipo;

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
            System.out.println("\t2. Actualizar Pais");
            System.out.println("\t3. Buscar Pais por ID");
            System.out.println("\t4. Eliminar Pais");
            System.out.println("\t5. Listar todos Paises");
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
            teamServices.createTeam(new Equipo(nombre, 0,0,0,0,0,0,0));
            String option = console.readString("quieres registrar otro equipo? (y/n): ");
            if (option.equalsIgnoreCase("n")){
                break registro;
            }
        }
    }
}
