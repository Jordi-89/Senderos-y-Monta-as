// src/javaEnjoyers/modelo/dao/impl/InscripcionDAOImpl.java
package javaEnjoyers.modelo.impl_dao;

import javaEnjoyers.modelo.dao.DAOFactoryProvider;
import javaEnjoyers.modelo.dao.InscripcionDAO;
import javaEnjoyers.modelo.Inscripcion;
import javaEnjoyers.modelo.Socio;
import javaEnjoyers.modelo.Excursion;
import javaEnjoyers.modelo.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class InscripcionDAOImpl implements InscripcionDAO {

    @Override
    public Inscripcion findByCodigo(String codigoInscripcion) {
        String query = "SELECT * FROM Inscripcion WHERE codigoInscripcion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, codigoInscripcion);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Socio socio = obtenerSocio(rs.getString("numeroSocio"));
                Excursion excursion = obtenerExcursion(rs.getString("codigoExcursion"));
                return new Inscripcion(codigoInscripcion, socio, excursion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Inscripcion> findAll() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String query = "SELECT * FROM Inscripcion";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Socio socio = obtenerSocio(rs.getString("numeroSocio"));
                Excursion excursion = obtenerExcursion(rs.getString("codigoExcursion"));
                inscripciones.add(new Inscripcion(rs.getString("codigoInscripcion"), socio, excursion));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscripciones;
    }

    @Override
    public List<Inscripcion> findBySocio(String numeroSocio) {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String query = "SELECT * FROM Inscripcion WHERE numeroSocio = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, numeroSocio);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion(
                        rs.getString("codigoInscripcion"),
                        // Necesitarás métodos para obtener el socio y excursión completos por número de socio o código de excursión
                        obtenerSocio(rs.getString("numeroSocio")),
                        obtenerExcursion(rs.getString("codigoExcursion"))
                );
                inscripciones.add(inscripcion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscripciones;
    }

    @Override
    public List<Inscripcion> findByExcursion(String codigoExcursion) {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String query = "SELECT * FROM Inscripcion WHERE codigoExcursion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, codigoExcursion);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion(
                        rs.getString("codigoInscripcion"),
                        obtenerSocio(rs.getString("numeroSocio")),
                        obtenerExcursion(rs.getString("codigoExcursion"))
                );
                inscripciones.add(inscripcion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscripciones;
    }

    public List<Inscripcion> findByFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String query = "SELECT * FROM Inscripcion WHERE fecha >= ? AND fecha <= ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(fechaInicio));
            stmt.setDate(2, Date.valueOf(fechaFin));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion(
                        rs.getString("codigoInscripcion"),
                        obtenerSocio(rs.getString("numeroSocio")),
                        obtenerExcursion(rs.getString("codigoExcursion"))
                );
                inscripciones.add(inscripcion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscripciones;
    }

    @Override
    public void save(Inscripcion inscripcion) {
        String query = "INSERT INTO Inscripcion (codigoInscripcion, numeroSocio, codigoExcursion) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, inscripcion.getCodigoInscripcion());
            stmt.setString(2, inscripcion.getSocio().getNumeroSocio());
            stmt.setString(3, inscripcion.getExcursion().getCodigoExcursion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Inscripcion inscripcion) {
        String query = "UPDATE Inscripcion SET numeroSocio = ?, codigoExcursion = ? WHERE codigoInscripcion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, inscripcion.getSocio().getNumeroSocio());
            stmt.setString(2, inscripcion.getExcursion().getCodigoExcursion());
            stmt.setString(3, inscripcion.getCodigoInscripcion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String codigoInscripcion) {
        String query = "DELETE FROM Inscripcion WHERE codigoInscripcion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, codigoInscripcion);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Socio obtenerSocio(String numeroSocio) {
        return DAOFactoryProvider.getDAOFactory().getSocioEstandarDAO().findByNumeroSocio(numeroSocio);
    }

    private Excursion obtenerExcursion(String codigoExcursion) {
        return DAOFactoryProvider.getDAOFactory().getExcursionDAO().findByCodigo(codigoExcursion);
    }


}
