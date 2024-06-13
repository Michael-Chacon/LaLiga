package org.laLiga.servicios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import org.laLiga.abstraccion.Repositorio;
import org.laLiga.modelo.Jugador;
import java.util.ArrayList;
import java.util.List;

public class JugadorRepositorio extends Repositorio<Jugador> {
    private static final String FILE_PATH = "personas.json";
    private Gson gson;
    List<Jugador> listaJugadores;

    public JugadorRepositorio() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        listaJugadores = getJson();
    }

    @Override
    public void addObject(Jugador valor) {
        this.listaJugadores.add(valor);
        setJson();
    }

    @Override
    public List<Jugador> listar() {
        return new ArrayList<>(listaJugadores);
    }

    @Override
    public Jugador buscarPorId(int id) {
        Jugador resultado = null;
        for (Jugador jugador: listaJugadores){
            if (jugador.getId() == id){
                resultado = jugador;
            }
        }
        return resultado;
    }

    @Override
    public int ultimoId() {
        return this.listaJugadores.size() + 1;
    }

    @Override
    public List<Jugador> getJson() {
        try(Reader reader = new FileReader(FILE_PATH)){
            Type listType = new TypeToken<ArrayList<Jugador>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            return new ArrayList<>(); // Si el archivo no existe retorna una al vacio
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void setJson() {
        try(Writer write = new FileWriter(FILE_PATH)){
            gson.toJson(listaJugadores, write);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarObjeto(Jugador objeto) {
        for (int i = 0; i < listaJugadores.size(); i++){
            if (listaJugadores.get(i).getId() == objeto.getId()){
                listaJugadores.set(i, objeto);
                setJson();
                return;
            }
        }
    }
}
