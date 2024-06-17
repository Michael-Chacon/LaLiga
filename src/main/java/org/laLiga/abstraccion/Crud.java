package org.laLiga.abstraccion;

import org.laLiga.equipo.domain.entities.Equipo;

import java.util.List;

public interface Crud {
    List<Equipo> listar();
    void crear(Equipo equipo);
    Equipo buscarPorId(Integer id);
    List<Equipo> getJson();
    void setJson();
    void updateTeam(Equipo objeto);

}
