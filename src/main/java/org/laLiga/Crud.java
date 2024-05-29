package org.laLiga;

import org.laLiga.modelo.Equipo;

import java.util.List;

public interface Crud {
    List<Equipo> listar();
    void crear(Equipo equipo);
    Equipo buscarPorId(Integer id);

}
