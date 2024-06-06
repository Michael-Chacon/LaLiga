package org.laLiga;

import org.laLiga.modelo.CuerpoMedico;

public class Pruebas {
    public static void main(String[] args) {
        CuerpoMedico cm = new CuerpoMedico();

        cm.setNombre("alexis");
        System.out.println(cm.getNombre());

    }
}
