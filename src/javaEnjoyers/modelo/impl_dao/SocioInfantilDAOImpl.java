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
        String query = "SELECT s.numeroSocio, s.nombre, si.numeroSocioAdulto " +
                "FROM socio s " +
                "JOIN socioInfantil si ON s.id = si.id " +
                "WHERE s.numeroSocio = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, numeroSocio);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String numeroSocioAdulto = rs.getString("numeroSocioAdulto");

                return new SocioInfantil(numeroSocio, nombre, numeroSocioAdulto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Socio> findAll() {
        List<Socio> sociosInfantil = new ArrayList<>();
        String query = "SELECT s.numeroSocio, s.nombre, si.numeroSocioAdulto " +
                "FROM socio s " +
                "JOIN socioInfantil si ON s.id = si.id"; // Relación basada en 'id'

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

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

            String insertSocio = "INSERT INTO socio (numeroSocio, nombre) VALUES (?, ?)";
            String insertSocioInfantil = "INSERT INTO socioInfantil (id, numeroSocioAdulto) VALUES (?, ?)";

            try (Connection conn = DatabaseConnection.getConnection()) {
                // Insertar en la tabla socio
                try (PreparedStatement stmtSocio = conn.prepareStatement(insertSocio, Statement.RETURN_GENERATED_KEYS)) {
                    stmtSocio.setString(1, socioInfantil.getNumeroSocio());
                    stmtSocio.setString(2, socioInfantil.getNombre());
                    stmtSocio.executeUpdate();

                    // Obtener el ID generado
                    ResultSet generatedKeys = stmtSocio.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int socioId = generatedKeys.getInt(1);

                        // Insertar en la tabla socioInfantil usando el ID obtenido
                        try (PreparedStatement stmtSocioInfantil = conn.prepareStatement(insertSocioInfantil)) {
                            stmtSocioInfantil.setInt(1, socioId);
                            stmtSocioInfantil.setString(2, socioInfantil.getNumeroSocioAdulto());
                            stmtSocioInfantil.executeUpdate();
                        }
                    }
                }
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
        String deleteQuery = "DELETE FROM socio WHERE numeroSocio = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {

            stmt.setString(1, numeroSocio);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("** Socio infantil eliminado correctamente **");
            } else {
                System.out.println("No se encontró un socio infantil con el número proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
