package org.laLiga.servicios;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import org.laLiga.abstraccion.Repositorio;
import org.laLiga.modelo.CuerpoTecnico;

import java.util.ArrayList;
import java.util.List;

public class CuTecnicoRepositorio extends Repositorio<CuerpoTecnico> {
    private static final String FILE_PATH = "db/cuerpoTecnico.json";
    private Gson gson;
    List<CuerpoTecnico> listaCuerpoTecnico;
    public CuTecnicoRepositorio() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        listaCuerpoTecnico = getJson();
    }

    @Override
    public void addObject(CuerpoTecnico valor) {
        this.listaCuerpoTecnico.add(valor);
        setJson();
    }

    @Override
    public List<CuerpoTecnico> listar() {
        return new ArrayList<>(listaCuerpoTecnico);
    }

    @Override
    public CuerpoTecnico buscarPorId(int id) {
        return null;
    }

    @Override
    public int ultimoId() {
        return this.listaCuerpoTecnico.size() + 1;
    }

    @Override
    public List<CuerpoTecnico> getJson() {
        try(Reader reader = new FileReader(FILE_PATH)){
            Type listType = new TypeToken<ArrayList<CuerpoTecnico>>() {}.getType();
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
            gson.toJson(listaCuerpoTecnico, write);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarObjeto(CuerpoTecnico objeto) {
        for (int i = 0; i < listaCuerpoTecnico.size(); i++){
            if (listaCuerpoTecnico.get(i).getId() == objeto.getId()){
                listaCuerpoTecnico.set(i, objeto);
                setJson();
                return;
            }
        }
    }
}
