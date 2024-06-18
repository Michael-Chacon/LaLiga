package org.laLiga.cuerpoMedico.adapter.in;

import org.laLiga.Validacion.Validacion;
import org.laLiga.console.Console;
import org.laLiga.cuerpoMedico.application.MedicalStaffService;
import org.laLiga.cuerpoMedico.domain.entities.CuerpoMedico;
import org.laLiga.equipo.domain.entities.Equipo;

import java.util.Optional;

public class MedicalStaffConsoleAdapter {

    private final MedicalStaffService medicalStaffService;

    public MedicalStaffConsoleAdapter(MedicalStaffService medicalStaffService) {
        this.medicalStaffService = medicalStaffService;
    }


    public void registrarCuMedico(Optional<Equipo> equipo, Console console){
        System.out.println("--------------------------------------------------------");
        System.out.println("** Registrar cuerpo medico en el " + equipo.get().getNombre() + " **");
        System.out.println("---------------------------------------------------------\n");

        String[] roles = {"Médico", "Fisioterapeuta"};

        registrarCuMedico: while (true){

            String nombre = console.readString("Nombre: ");

            String apellido = console.readString("Apellidos: ");

            int edad = console.readInt("Edad: ");

            System.out.println("Roles: ");
            for (int i = 0; i < roles.length; i++){
                System.out.println(i + ". " + roles[i]);
            }
            int indice = Validacion.validarInt("Selecciona el rol por el número: ");
            String rol = roles[indice];

            medicalStaffService.createMedicalStaff(new CuerpoMedico(nombre, apellido, rol, edad, equipo.get().getId()));

            String option = console.readString("quieres registrar otro integrante en el plantel?(y/n): ");
            if (option.equalsIgnoreCase("n")){
                break registrarCuMedico;
            }
        }
    }
}
