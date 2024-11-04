// src/javaEnjoyers/modelo/dao/impl/SocioDAOImpl.java
package javaEnjoyers.modelo.impl_dao;

import javaEnjoyers.modelo.*;
import javaEnjoyers.modelo.dao.FederacionDAO;
import javaEnjoyers.modelo.dao.SeguroDAO;
import javaEnjoyers.modelo.dao.SocioDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocioDAOImpl implements SocioDAO {

    @Override
    public Socio findByNumeroSocio(String numeroSocio) {
        String query = "SELECT * FROM Socio WHERE numeroSocio = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, numeroSocio);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String tipo = rs.getString("tipo");
                String nombre = rs.getString("nombre");
                String nif = rs.getString("nif");
                Seguro seguro = obtenerSeguro(rs.getInt("seguro_id"));
                Federacion federacion = obtenerFederacion(rs.getInt("federacion_id"));
                String numeroSocioAdulto = rs.getString("numeroSocioAdulto");

                return SocioFactory.createSocio(tipo, numeroSocio, nombre, nif, seguro, federacion, numeroSocioAdulto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Seguro obtenerSeguro(int seguroId) {
        SeguroDAO seguroDAO = new SeguroDAOImpl();
        return seguroDAO.findById(seguroId);
    }

    private Federacion obtenerFederacion(int federacionId) {
        FederacionDAO federacionDAO = new FederacionDAOImpl();
        return federacionDAO.findById(federacionId);
    }

    @Override
    public List<Socio> findAll() {
        List<Socio> socios = new ArrayList<>();
        String query = "SELECT * FROM Socio";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String tipo = rs.getString("tipo");
                String numeroSocio = rs.getString("numeroSocio");
                String nombre = rs.getString("nombre");
                String nif = rs.getString("nif");
                Seguro seguro = obtenerSeguro(rs.getInt("seguro_id"));
                Federacion federacion = obtenerFederacion(rs.getInt("federacion_id"));
                String numeroSocioAdulto = rs.getString("numeroSocioAdulto");

                Socio socio = SocioFactory.createSocio(tipo, numeroSocio, nombre, nif, seguro, federacion, numeroSocioAdulto);
                socios.add(socio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return socios;
    }

    @Override
    public void save(Socio socio) {
        String query = "INSERT INTO Socio (numeroSocio, nombre) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, socio.getNumeroSocio());
            stmt.setString(2, socio.getNombre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Socio socio) {
        String query = "UPDATE Socio SET nombre = ? WHERE numeroSocio = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, socio.getNombre());
            stmt.setString(2, socio.getNumeroSocio());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String numeroSocio) {
        String query = "DELETE FROM Socio WHERE numeroSocio = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, numeroSocio);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
