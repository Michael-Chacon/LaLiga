package org.laLiga.controlador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import org.laLiga.abstraccion.Repositorio;
import org.laLiga.modelo.CuerpoMedico;
import java.util.ArrayList;
import java.util.List;

public class CuMedicoRepositorio extends Repositorio<CuerpoMedico> {
    private static final String FILE_PATH = "cuerpoMedico.json";
    private List<CuerpoMedico> listaCuerpoMedico;
    private Gson gson;

    public CuMedicoRepositorio() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        listaCuerpoMedico = getJson();
    }

    @Override
    public void addObject(CuerpoMedico valor) {
        this.listaCuerpoMedico.add(valor);
        setJson();
    }

    @Override
    public List<CuerpoMedico> listar() {
        return new ArrayList<>(listaCuerpoMedico);
    }

    @Override
    public CuerpoMedico buscarPorId(int id) {
        return null;
    }

    @Override
    public int ultimoId() {
        return this.listaCuerpoMedico.size() + 1;
    }

    @Override
    public List<CuerpoMedico> getJson() {
        try(Reader reader = new FileReader(FILE_PATH)){
            Type listType = new TypeToken<ArrayList<CuerpoMedico>>() {}.getType();
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
            gson.toJson(listaCuerpoMedico, write);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarObjeto(CuerpoMedico objeto) {
        for (int i = 0; i < listaCuerpoMedico.size(); i++){
            if (listaCuerpoMedico.get(i).getId() == objeto.getId()){
                listaCuerpoMedico.set(i, objeto);
                setJson();
                return;
            }
        }
    }
}
