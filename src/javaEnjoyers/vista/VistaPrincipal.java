package javaEnjoyers.vista;

import javaEnjoyers.controlador.Controlador;
import java.util.Scanner;

public class VistaPrincipal {

    private VistaSocios vistaSocios;
    private VistaExcursiones vistaExcursiones;
    private VistaInscripciones vistaInscripciones;
    private Scanner scanner;

    // Constructor
    public VistaPrincipal(Controlador controlador) {
        this.vistaSocios = new VistaSocios(controlador);
        this.vistaExcursiones = new VistaExcursiones(controlador);
        this.vistaInscripciones = new VistaInscripciones(controlador);
        this.scanner = new Scanner(System.in);
    }

    // Metodo que muestra el menú principal
    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("Menú Principal");
            System.out.println("1. Gestionar Socios");
            System.out.println("2. Gestionar Excursiones");
            System.out.println("3. Gestionar Inscripciones");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    vistaSocios.mostrarMenuSocios();
                    break;
                case 2:
                    vistaExcursiones.mostrarMenuExcursiones();
                    break;
                case 3:
                    vistaInscripciones.mostrarMenuInscripciones();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }
}
