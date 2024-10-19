package javaEnjoyers.vista;

package grupofp.vista;

import javaEnjoyers.controlador.Controlador;
import java.util.Scanner;

public class Vista {
    private Controlador controlador;
    private Scanner scanner;

    public Vista(Controlador controlador) {
        this.controlador = controlador;
        this.scanner = new Scanner(System.in);
    }

    // Método para mostrar el menú principal
    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n---- Menú Principal ----");
            System.out.println("1. Gestión de Excursiones");
            System.out.println("2. Gestión de Socios");
            System.out.println("3. Gestión de Inscripciones");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    gestionarExcursiones();
                    break;
                case 2:
                    gestionarSocios();
                    break;
                case 3:
                    gestionarInscripciones();
                    break;
                case 4:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 4);
    }

    // Método para gestionar excursiones
    private void gestionarExcursiones() {
        int opcion;
        do {
            System.out.println("\n---- Gestión de Excursiones ----");
            System.out.println("1. Añadir Excursión");
            System.out.println("2. Mostrar Excursiones con filtro de fechas");
            System.out.println("3. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    agregarExcursion();
                    break;
                case 2:
                    mostrarExcursionesFiltradas();
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 3);
    }

    // Ejemplo de métodos para gestionar excursiones
    private void agregarExcursion() {
        System.out.print("Ingrese el código de la excursión: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese la descripción de la excursión: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese la fecha de la excursión (formato YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.print("Ingrese el número de días: ");
        int dias = scanner.nextInt();
        System.out.print("Ingrese el precio de inscripción: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer

        // Llamar al controlador para agregar la excursión
        controlador.agregarExcursion(codigo, descripcion, fecha, dias, precio);
        System.out.println("Excursión agregada con éxito.");
    }

    private void mostrarExcursionesFiltradas() {
        System.out.print("Ingrese la fecha de inicio (formato YYYY-MM-DD): ");
        String fechaInicio = scanner.nextLine();
        System.out.print("Ingrese la fecha de fin (formato YYYY-MM-DD): ");
        String fechaFin = scanner.nextLine();

        // Llamar al controlador para mostrar excursiones filtradas
        controlador.mostrarExcursionesFiltradas(fechaInicio, fechaFin);
    }

    // Métodos para gestionar socios
    private void gestionarSocios() {
        // Similar a gestionarExcursiones
    }

    // Métodos para gestionar inscripciones
    private void gestionarInscripciones() {
        // Similar a gestionarExcursiones
    }

    // Otros métodos para capturar datos y mostrar información pueden ser añadidos aquí
}
