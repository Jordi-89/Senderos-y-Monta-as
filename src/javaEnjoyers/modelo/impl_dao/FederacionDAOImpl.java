// src/javaEnjoyers/modelo/dao/impl/FederacionDAOImpl.java
package javaEnjoyers.modelo.impl_dao;

import javaEnjoyers.modelo.dao.FederacionDAO;
import javaEnjoyers.modelo.Federacion;
import javaEnjoyers.modelo.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FederacionDAOImpl implements FederacionDAO {

    @Override
    public Federacion findByCodigo(String codigoFederacion) {
        String query = "SELECT codigoFederacion, nombre FROM federacion WHERE codigoFederacion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, codigoFederacion);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nombreFederacion = rs.getString("nombre");
                return new Federacion(codigoFederacion, nombreFederacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Federacion federacion) {
        String query = "INSERT INTO federacion (codigoFederacion, nombre) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, federacion.getCodigoFederacion());
            stmt.setString(2, federacion.getNombre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Federacion> findAll() {
        List<Federacion> federaciones = new ArrayList<>();
        String query = "SELECT * FROM Federacion";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Federacion federacion = new Federacion(
                        rs.getString("codigoFederacion"),
                        rs.getString("nombre")
                );
                federaciones.add(federacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return federaciones;
    }

}
