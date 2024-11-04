// src/javaEnjoyers/modelo/dao/impl/FederacionDAOImpl.java
package javaEnjoyers.modelo.impl_dao;

import javaEnjoyers.modelo.dao.FederacionDAO;
import javaEnjoyers.modelo.Federacion;
import javaEnjoyers.modelo.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FederacionDAOImpl implements FederacionDAO {

    @Override
    public Federacion findByCodigo(String codigoFederacion) {
        String query = "SELECT * FROM Federacion WHERE codigoFederacion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, codigoFederacion);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Federacion(
                        rs.getString("codigoFederacion"),
                        rs.getString("nombre")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
