package org.laLiga.cuerpoTecnico.adapter.out;

import org.laLiga.cuerpoTecnico.infraestructure.CoachingStaffRepository;
import org.laLiga.cuerpoTecnico.domain.entities.CuerpoTecnico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoachingStaffMySQLRepository implements CoachingStaffRepository {
    private final String url;
    private final String user;
    private final String password;

    public CoachingStaffMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(CuerpoTecnico cuerpoTecnico) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO cuerpoTecnico (nombreCuTecnico, apellidoCuTecnico, rolCuTecnico, edadCuTecnico, idEquipoCuTecnico) VALUES (?,?,?,?,?)";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setString(1, cuerpoTecnico.getNombre());
                stm.setString(2, cuerpoTecnico.getApellido());
                stm.setString(3, cuerpoTecnico.getRol());
                stm.setInt(4, cuerpoTecnico.getEdad());
                stm.setInt(5, cuerpoTecnico.getIdEquipo());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(CuerpoTecnico cuerpoTecnico) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "UPDATE cuerpoTecnico SET nombreCuTecnico = ?, apellidoCuTecnico = ?, rolCuTecnico = ?, edadCuTecnico = ?, idEquipoCuTecnico = ? WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setString(1, cuerpoTecnico.getNombre());
                stm.setString(2, cuerpoTecnico.getApellido());
                stm.setString(3, cuerpoTecnico.getRol());
                stm.setInt(4, cuerpoTecnico.getEdad());
                stm.setInt(5, cuerpoTecnico.getIdEquipo());
                stm.setInt(6, cuerpoTecnico.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<CuerpoTecnico> findById(int id) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM cuerpoTecnico WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        CuerpoTecnico cuerpoTecnico = new CuerpoTecnico(resultSet.getInt("id"), resultSet.getString("nombreCuTecnico"),
                                resultSet.getString("apellidoCuTecnico"), resultSet.getString("rolCuTecnico"),
                                resultSet.getInt("edadCuTecnico"), resultSet.getInt("idEquipoCuTecnico"));
                        return Optional.of(cuerpoTecnico);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<CuerpoTecnico> findAll() {
        List<CuerpoTecnico> listaCuerpoTecnicos = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM cuerpoTecnico";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    CuerpoTecnico cuerpoTecnico = new CuerpoTecnico(resultSet.getInt("id"), resultSet.getString("nombreCuTecnico"),
                            resultSet.getString("apellidoCuTecnico"), resultSet.getString("rolCuTecnico"),
                            resultSet.getInt("edadCuTecnico"), resultSet.getInt("idEquipoCuTecnico"));
                    listaCuerpoTecnicos.add(cuerpoTecnico);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCuerpoTecnicos;
    }

    @Override
    public void delete(int id) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "DELETE FROM cuerpoTecnico WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
