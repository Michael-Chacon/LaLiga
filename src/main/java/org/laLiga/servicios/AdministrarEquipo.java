package org.laLiga.servicios;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import org.laLiga.abstraccion.Crud;
import org.laLiga.modelo.Equipo;
import java.util.ArrayList;
import java.util.List;

public class AdministrarEquipo implements Crud {
    public static final String FILE_PATH = "equipos.json";
    public Gson gson;
    public List<Equipo> datosEquipo;

    public AdministrarEquipo(){
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.datosEquipo = getJson();
    }

    @Override
    public List<Equipo> listar() {
        return datosEquipo;
    }

    @Override
    public void crear(Equipo equipo) {
        this.datosEquipo.add(equipo);
        setJson();
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

    @Override
    public List<Equipo> getJson() {
        try(Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Equipo>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void setJson() {
        try(Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(datosEquipo, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTeam(Equipo objeto) {
        for (int i = 0; i < datosEquipo.size(); i++){
            if (datosEquipo.get(i).getId() == objeto.getId()){
                datosEquipo.set(i, objeto);
                setJson();
                return;
            }
        }
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
        this.updateTeam(ganador);
        this.updateTeam(perdedor);

    }
}
