package org.laLiga.console;

import org.laLiga.abstraccion.Repositorio;
import org.laLiga.cuerpoTecnico.domain.entities.CuerpoTecnico;
import org.laLiga.equipo.domain.entities.Equipo;

public class CoachingStaffConsole {
    Repositorio<CuerpoTecnico> cuerpoTecnicoRepositorio;

    public CoachingStaffConsole(Repositorio<CuerpoTecnico> cuerpoTecnicoRepositorio) {
        this.cuerpoTecnicoRepositorio = cuerpoTecnicoRepositorio;
    }

    public void registrarCuTecnico(Equipo equipo, Console console){
        System.out.println("--------------------------------------------------------");
        System.out.println("** Registrar cuerpo técnico en el " + equipo.getNombre() + " **");
        System.out.println("---------------------------------------------------------\n");

        String[] roles = {"Técnico", "Asistente técnico", "Preparador físico"};

        registrarCuTecnico: while (true){
            int idCuTecnico = cuerpoTecnicoRepositorio.ultimoId();

            String nombre = console.readString("Nombre: ");

            String apellido = console.readString("Apellidos: ");

            int edad = console.readInt("Edad: ");

            System.out.println("Roles: ");
            for (int i = 0; i < roles.length; i++){
                System.out.println(i + ". " + roles[i]);
            }
            int indice = console.readInt("Selecciona el rol por el número: ");
            String rol = roles[indice];

            cuerpoTecnicoRepositorio.addObject(new CuerpoTecnico(idCuTecnico, nombre, apellido, rol, edad, equipo.getId()));

            String option = console.readString("quieres registrar otro integrante en el plantel?(y/n): ");
            if (option.equalsIgnoreCase("n")){
                break registrarCuTecnico;
            }
        }
    }
}
