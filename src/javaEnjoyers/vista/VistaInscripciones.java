package javaEnjoyers.vista;

import javaEnjoyers.controlador.Controlador;
import javaEnjoyers.modelo.Inscripcion;
import javaEnjoyers.modelo.Socio;
import javaEnjoyers.modelo.Excursion;

import java.util.ArrayList;
import java.util.Scanner;

public class VistaInscripciones {

    private Controlador controlador;
    private Scanner scanner;

    public VistaInscripciones(Controlador controlador) {
        this.controlador = controlador;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuInscripciones() {
        int opcion;
        do {
            System.out.println("Menú de Inscripciones");
            System.out.println("1. Añadir Inscripción");
            System.out.println("2. Eliminar Inscripción");
            System.out.println("3. Mostrar todas las inscripciones");
            System.out.println("4. Mostrar inscripciones por socio");
            System.out.println("5. Mostrar inscripciones por excursión");
            System.out.println("0. Volver al Menú Principal");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            switch (opcion) {
                case 1:
                    agregarInscripcion();
                    break;
                case 2:
                    eliminarInscripcion();
                    break;
                case 3:
                    mostrarTodasLasInscripciones();
                    break;
                case 4:
                    mostrarInscripcionesPorSocio();
                    break;
                case 5:
                    mostrarInscripcionesPorExcursion();
                    break;
                case 0:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void agregarInscripcion() {
        System.out.print("Código de inscripción: ");
        String codigoInscripcion = scanner.nextLine();

        System.out.print("Número de Socio: ");
        String numeroSocio = scanner.nextLine();
        Socio socio = controlador.buscarSocioPorNumero(numeroSocio);
        if (socio == null) {
            System.out.println("El socio no existe.");
            return;
        }

        System.out.print("Código de la Excursión: ");
        String codigoExcursion = scanner.nextLine();
        Excursion excursion = controlador.buscarExcursionPorCodigo(codigoExcursion);
        if (excursion == null) {
            System.out.println("La excursión no existe.");
            return;
        }

        controlador.agregarInscripcion(codigoInscripcion, numeroSocio, codigoExcursion);
        System.out.println("Inscripción añadida correctamente.");
    }

    private void eliminarInscripcion() {
        System.out.print("Código de inscripción a eliminar: ");
        String codigoInscripcion = scanner.nextLine();

        boolean eliminada = controlador.eliminarInscripcion(codigoInscripcion);
        if (eliminada) {
            System.out.println("Inscripción eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar la inscripción.");
        }
    }

    private void mostrarTodasLasInscripciones() {
        ArrayList<Inscripcion> inscripciones = controlador.mostrarInscripciones();
        if (inscripciones.isEmpty()) {
            System.out.println("No hay inscripciones registradas.");
        } else {
            System.out.println("Inscripciones registradas:");
            for (Inscripcion inscripcion : inscripciones) {
                System.out.println(inscripcion.toString());
            }
        }
    }

    private void mostrarInscripcionesPorSocio() {
        System.out.print("Número de Socio: ");
        String numeroSocio = scanner.nextLine();
        ArrayList<Inscripcion> inscripciones = controlador.mostrarInscripcionesPorSocio(numeroSocio);
        if (inscripciones.isEmpty()) {
            System.out.println("El socio no tiene inscripciones registradas.");
        } else {
            System.out.println("Inscripciones del socio:");
            for (Inscripcion inscripcion : inscripciones) {
                System.out.println(inscripcion.toString());
            }
        }
    }

    private void mostrarInscripcionesPorExcursion() {
        System.out.print("Código de la Excursión: ");
        String codigoExcursion = scanner.nextLine();
        ArrayList<Inscripcion> inscripciones = controlador.mostrarInscripcionesPorExcursion(codigoExcursion);
        if (inscripciones.isEmpty()) {
            System.out.println("No hay inscripciones para esta excursión.");
        } else {
            System.out.println("Inscripciones de la excursión:");
            for (Inscripcion inscripcion : inscripciones) {
                System.out.println(inscripcion.toString());
            }
        }
    }
}
