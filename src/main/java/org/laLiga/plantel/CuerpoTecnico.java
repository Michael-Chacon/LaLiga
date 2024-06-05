package org.laLiga.plantel;

import org.laLiga.abstraccion.Plantel;

import java.util.ArrayList;
import java.util.List;

public class CuerpoTecnico extends Plantel<CuerpoTecnico> {
    List<CuerpoTecnico> cuerpoTecnicos;

    public CuerpoTecnico() {
        this.cuerpoTecnicos = new ArrayList<>();
    }

    public CuerpoTecnico(int id, String nombre, String apellidos, String rol, int edad, int idEquipo) {
        super(id, nombre, apellidos, rol, edad, idEquipo);
    }

    @Override
    public void addObject(CuerpoTecnico valor) {
        this.cuerpoTecnicos.add(valor);
    }

    @Override
    public List<CuerpoTecnico> listar() {
        return this.cuerpoTecnicos;
    }

    @Override
    public <T> T buscarPorId(int id) {
        return null;
    }
}
