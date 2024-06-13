package org.laLiga.abstraccion;

import org.laLiga.modelo.Equipo;

import java.util.List;

public interface Crud {
    List<Equipo> listar();
    void crear(Equipo equipo);
    Equipo buscarPorId(Integer id);
    List<Equipo> getJson();
    void setJson();
    void updateTeam();

}
