// src/javaEnjoyers/modelo/dao/impl/SeguroDAOImpl.java
package javaEnjoyers.modelo.impl_dao;

import javaEnjoyers.modelo.TipoSeguro;
import javaEnjoyers.modelo.dao.SeguroDAO;
import javaEnjoyers.modelo.Seguro;
import javaEnjoyers.modelo.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeguroDAOImpl implements SeguroDAO {

    public Seguro findByTipoSeguro(TipoSeguro tipoSeguro) {
        String query = "SELECT * FROM Seguro WHERE tipoSeguro = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, tipoSeguro.name()); // Utilizamos name() para pasar el nombre del enum como String
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double precioSeguro = rs.getDouble("precio");
                return new Seguro(tipoSeguro, precioSeguro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
