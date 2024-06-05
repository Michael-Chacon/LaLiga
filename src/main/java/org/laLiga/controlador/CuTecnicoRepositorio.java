package org.laLiga.controlador;

import org.laLiga.abstraccion.Repositorio;
import org.laLiga.plantel.CuerpoTecnico;

import java.util.ArrayList;
import java.util.List;

public class CuTecnicoRepositorio extends Repositorio<CuerpoTecnico> {

    List<CuerpoTecnico> listaCuerpoTecnico = new ArrayList<>();

    @Override
    public void addObject(CuerpoTecnico valor) {
        this.listaCuerpoTecnico.add(valor);
    }

    @Override
    public List<CuerpoTecnico> listar() {
        return this.listaCuerpoTecnico;
    }

    @Override
    public CuerpoTecnico buscarPorId(int id) {
        return null;
    }

    @Override
    public int ultimoId() {
        return this.listaCuerpoTecnico.size() + 1;
    }
}
