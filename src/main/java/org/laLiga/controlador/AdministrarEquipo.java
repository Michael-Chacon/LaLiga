package org.laLiga.controlador;
import org.laLiga.Crud;
import org.laLiga.modelo.Equipo;
import java.util.ArrayList;
import java.util.List;

public class AdministrarEquipo implements Crud {
    public List<Equipo> datosEquipo;

    public AdministrarEquipo(){
        this.datosEquipo = new ArrayList<>();
    }

    @Override
    public List<Equipo> listar() {
        return datosEquipo;
    }

    @Override
    public void crear(Equipo equipo) {
        this.datosEquipo.add(equipo);
    }

    @Override
    public Equipo buscarPorId(Integer id) {
        Equipo resultado = null;
        for (Equipo equipo: datosEquipo){
            if (equipo.getId() == id){
                resultado = equipo;
            }
        }
        return resultado;
    }

    public int ultimoid(){
        return datosEquipo.size() + 1;
    }

    public void registraCombate(Equipo ganador, Equipo perdedor, int golesGanador, int golesPerdedor){
        if (golesGanador == golesPerdedor){
            ganador.setPj(ganador.getPj() + 1);
            ganador.setPe(ganador.getPe() + 1);
            ganador.setGf(ganador.getGf() + golesGanador);
            ganador.setGc(ganador.getGc() + golesPerdedor);
            ganador.setTp(ganador.getTp() + 1);

            perdedor.setPj(perdedor.getPj() + 1);
            perdedor.setPe(perdedor.getPe() + 1);
            perdedor.setGf(perdedor.getGf() + golesPerdedor);
            perdedor.setGc(perdedor.getGc() + golesGanador);
            perdedor.setTp(perdedor.getTp() + 1);

        }else {
            ganador.setPj(ganador.getPj() + 1);
            ganador.setPg(ganador.getPg() + 1);
            ganador.setGf(ganador.getGf() + golesGanador);
            ganador.setGc(ganador.getGc() + golesPerdedor);
            ganador.setTp(ganador.getTp() + 3);

            perdedor.setPj(perdedor.getPj() + 1);
            perdedor.setPp(perdedor.getPp() + 1);
            perdedor.setGf(perdedor.getGf() + golesPerdedor);
            perdedor.setGc(perdedor.getGc() + golesGanador);
        }
    }
}
