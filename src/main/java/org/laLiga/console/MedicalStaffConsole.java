package org.laLiga.console;

import org.laLiga.Validacion.Validacion;
import org.laLiga.abstraccion.Repositorio;
import org.laLiga.cuerpoMedico.domain.entities.CuerpoMedico;
import org.laLiga.equipo.domain.entities.Equipo;

public class MedicalStaffConsole {
    Repositorio<CuerpoMedico> cuerpoMedico;

    public MedicalStaffConsole(Repositorio<CuerpoMedico> cuerpoMedico) {
        this.cuerpoMedico = cuerpoMedico;
    }

    public void registrarCuMedico(Equipo equipo, Console console){
        System.out.println("--------------------------------------------------------");
        System.out.println("** Registrar cuerpo medico en el " + equipo.getNombre() + " **");
        System.out.println("---------------------------------------------------------\n");

        String[] roles = {"Médico", "Fisioterapeuta"};

        registrarCuMedico: while (true){
            int idCuMedico = cuerpoMedico.ultimoId();

            String nombre = console.readString("Nombre: ");

            String apellido = console.readString("Apellidos: ");

            int edad = console.readInt("Edad: ");

            System.out.println("Roles: ");
            for (int i = 0; i < roles.length; i++){
                System.out.println(i + ". " + roles[i]);
            }
            int indice = Validacion.validarInt("Selecciona el rol por el número: ");
            String rol = roles[indice];

            cuerpoMedico.addObject(new CuerpoMedico(idCuMedico, nombre, apellido, rol, edad, equipo.getId()));

            String option = console.readString("quieres registrar otro integrante en el plantel?(y/n): ");
            if (option.equalsIgnoreCase("n")){
                break registrarCuMedico;
            }
        }
    }
}
