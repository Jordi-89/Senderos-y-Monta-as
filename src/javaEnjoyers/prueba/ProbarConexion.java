package javaEnjoyers.prueba;

import javaEnjoyers.modelo.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class ProbarConexion {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
