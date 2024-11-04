// src/javaEnjoyers/modelo/dao/impl/SocioFederadoDAOImpl.java
package javaEnjoyers.modelo.impl_dao;

import javaEnjoyers.modelo.Socio;
import javaEnjoyers.modelo.dao.SocioFederadoDAO;
import javaEnjoyers.modelo.SocioFederado;
import javaEnjoyers.modelo.DatabaseConnection;
import javaEnjoyers.modelo.Federacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocioFederadoDAOImpl implements SocioFederadoDAO {

    @Override
    public SocioFederado findByNumeroSocio(String numeroSocio) {
        String query = "SELECT * FROM SocioFederado WHERE numeroSocio = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, numeroSocio);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SocioFederado(
                        rs.getString("numeroSocio"),
                        rs.getString("nombre"),
                        rs.getString("nif"),
                        obtenerFederacion(rs.getInt("federacion_id"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Federacion obtenerFederacion(int federacionId) {
        // Implementación para recuperar la federación desde su DAO
        return null;
    }

    @Override
    public List<Socio> findAll() {
        List<Socio> sociosFederado = new ArrayList<>();
        String query = "SELECT * FROM SocioFederado";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                SocioFederado socioFederado = new SocioFederado(
                        rs.getString("numeroSocio"),
                        rs.getString("nombre"),
                        rs.getString("nif"),
                        obtenerFederacion(rs.getInt("federacion_id"))
                );
                sociosFederado.add(socioFederado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sociosFederado;
    }

    @Override
    public void save(Socio socio) {
        if (socio instanceof SocioFederado) {
            SocioFederado socioFederado = (SocioFederado) socio;
            String query = "INSERT INTO SocioFederado (numeroSocio, nombre, nif, codigoFederacion) VALUES (?, ?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, socioFederado.getNumeroSocio());
                stmt.setString(2, socioFederado.getNombre());
                stmt.setString(3, socioFederado.getNif());
                stmt.setString(4, socioFederado.getFederacion().getCodigoFederacion());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Socio socio) {
        if (socio instanceof SocioFederado) {
            SocioFederado socioFederado = (SocioFederado) socio;
            String query = "UPDATE SocioFederado SET nombre = ?, nif = ?, codigoFederacion = ? WHERE numeroSocio = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, socioFederado.getNombre());
                stmt.setString(2, socioFederado.getNif());
                stmt.setString(3, socioFederado.getFederacion().getCodigoFederacion()); // Usamos codigoFederacion
                stmt.setString(4, socioFederado.getNumeroSocio());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String numeroSocio) {
        String query = "DELETE FROM SocioFederado WHERE numeroSocio = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, numeroSocio);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
