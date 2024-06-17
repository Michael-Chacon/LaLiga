package org.laLiga.equipo.domain.entities;

public class Equipo {
    int id;
    String nombre;
    int pj;
    int pg;
    int pp;
    int pe;
    int gf;
    int gc;
    int tp;
    private static int ultimoId;

    public Equipo() {
        this.id = ++ultimoId;
    }

    public Equipo(int id, String nombre, int pj, int pg, int pp, int pe, int gf, int gc, int tp) {
        this.id = id;
        this.nombre = nombre;
        this.pj = pj;
        this.pg = pg;
        this.pp = pp;
        this.pe = pe;
        this.gf = gf;
        this.gc = gc;
        this.tp = tp;
    }

    public Equipo(String nombre, int pj, int pg, int pp, int pe, int gf, int gc, int tp) {
        this.nombre = nombre;
        this.pj = pj;
        this.pg = pg;
        this.pp = pp;
        this.pe = pe;
        this.gf = gf;
        this.gc = gc;
        this.tp = tp;
    }

    public Equipo(String nombre, int pj) {
        this.nombre = nombre;
        this.pj = pj;
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

    public int getPj() {
        return pj;
    }

    public void setPj(int pj) {
        this.pj = pj;
    }

    public int getPg() {
        return pg;
    }

    public void setPg(int pg) {
        this.pg = pg;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public int getPe() {
        return pe;
    }

    public void setPe(int pe) {
        this.pe = pe;
    }

    public int getGf() {
        return gf;
    }

    public void setGf(int gf) {
        this.gf = gf;
    }

    public int getGc() {
        return gc;
    }

    public void setGc(int gc) {
        this.gc = gc;
    }

    public int getTp() {
        return tp;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }


}
