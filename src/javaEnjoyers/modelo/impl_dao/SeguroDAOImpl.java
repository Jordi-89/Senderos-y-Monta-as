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

    @Override
    public Seguro findById(int id) {
        String query = "SELECT * FROM Seguro WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TipoSeguro tipoSeguro = TipoSeguro.valueOf(rs.getString("tipo"));
                double precio = rs.getDouble("precio");
                return new Seguro(tipoSeguro, precio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
