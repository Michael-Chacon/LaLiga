package org.laLiga.abstraccion;

import java.util.List;

abstract public class Plantel<T>{
    int id;
    String nombre;
    String apellidos;
    String rol;
    int edad;
    int idEquipo;

    public Plantel() {
    }

    public Plantel(int id, String nombre, String apellidos, String rol, int edad, int idEquipo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.rol = rol;
        this.edad = edad;
        this.idEquipo = idEquipo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    abstract public void addObject(T valor);
    abstract public List<T> listar();
    abstract public <T> T buscarPorId(int id);
}
