package org.laLiga.cuerpoMedico.adapter.out;

import org.laLiga.cuerpoMedico.infraestructure.MedicalStaffRepository;
import org.laLiga.cuerpoMedico.domain.entities.CuerpoMedico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicalStaffMySQLRepository implements MedicalStaffRepository {

    private final String url;
    private final String user;
    private final String password;

    public MedicalStaffMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(CuerpoMedico cuerpoMedico) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO cuerpoMedico (nombreCuMedico, apellidoCuMedico, rolCuMedico, edadCuMedico, idEquipoCuMedico) VALUES (?,?,?,?,?)";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setString(1, cuerpoMedico.getNombre());
                stm.setString(2, cuerpoMedico.getApellido());
                stm.setString(3, cuerpoMedico.getRol());
                stm.setInt(4, cuerpoMedico.getEdad());
                stm.setInt(5, cuerpoMedico.getIdEquipo());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(CuerpoMedico cuerpoMedico) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "UPDATE cuerpoMedico SET nombreCuMedico = ?, apellidoCuMedico = ?, rolCuMedico = ?, edadCuMedico = ?, idEquipoCuMedico = ? WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setString(1, cuerpoMedico.getNombre());
                stm.setString(2, cuerpoMedico.getApellido());
                stm.setString(3, cuerpoMedico.getRol());
                stm.setInt(4, cuerpoMedico.getEdad());
                stm.setInt(5, cuerpoMedico.getIdEquipo());
                stm.setInt(6, cuerpoMedico.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<CuerpoMedico> findById(int id) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM cuerpoMedico WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        CuerpoMedico cuerpoMedico = new CuerpoMedico(resultSet.getInt("id"), resultSet.getString("nombreCuMedico"),
                                resultSet.getString("apellidoCuMedico"), resultSet.getString("rolCuMedico"),
                                resultSet.getInt("edadCuMedico"), resultSet.getInt("idEquipoCuMedico"));
                        return Optional.of(cuerpoMedico);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<CuerpoMedico> findAll() {
        List<CuerpoMedico> listaCuerpoMedicos = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM cuerpoMedico";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    CuerpoMedico cuerpoMedico = new CuerpoMedico(resultSet.getInt("id"), resultSet.getString("nombreCuMedico"),
                            resultSet.getString("apellidoCuMedico"), resultSet.getString("rolCuMedico"),
                            resultSet.getInt("edadCuMedico"), resultSet.getInt("idEquipoCuMedico"));
                    listaCuerpoMedicos.add(cuerpoMedico);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCuerpoMedicos;
    }

    @Override
    public void delete(int id) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "DELETE FROM cuerpoMedico WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
