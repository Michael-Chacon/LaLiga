package org.laLiga.controlador;

import org.laLiga.abstraccion.Repositorio;
import org.laLiga.modelo.CuerpoMedico;

import java.util.ArrayList;
import java.util.List;

public class CuMedicoRepositorio extends Repositorio<CuerpoMedico> {

    List<CuerpoMedico> listaCuerpoMedico = new ArrayList<>();

    @Override
    public void addObject(CuerpoMedico valor) {
        this.listaCuerpoMedico.add(valor);
    }

    @Override
    public List<CuerpoMedico> listar() {
        return this.listaCuerpoMedico;
    }

    @Override
    public CuerpoMedico buscarPorId(int id) {
        return null;
    }

    @Override
    public int ultimoId() {
        return this.listaCuerpoMedico.size() + 1;
    }
}
