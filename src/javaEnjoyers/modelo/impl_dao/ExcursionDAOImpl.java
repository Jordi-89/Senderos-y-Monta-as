// src/javaEnjoyers/modelo/dao/impl/ExcursionDAOImpl.java
package javaEnjoyers.modelo.impl_dao;

import javaEnjoyers.modelo.dao.ExcursionDAO;
import javaEnjoyers.modelo.Excursion;
import javaEnjoyers.modelo.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExcursionDAOImpl implements ExcursionDAO {

    @Override
    public Excursion findByCodigo(String codigo) {
        String query = "SELECT * FROM Excursion WHERE codigoExcursion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Excursion(
                        rs.getString("codigoExcursion"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("numeroDias"),
                        rs.getDouble("precioExcursion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Excursion> findAll() {
        List<Excursion> excursiones = new ArrayList<>();
        String query = "SELECT * FROM Excursion";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                excursiones.add(new Excursion(
                        rs.getString("codigoExcursion"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("numeroDias"),
                        rs.getDouble("precioExcursion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return excursiones;
    }

    @Override
    public void save(Excursion excursion) {
        String query = "INSERT INTO Excursion (codigoExcursion, descripcion, fecha, numeroDias, precioExcursion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, excursion.getCodigoExcursion());
            stmt.setString(2, excursion.getDescripcion());
            stmt.setDate(3, Date.valueOf(excursion.getFecha()));
            stmt.setInt(4, excursion.getNumeroDias());
            stmt.setDouble(5, excursion.getPrecioExcursion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Excursion excursion) {
        String query = "UPDATE Excursion SET descripcion = ?, fecha = ?, numeroDias = ?, precioExcursion = ? WHERE codigoExcursion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, excursion.getDescripcion());
            stmt.setDate(2, Date.valueOf(excursion.getFecha()));
            stmt.setInt(3, excursion.getNumeroDias());
            stmt.setDouble(4, excursion.getPrecioExcursion());
            stmt.setString(5, excursion.getCodigoExcursion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String codigo) {
        String query = "DELETE FROM Excursion WHERE codigoExcursion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
