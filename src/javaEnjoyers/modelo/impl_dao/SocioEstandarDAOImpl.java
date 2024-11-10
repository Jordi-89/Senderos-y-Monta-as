// src/javaEnjoyers/modelo/dao/impl/SocioEstandarDAOImpl.java
package javaEnjoyers.modelo.impl_dao;

import javaEnjoyers.modelo.*;
import javaEnjoyers.modelo.dao.SocioEstandarDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocioEstandarDAOImpl implements SocioEstandarDAO {

    @Override
    public SocioEstandar findByNumeroSocio(String numeroSocio) {
        String query = "SELECT s.numeroSocio, s.nombre, se.nif, sg.tipoSeguro, sg.precioSeguro " +
                "FROM socio s " +
                "JOIN socioEstandar se ON s.id = se.id " +
                "JOIN seguro sg ON se.tipoSeguro = sg.tipoSeguro " +
                "WHERE s.numeroSocio = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, numeroSocio);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String nif = rs.getString("nif");
                TipoSeguro tipoSeguro = TipoSeguro.valueOf(rs.getString("tipoSeguro"));
                double precioSeguro = rs.getDouble("precioSeguro");

                // Crear una instancia de Seguro basada en la información recuperada
                Seguro seguro = new Seguro(tipoSeguro, precioSeguro);
                return new SocioEstandar(numeroSocio, nombre, nif, seguro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Socio> findAll() {
        List<Socio> sociosEstandar = new ArrayList<>();
        String query = "SELECT s.numeroSocio, s.nombre, se.nif, sg.tipoSeguro, sg.precioSeguro " +
                "FROM socio s " +
                "JOIN socioEstandar se ON s.id = se.id " +
                "JOIN seguro sg ON se.tipoSeguro = sg.tipoSeguro";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String numeroSocio = rs.getString("numeroSocio");
                String nombre = rs.getString("nombre");
                String nif = rs.getString("nif");
                TipoSeguro tipoSeguro = TipoSeguro.valueOf(rs.getString("tipoSeguro"));
                double precioSeguro = rs.getDouble("precioSeguro");

                // Crear una instancia de Seguro basada en la información recuperada
                Seguro seguro = new Seguro(tipoSeguro, precioSeguro);
                SocioEstandar socioEstandar = new SocioEstandar(numeroSocio, nombre, nif, seguro);

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

            // Primero guarda los datos en la tabla 'socio'
            String insertSocio = "INSERT INTO socio (numeroSocio, nombre) VALUES (?, ?)";
            String insertSocioEstandar = "INSERT INTO socioEstandar (id, nif, tipoSeguro) VALUES (?, ?, ?)";

            try (Connection conn = DatabaseConnection.getConnection()) {
                conn.setAutoCommit(false);  // Iniciar una transacción

                // Insertar en la tabla socio y obtener el ID generado
                try (PreparedStatement stmtSocio = conn.prepareStatement(insertSocio, Statement.RETURN_GENERATED_KEYS)) {
                    stmtSocio.setString(1, socioEstandar.getNumeroSocio());
                    stmtSocio.setString(2, socioEstandar.getNombre());
                    stmtSocio.executeUpdate();

                    ResultSet rs = stmtSocio.getGeneratedKeys();
                    if (rs.next()) {
                        int socioId = rs.getInt(1);  // Obtener el ID generado

                        // Insertar en la tabla socioEstandar usando el ID obtenido
                        try (PreparedStatement stmtSocioEstandar = conn.prepareStatement(insertSocioEstandar)) {
                            stmtSocioEstandar.setInt(1, socioId);  // Usar socioId aquí
                            stmtSocioEstandar.setString(2, socioEstandar.getNif());
                            stmtSocioEstandar.setString(3, socioEstandar.getSeguro().getTipoSeguro().name()); // Usamos tipoSeguro
                            stmtSocioEstandar.executeUpdate();
                        }
                    }
                }
                conn.commit();  // Confirmar la transacción
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Socio socio) {
        if (socio instanceof SocioEstandar) {
            SocioEstandar socioEstandar = (SocioEstandar) socio;
            String query = "UPDATE socioEstandar SET nif = ?, tipoSeguro = ? WHERE id = (SELECT id FROM socio WHERE numeroSocio = ?)";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, socioEstandar.getNif());
                stmt.setString(2, socioEstandar.getSeguro().getTipoSeguro().name()); // Usamos tipoSeguro
                stmt.setString(3, socioEstandar.getNumeroSocio());
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
                System.out.println("** Socio estándar eliminado correctamente **");
            } else {
                System.out.println("No se encontró un socio estándar con el número proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
