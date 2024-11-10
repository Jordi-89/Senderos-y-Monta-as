// src/javaEnjoyers/modelo/dao/impl/InscripcionDAOImpl.java
package javaEnjoyers.modelo.impl_dao;

import javaEnjoyers.modelo.*;
import javaEnjoyers.modelo.dao.DAOFactoryProvider;
import javaEnjoyers.modelo.dao.InscripcionDAO;

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
    public List<Inscripcion> findByNumeroSocio(String numeroSocio) {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String query = "SELECT i.codigoInscripcion, i.codigoExcursion, " +
                "e.descripcion, e.fecha, e.numeroDias, e.precioExcursion, " +
                "s.numeroSocio, s.nombre, se.nif, se.tipoSeguro, sg.precioSeguro, " +
                "sf.nif AS nifFederado, sf.codigoFederacion, f.nombre AS nombreFederacion, " +
                "si.numeroSocioAdulto " +
                "FROM inscripcion i " +
                "JOIN excursion e ON i.codigoExcursion = e.codigoExcursion " +
                "JOIN socio s ON i.numeroSocio = s.numeroSocio " +
                "LEFT JOIN socioEstandar se ON s.id = se.id " +
                "LEFT JOIN Seguro sg ON se.tipoSeguro = sg.tipoSeguro " +
                "LEFT JOIN socioFederado sf ON s.id = sf.id " +
                "LEFT JOIN federacion f ON sf.codigoFederacion = f.codigoFederacion " +
                "LEFT JOIN socioInfantil si ON s.id = si.id " +
                "WHERE i.numeroSocio = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, numeroSocio);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Excursion excursion = new Excursion(
                        rs.getString("codigoExcursion"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("numeroDias"),
                        rs.getDouble("precioExcursion")
                );

                Socio socio = null;
                if (rs.getString("tipoSeguro") != null) {
                    Seguro seguro = new Seguro(
                            TipoSeguro.valueOf(rs.getString("tipoSeguro")),
                            rs.getDouble("precioSeguro")
                    );
                    socio = new SocioEstandar(rs.getString("numeroSocio"), rs.getString("nombre"), rs.getString("nif"), seguro);
                } else if (rs.getString("codigoFederacion") != null) {
                    Federacion federacion = new Federacion(rs.getString("codigoFederacion"), rs.getString("nombreFederacion"));
                    socio = new SocioFederado(rs.getString("numeroSocio"), rs.getString("nombre"), rs.getString("nifFederado"), federacion);
                } else if (rs.getString("numeroSocioAdulto") != null) {
                    socio = new SocioInfantil(rs.getString("numeroSocio"), rs.getString("nombre"), rs.getString("numeroSocioAdulto"));
                }

                Inscripcion inscripcion = new Inscripcion(
                        rs.getString("codigoInscripcion"),
                        socio,
                        excursion
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
        String insertInscripcion = "INSERT INTO inscripcion (codigoInscripcion, numeroSocio, codigoExcursion) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertInscripcion)) {

            // Asignar los valores
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
        // Intentar encontrar el socio en el DAO de SocioEstandar
        Socio socio = DAOFactoryProvider.getDAOFactory().getSocioEstandarDAO().findByNumeroSocio(numeroSocio);
        if (socio != null) {
            return socio;
        }

        // Intentar encontrar el socio en el DAO de SocioFederado
        socio = DAOFactoryProvider.getDAOFactory().getSocioFederadoDAO().findByNumeroSocio(numeroSocio);
        if (socio != null) {
            return socio;
        }

        // Intentar encontrar el socio en el DAO de SocioInfantil
        socio = DAOFactoryProvider.getDAOFactory().getSocioInfantilDAO().findByNumeroSocio(numeroSocio);
        return socio; // Puede ser null si no se encuentra el socio
    }


    private Excursion obtenerExcursion(String codigoExcursion) {
        return DAOFactoryProvider.getDAOFactory().getExcursionDAO().findByCodigo(codigoExcursion);
    }


}
