package javaEnjoyers;

import javaEnjoyers.controlador.Controlador;
import javaEnjoyers.modelo.DatabaseConnection;
import javaEnjoyers.modelo.Datos;
import javaEnjoyers.vista.VistaPrincipal;

public class Main {

    public static void main(String[] args) {
        // Inicializamos los datos y el controlador
        Datos datos = new Datos();
        Controlador controlador = new Controlador(datos);

        // Iniciamos la vista principal
        VistaPrincipal vistaPrincipal = new VistaPrincipal(controlador);
        vistaPrincipal.mostrarMenuPrincipal();

        DatabaseConnection.testConnection();
    }
}
