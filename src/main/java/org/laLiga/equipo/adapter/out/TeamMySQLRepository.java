package org.laLiga.equipo.adapter.out;

import org.laLiga.equipo.domain.entities.Equipo;
import org.laLiga.equipo.infraestructure.TeamRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamMySQLRepository implements TeamRepository {
    private final String url;
    private final String user;
    private final String password;

    public TeamMySQLRepository(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public int save(Equipo equipo) {
        ResultSet getId = null;
        PreparedStatement preparedStatement = null;
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO equipo (nombreEquip, pj, pg, pp, pe, gf, gc, tp) VALUES (?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, equipo.getNombre());
                preparedStatement.setInt(2, equipo.getPj());
                preparedStatement.setInt(3, equipo.getPg());
                preparedStatement.setInt(4, equipo.getPp());
                preparedStatement.setInt(5, equipo.getPe());
                preparedStatement.setInt(6, equipo.getGf());
                preparedStatement.setInt(7, equipo.getGc());
                preparedStatement.setInt(8, equipo.getTp());

                int idEquipo = preparedStatement.executeUpdate();
                if (idEquipo > 0) {
                    getId = preparedStatement.getGeneratedKeys();
                    if (getId.next()) {
                        int equipoId = getId.getInt(1);
                        System.out.println("El id es: " + equipoId);
                        return equipoId;
                    } else {
                        System.out.println("No obtuve ningun id");
                    }
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public void update(Equipo equipo) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "UPDATE equipo SET nombreEQuip = ?, pj = ?, pg = ?, pp = ?, pe = ?, gf = ?, gc = ?, tp = ? WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setString(1, equipo.getNombre());
                stm.setInt(2, equipo.getPj());
                stm.setInt(3, equipo.getPg());
                stm.setInt(4, equipo.getPp());
                stm.setInt(5, equipo.getPe());
                stm.setInt(6, equipo.getGf());
                stm.setInt(7, equipo.getGc());
                stm.setInt(8, equipo.getTp());
                stm.setInt(9, equipo.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Equipo> findById(int id) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM equipo WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Equipo team = new Equipo(resultSet.getInt("id"), resultSet.getString("nombreEquip"),
                                resultSet.getInt("pj"), resultSet.getInt("pg"), resultSet.getInt("pp"),
                                resultSet.getInt("pe"), resultSet.getInt("gf"), resultSet.getInt("gc"), resultSet.getInt("tp"));
                        return Optional.of(team);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Equipo> findAll() {
        List<Equipo> listaEquipos = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM equipo";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Equipo team = new Equipo(resultSet.getInt("id"), resultSet.getString("nombreEquip"),
                            resultSet.getInt("pj"), resultSet.getInt("pg"), resultSet.getInt("pp"),
                            resultSet.getInt("pe"), resultSet.getInt("gf"), resultSet.getInt("gc"),
                            resultSet.getInt("tp"));
                    listaEquipos.add(team);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaEquipos;
    }

    @Override
    public void delete(int id) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "DELETE FROM equipo WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
