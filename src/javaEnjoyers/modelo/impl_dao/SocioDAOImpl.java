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
                Federacion federacion = obtenerFederacion(rs.getString("codigo_Federacion"));
                String numeroSocioAdulto = rs.getString("numeroSocioAdulto");

                return SocioFactory.createSocio(tipo, numeroSocio, nombre, nif, seguro, federacion, numeroSocioAdulto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Seguro obtenerSeguro(int seguroId) {
        String query = "SELECT tipo, precio FROM seguro WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, seguroId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                TipoSeguro tipo = TipoSeguro.valueOf(rs.getString("tipo"));
                double precio = rs.getDouble("precio");
                return new Seguro(tipo, precio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Federacion obtenerFederacion(String codigoFederacion) {
        FederacionDAO federacionDAO = new FederacionDAOImpl();
        return federacionDAO.findByCodigo(codigoFederacion);
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
                Federacion federacion = obtenerFederacion(rs.getString("codigo_Federacion"));
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
        String deleteQuery = "DELETE FROM socio WHERE numeroSocio = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {

            stmt.setString(1, numeroSocio);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("** Socio eliminado correctamente **");
            } else {
                System.out.println("No se encontró un socio con el número proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
