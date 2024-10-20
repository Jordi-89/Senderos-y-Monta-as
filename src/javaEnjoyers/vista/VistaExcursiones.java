package javaEnjoyers.vista;

import javaEnjoyers.controlador.Controlador;
import javaEnjoyers.modelo.Excursion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class VistaExcursiones {

    private Controlador controlador;
    private Scanner scanner;

    public VistaExcursiones(Controlador controlador) {
        this.controlador = controlador;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuExcursiones() {
        int opcion;
        do {
            System.out.println("Menú de Excursiones");
            System.out.println("1. Añadir Excursión");
            System.out.println("2. Eliminar Excursión");
            System.out.println("3. Mostrar todas las excursiones");
            System.out.println("4. Filtrar excursiones por fecha");
            System.out.println("0. Volver al Menú Principal");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            switch (opcion) {
                case 1:
                    agregarExcursion();
                    break;
                case 2:
                    eliminarExcursion();
                    break;
                case 3:
                    mostrarTodasLasExcursiones();
                    break;
                case 4:
                    filtrarExcursionesPorFecha();
                    break;
                case 0:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void agregarExcursion() {
        System.out.print("Código de la excursión: ");
        String codigo = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Fecha (dd/MM/yyyy): ");
        String fechaStr = scanner.nextLine();
        LocalDate fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Número de días: ");
        int numeroDias = scanner.nextInt();
        System.out.print("Precio de la excursión: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        controlador.agregarExcursion(codigo, descripcion, fecha, numeroDias, precio);
        System.out.println("Excursión añadida correctamente.");
    }

    private void eliminarExcursion() {
        System.out.print("Código de la excursión a eliminar: ");
        String codigoExcursion = scanner.nextLine();

        boolean eliminada = controlador.eliminarExcursion(codigoExcursion);
        if (eliminada) {
            System.out.println("Excursión eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar la excursión, está asociada a inscripciones o no existe.");
        }
    }

    private void mostrarTodasLasExcursiones() {
        ArrayList<Excursion> excursiones = controlador.mostrarExcursiones();
        if (excursiones.isEmpty()) {
            System.out.println("No hay excursiones registradas.");
        } else {
            System.out.println("Excursiones registradas:");
            for (Excursion excursion : excursiones) {
                System.out.println(excursion.toString());
            }
        }
    }

    private void filtrarExcursionesPorFecha() {
        try {
            // Pedir al usuario que ingrese las fechas
            System.out.println("Ingrese la fecha de inicio (dd/MM/yyyy): ");
            String fechaInicioStr = scanner.nextLine();
            LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            System.out.println("Ingrese la fecha de fin (dd/MM/yyyy): ");
            String fechaFinStr = scanner.nextLine();
            LocalDate fechaFin = LocalDate.parse(fechaFinStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            // Llamar al controlador para obtener las excursiones en ese rango de fechas
            ArrayList<Excursion> excursionesFiltradas = controlador.filtrarExcursionesPorFecha(fechaInicio, fechaFin);

            // Mostrar los resultados
            if (excursionesFiltradas.isEmpty()) {
                System.out.println("No se encontraron excursiones en el rango de fechas indicado.");
            } else {
                System.out.println("Excursiones entre " + fechaInicio + " y " + fechaFin + ":");
                for (Excursion excursion : excursionesFiltradas) {
                    System.out.println(excursion.toString());
                }
            }
        } catch (DateTimeParseException e) {
            // Capturar el error si el formato de fecha es incorrecto
            System.out.println("Error: Formato de fecha incorrecto. Use el formato dd/MM/yyyy.");
        }
    }
}
