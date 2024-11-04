// src/javaEnjoyers/modelo/dao/impl/SocioEstandarDAOImpl.java
package javaEnjoyers.modelo.impl_dao;

import javaEnjoyers.modelo.Socio;
import javaEnjoyers.modelo.dao.SocioEstandarDAO;
import javaEnjoyers.modelo.SocioEstandar;
import javaEnjoyers.modelo.DatabaseConnection;
import javaEnjoyers.modelo.Seguro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocioEstandarDAOImpl implements SocioEstandarDAO {

    @Override
    public SocioEstandar findByNumeroSocio(String numeroSocio) {
        String query = "SELECT * FROM SocioEstandar WHERE numeroSocio = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, numeroSocio);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SocioEstandar(
                        rs.getString("numeroSocio"),
                        rs.getString("nombre"),
                        rs.getString("nif"),
                        obtenerSeguro(rs.getInt("seguro_id"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Seguro obtenerSeguro(int seguroId) {
        // Lógica para recuperar un Seguro por ID
        return null; // Debes implementar el método para recuperar el seguro desde su DAO
    }

    @Override
    public List<Socio> findAll() {
        List<Socio> sociosEstandar = new ArrayList<>();
        String query = "SELECT * FROM SocioEstandar";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                SocioEstandar socioEstandar = new SocioEstandar(
                        rs.getString("numeroSocio"),
                        rs.getString("nombre"),
                        rs.getString("nif"),
                        obtenerSeguro(rs.getInt("seguro_id"))
                );
                sociosEstandar.add(socioEstandar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sociosEstandar;
    }


    @Override
    public void save(Socio socio) {
        if (socio instanceof SocioEstandar) {
            SocioEstandar socioEstandar = (SocioEstandar) socio;
            String query = "INSERT INTO SocioEstandar (numeroSocio, nombre, nif, tipoSeguro, precioSeguro) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, socioEstandar.getNumeroSocio());
                stmt.setString(2, socioEstandar.getNombre());
                stmt.setString(3, socioEstandar.getNif());
                stmt.setString(4, socioEstandar.getSeguro().getTipoSeguro().name());
                stmt.setDouble(5, socioEstandar.getSeguro().getPrecioSeguro());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Socio socio) {
        if (socio instanceof SocioEstandar) {
            SocioEstandar socioEstandar = (SocioEstandar) socio;
            String query = "UPDATE SocioEstandar SET nombre = ?, nif = ?, tipoSeguro = ?, precioSeguro = ? WHERE numeroSocio = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, socioEstandar.getNombre());
                stmt.setString(2, socioEstandar.getNif());
                stmt.setString(3, socioEstandar.getSeguro().getTipoSeguro().name()); // Usamos tipoSeguro
                stmt.setDouble(4, socioEstandar.getSeguro().getPrecioSeguro()); // Usamos precioSeguro
                stmt.setString(5, socioEstandar.getNumeroSocio());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String numeroSocio) {
        String query = "DELETE FROM SocioEstandar WHERE numeroSocio = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, numeroSocio);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
