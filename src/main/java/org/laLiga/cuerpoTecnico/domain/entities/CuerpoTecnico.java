package org.laLiga.cuerpoTecnico.domain.entities;

import org.laLiga.abstraccion.Plantel;

public class CuerpoTecnico extends Plantel {

    public CuerpoTecnico() {
    }

    public CuerpoTecnico(int id, String nombre, String apellido, String rol, int edad, int idEquipo) {
        super(id, nombre, apellido, rol, edad, idEquipo);
    }

}
