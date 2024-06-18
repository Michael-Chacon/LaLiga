package org.laLiga.jugador.adapter.out;

import org.laLiga.jugador.domain.entities.Jugador;
import org.laLiga.jugador.infraestructure.PlayerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerMySQLRepository implements PlayerRepository {
    private final String url;
    private final String user;
    private final String password;

    public PlayerMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Jugador jugador) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "INSERT INTO jugador (nombre, apellido, edad, idEquipo, dorsal, posicionJuego, nacionalidad, fechaIngreso) VALUES (?,?,?,?,?,?,?,?)";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setString(1, jugador.getNombre());
                stm.setString(2, jugador.getApellido());
                stm.setInt(3, jugador.getEdad());
                stm.setInt(4, jugador.getIdEquipo());
                stm.setInt(5, jugador.getDorsal());
                stm.setString(6, jugador.getPosicionJuego());
                stm.setString(7, jugador.getNacionalidad());
                stm.setString(8, jugador.getFechaIngreso());

                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Jugador jugador) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "UPDATE jugador SET nombre = ?, apellido = ?, edad = ?, idEquipo = ?, dorsal = ?, posicionJuego = ?, nacionalidad = ?, fechaIngreso = ? WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setString(1, jugador.getNombre());
                stm.setString(2, jugador.getApellido());
                stm.setInt(3, jugador.getEdad());
                stm.setInt(4, jugador.getIdEquipo());
                stm.setInt(5, jugador.getDorsal());
                stm.setString(6, jugador.getPosicionJuego());
                stm.setString(7, jugador.getNacionalidad());
                stm.setString(8, jugador.getFechaIngreso());
                stm.setInt(9, jugador.getId());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Jugador> findById(int id) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM jugador WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setInt(1, id);
                try(ResultSet resultSet = stm.executeQuery()){
                    if (resultSet.next()){
                        Jugador player = new Jugador(resultSet.getInt("id"), resultSet.getString("nombreJugador"),
                                resultSet.getString("apellidoJugador"), resultSet.getInt("edadJugador"), resultSet.getInt("idEquipo"),
                                resultSet.getInt("dorsal"), resultSet.getString("posicion"),
                                resultSet.getString("nacionalidad"), resultSet.getString("fechaIngreso"),
                                resultSet.getInt("golesAnotados"), resultSet.getInt("tarjetasRojas"),
                                resultSet.getInt("tarjetasAmarillas"));
                        return Optional.of(player);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Jugador> findAll() {
        List<Jugador> listaJugadores = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "SELECT * FROM jugador";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()){
                    Jugador player = new Jugador(resultSet.getInt("id"), resultSet.getString("nombreJugador"),
                            resultSet.getString("apellidoJugador"), resultSet.getInt("edadJugador"), resultSet.getInt("idEquipo"),
                            resultSet.getInt("dorsal"), resultSet.getString("posicion"),
                            resultSet.getString("nacionalidad"), resultSet.getString("fechaIngreso"),
                            resultSet.getInt("golesAnotados"), resultSet.getInt("tarjetasRojas"),
                            resultSet.getInt("tarjetasAmarillas"));
                    listaJugadores.add(player);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaJugadores;
    }

    @Override
    public void delete(int id) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String query = "DELETE FROM jugador WHERE id = ?";
            try(PreparedStatement stm = connection.prepareStatement(query)){
                stm.setInt(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
