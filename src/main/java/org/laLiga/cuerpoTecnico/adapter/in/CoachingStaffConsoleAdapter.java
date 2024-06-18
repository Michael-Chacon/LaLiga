package org.laLiga.cuerpoTecnico.adapter.in;

import org.laLiga.console.Console;
import org.laLiga.cuerpoTecnico.application.CoachingStaffServices;
import org.laLiga.cuerpoTecnico.domain.entities.CuerpoTecnico;
import org.laLiga.equipo.domain.entities.Equipo;

import java.util.Optional;

public class CoachingStaffConsoleAdapter {
    private final CoachingStaffServices coachingStaffServices;

    public CoachingStaffConsoleAdapter(CoachingStaffServices coachingStaffServices) {
        this.coachingStaffServices = coachingStaffServices;
    }

    public void registrarCuTecnico(Optional<Equipo> equipo, Console console){
        System.out.println("--------------------------------------------------------");
        System.out.println("** Registrar cuerpo técnico en el " + equipo.get().getNombre() + " **");
        System.out.println("---------------------------------------------------------\n");

        String[] roles = {"Técnico", "Asistente técnico", "Preparador físico"};

        registrarCuTecnico: while (true){

            String nombre = console.readString("Nombre: ");

            String apellido = console.readString("Apellidos: ");

            int edad = console.readInt("Edad: ");

            System.out.println("Roles: ");
            for (int i = 0; i < roles.length; i++){
                System.out.println(i + ". " + roles[i]);
            }
            int indice = console.readInt("Selecciona el rol por el número: ");
            String rol = roles[indice];

            coachingStaffServices.createCoachingStaff(new CuerpoTecnico(nombre, apellido, rol, edad, equipo.get().getId()));

            String option = console.readString("quieres registrar otro integrante en el plantel?(y/n): ");
            if (option.equalsIgnoreCase("n")){
                break registrarCuTecnico;
            }
        }
    }
}
