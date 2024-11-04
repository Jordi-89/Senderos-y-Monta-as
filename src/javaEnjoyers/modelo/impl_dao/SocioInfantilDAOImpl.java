// src/javaEnjoyers/modelo/dao/impl/SocioInfantilDAOImpl.java
package javaEnjoyers.modelo.impl_dao;

import javaEnjoyers.modelo.Socio;
import javaEnjoyers.modelo.dao.SocioInfantilDAO;
import javaEnjoyers.modelo.SocioInfantil;
import javaEnjoyers.modelo.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocioInfantilDAOImpl implements SocioInfantilDAO {

    @Override
    public SocioInfantil findByNumeroSocio(String numeroSocio) {
        String query = "SELECT * FROM SocioInfantil WHERE numeroSocio = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, numeroSocio);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SocioInfantil(
                        rs.getString("numeroSocio"),
                        rs.getString("nombre"),
                        rs.getString("numeroSocioAdulto")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Socio> findAll() {
        List<Socio> sociosInfantil = new ArrayList<>();
        String query = "SELECT * FROM SocioInfantil";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                SocioInfantil socioInfantil = new SocioInfantil(
                        rs.getString("numeroSocio"),
                        rs.getString("nombre"),
                        rs.getString("numeroSocioAdulto")
                );
                sociosInfantil.add(socioInfantil);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sociosInfantil;
    }


    @Override
    public void save(Socio socio) {
        if (socio instanceof SocioInfantil) {
            SocioInfantil socioInfantil = (SocioInfantil) socio;
            String query = "INSERT INTO SocioInfantil (numeroSocio, nombre, numeroSocioAdulto) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, socioInfantil.getNumeroSocio());
                stmt.setString(2, socioInfantil.getNombre());
                stmt.setString(3, socioInfantil.getNumeroSocioAdulto());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Socio socio) {
        if (socio instanceof SocioInfantil) {
            SocioInfantil socioInfantil = (SocioInfantil) socio;
            String query = "UPDATE SocioInfantil SET nombre = ?, numeroSocioAdulto = ? WHERE numeroSocio = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, socioInfantil.getNombre());
                stmt.setString(2, socioInfantil.getNumeroSocioAdulto());
                stmt.setString(3, socioInfantil.getNumeroSocio());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String numeroSocio) {
        String query = "DELETE FROM SocioInfantil WHERE numeroSocio = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, numeroSocio);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
