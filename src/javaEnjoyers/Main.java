package javaEnjoyers;

import javaEnjoyers.controlador.Controlador;
import javaEnjoyers.modelo.DatabaseConnection;
import javaEnjoyers.vista.VistaPrincipal;

public class Main {

    public static void main(String[] args) {
        // Inicializamos el controlador
        Controlador controlador = new Controlador();

        // Iniciamos la vista principal
        VistaPrincipal vistaPrincipal = new VistaPrincipal(controlador);
        vistaPrincipal.mostrarMenuPrincipal();

        // Prueba de conexión a la base de datos
        DatabaseConnection.testConnection();
    }
}
