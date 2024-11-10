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
        String query = "SELECT s.numeroSocio, s.nombre AS nombreSocio, sf.nif, f.codigoFederacion, f.nombre AS nombreFederacion " +
                "FROM socio s " +
                "JOIN socioFederado sf ON s.id = sf.id " +
                "JOIN federacion f ON sf.codigoFederacion = f.codigoFederacion " +
                "WHERE s.numeroSocio = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, numeroSocio);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombreSocio = rs.getString("nombreSocio");
                String nif = rs.getString("nif");
                String codigoFederacion = rs.getString("codigoFederacion");
                String nombreFederacion = rs.getString("nombreFederacion");

                Federacion federacion = new Federacion(codigoFederacion, nombreFederacion);
                return new SocioFederado(numeroSocio, nombreSocio, nif, federacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Socio> findAll() {
        List<Socio> sociosFederado = new ArrayList<>();
        String query = "SELECT s.numeroSocio, s.nombre, sf.nif, sf.codigoFederacion, f.nombre AS nombreFederacion " +
                "FROM socio s " +
                "JOIN socioFederado sf ON s.id = sf.id " +
                "JOIN federacion f ON sf.codigoFederacion = f.codigoFederacion";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String numeroSocio = rs.getString("numeroSocio");
                String nombre = rs.getString("nombre");
                String nif = rs.getString("nif");
                String codigoFederacion = rs.getString("codigoFederacion");
                String nombreFederacion = rs.getString("nombre");

                // Crear la federación a partir del código y nombre
                Federacion federacion = new Federacion(codigoFederacion, nombreFederacion);

                // Crear el socio federado
                SocioFederado socioFederado = new SocioFederado(numeroSocio, nombre, nif, federacion);

                sociosFederado.add(socioFederado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sociosFederado;
    }

    public void save(Socio socio) {
        if (socio instanceof SocioFederado) {
            SocioFederado socioFederado = (SocioFederado) socio;

            String insertSocio = "INSERT INTO socio (numeroSocio, nombre) VALUES (?, ?)";
            String insertSocioFederado = "INSERT INTO socioFederado (id, nif, codigoFederacion) VALUES (?, ?, ?)";

            Connection conn = null;
            try {
                conn = DatabaseConnection.getConnection();
                conn.setAutoCommit(false);

                // Verificar si el numeroSocio ya existe
                if (findByNumeroSocio(socioFederado.getNumeroSocio()) != null) {
                    throw new SQLException("Error: El número de socio ya existe en la base de datos.");
                }

                // Verificar si el codigoFederacion existe en la tabla federacion
                if (findFederacionByCodigo(socioFederado.getFederacion().getCodigoFederacion()) == null) {
                    throw new SQLException("Error: El código de federación no existe.");
                }

                // Insertar en la tabla socio
                try (PreparedStatement stmtSocio = conn.prepareStatement(insertSocio, Statement.RETURN_GENERATED_KEYS)) {
                    stmtSocio.setString(1, socioFederado.getNumeroSocio());
                    stmtSocio.setString(2, socioFederado.getNombre());
                    stmtSocio.executeUpdate();

                    ResultSet rs = stmtSocio.getGeneratedKeys();
                    if (rs.next()) {
                        int socioId = rs.getInt(1);

                        // Insertar en socioFederado usando codigoFederacion
                        try (PreparedStatement stmtSocioFederado = conn.prepareStatement(insertSocioFederado)) {
                            stmtSocioFederado.setInt(1, socioId);
                            stmtSocioFederado.setString(2, socioFederado.getNif());
                            stmtSocioFederado.setString(3, socioFederado.getFederacion().getCodigoFederacion());
                            stmtSocioFederado.executeUpdate();
                        }
                    }
                }

                conn.commit();
            } catch (SQLException e) {
                if (conn != null) {
                    try {
                        conn.rollback(); // Revertir cambios si ocurre un error
                    } catch (SQLException rollbackEx) {
                        rollbackEx.printStackTrace();
                    }
                }
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close(); // Cerrar la conexión manualmente
                    } catch (SQLException closeEx) {
                        closeEx.printStackTrace();
                    }
                }
            }
        }
    }

    public Federacion findFederacionByCodigo(String codigoFederacion) {
        String query = "SELECT codigoFederacion, nombre FROM federacion WHERE codigoFederacion = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, codigoFederacion);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                return new Federacion(codigoFederacion, nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Retornar null si no se encuentra ninguna federación
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
        String deleteQuery = "DELETE FROM socio WHERE numeroSocio = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {

            stmt.setString(1, numeroSocio);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("** Socio federado eliminado correctamente **");
            } else {
                System.out.println("No se encontró un socio federado con el número proporcionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
