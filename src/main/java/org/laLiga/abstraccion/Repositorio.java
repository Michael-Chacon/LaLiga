package org.laLiga.abstraccion;

import java.util.List;

abstract  public class Repositorio<T> {
    abstract public void addObject(T valor);
    abstract public List<T> listar();
    abstract public  T buscarPorId(int id);
    abstract public int ultimoId();
    abstract public List<T> getJson();
    abstract public void setJson();
    abstract public void actualizarObjeto(T objeto);
}
