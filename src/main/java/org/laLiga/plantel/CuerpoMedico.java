package org.laLiga.plantel;

import org.laLiga.abstraccion.Plantel;

import java.util.ArrayList;
import java.util.List;

public class CuerpoMedico extends Plantel <CuerpoMedico>{
    List<CuerpoMedico> cuerpoMedicos;
    public CuerpoMedico() {
        this.cuerpoMedicos = new ArrayList<>();
    }

    public CuerpoMedico(int id, String nombre, String apellidos, String rol, int edad, int idEquipo) {
        super(id, nombre, apellidos, rol, edad, idEquipo);
    }

    @Override
    public void addObject(CuerpoMedico valor) {
        this.cuerpoMedicos.add(valor);
    }

    @Override
    public List<CuerpoMedico> listar() {
        return this.cuerpoMedicos;
    }

    @Override
    public Object buscarPorId(int id) {
        return null;
    }
}
