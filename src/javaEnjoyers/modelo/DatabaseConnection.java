package javaEnjoyers.modelo;  // Asegúrate de que este paquete coincide con tu estructura de proyecto

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/centroexcursionista";
    private static final String USERNAME = "root";  // Deja esto vacío si usas Windows Authentication
    private static final String PASSWORD = "pooconbbdd24.";  // Deja esto vacío si usas Windows Authentication

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // Metodo de prueba para verificar la conexión
    public static void testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}
