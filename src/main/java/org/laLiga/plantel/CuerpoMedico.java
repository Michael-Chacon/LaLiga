package org.laLiga.plantel;

import org.laLiga.abstraccion.Plantel;

public class CuerpoMedico extends Plantel {
    public CuerpoMedico() {
    }

    public CuerpoMedico(int id, String nombre, String apellido, String rol, int edad, int idEquipo) {
        super(id, nombre, apellido, rol, edad, idEquipo);
    }
}
