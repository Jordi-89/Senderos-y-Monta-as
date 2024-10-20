package javaEnjoyers.vista;

import javaEnjoyers.controlador.Controlador;
import javaEnjoyers.modelo.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
            System.out.println("6. Filtrar inscripciones por rango de fechas");
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
                case 6:
                    mostrarInscripcionesPorFecha();
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
            System.out.println("El socio no existe. Vamos a registrarlo.");
            socio = agregarNuevoSocio(numeroSocio);  // Llamar a un metodo para agregar nuevo socio
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

    private Socio agregarNuevoSocio(String numeroSocio) {
        System.out.print("Nombre del Socio: ");
        String nombre = scanner.nextLine();

        System.out.println("Tipo de socio:");
        System.out.println("1. Socio Estándar");
        System.out.println("2. Socio Federado");
        System.out.println("3. Socio Infantil");
        int tipoSocio = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Socio nuevoSocio = null;

        switch (tipoSocio) {
            case 1:
                nuevoSocio = agregarSocioEstandar(numeroSocio, nombre);
                break;
            case 2:
                nuevoSocio = agregarSocioFederado(numeroSocio, nombre);
                break;
            case 3:
                nuevoSocio = agregarSocioInfantil(numeroSocio, nombre);
                break;
            default:
                System.out.println("Opción no válida.");
                return null;
        }
        controlador.agregarSocio(nuevoSocio);  // Guardar el nuevo socio en el sistema
        System.out.println("Socio añadido correctamente.");
        return nuevoSocio;
    }

    // Metodo para agregar Socio Estándar
    private SocioEstandar agregarSocioEstandar(String numeroSocio, String nombre) {
        System.out.print("NIF: ");
        String nif = scanner.nextLine();

        System.out.println("Seleccione tipo de seguro:");
        System.out.println("1. Básico");
        System.out.println("2. Completo");
        int tipoSeguro = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Seguro seguro = null;
        if (tipoSeguro == 1) {
            seguro = new Seguro(TipoSeguro.BASICO, 20.0);  // Precio de ejemplo
        } else if (tipoSeguro == 2) {
            seguro = new Seguro(TipoSeguro.COMPLETO, 50.0);  // Precio de ejemplo
        } else {
            System.out.println("Opción de seguro no válida.");
            return null;
        }

        return new SocioEstandar(numeroSocio, nombre, nif, seguro);
    }

    // Metodo para agregar Socio Federado
    private SocioFederado agregarSocioFederado(String numeroSocio, String nombre) {
        System.out.print("NIF: ");
        String nif = scanner.nextLine();

        // Listar las federaciones disponibles
        ArrayList<Federacion> federaciones = controlador.mostrarFederaciones();  // Método del controlador para obtener federaciones
        if (federaciones.isEmpty()) {
            System.out.println("No hay federaciones disponibles.");
            return null;
        }

        System.out.println("Seleccione una federación:");
        for (int i = 0; i < federaciones.size(); i++) {
            System.out.println((i + 1) + ". " + federaciones.get(i).getNombre());
        }
        int opcionFederacion = scanner.nextInt();
        scanner.nextLine();  // Limpiar buffer

        if (opcionFederacion < 1 || opcionFederacion > federaciones.size()) {
            System.out.println("Opción no válida.");
            return null;
        }
        Federacion federacionSeleccionada = federaciones.get(opcionFederacion - 1);
        return new SocioFederado(numeroSocio, nombre, nif, federacionSeleccionada);
    }

    // Metodo para agregar Socio Infantil
    private SocioInfantil agregarSocioInfantil(String numeroSocio, String nombre) {
        System.out.print("Número de Socio del padre o madre: ");
        String numeroSocioAdulto = scanner.nextLine();

        Socio adulto = controlador.buscarSocioPorNumero(numeroSocioAdulto);
        if (adulto == null) {
            System.out.println("El padre o madre no existe.");
            return null;
        }
        return new SocioInfantil(numeroSocio, nombre, numeroSocioAdulto);
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

    private void mostrarInscripcionesPorFecha() {
        System.out.println("Ingrese la fecha de inicio (dd/MM/yyyy): ");
        String fechaInicioStr = scanner.nextLine();
        LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.println("Ingrese la fecha de fin (dd/MM/yyyy): ");
        String fechaFinStr = scanner.nextLine();
        LocalDate fechaFin = LocalDate.parse(fechaFinStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Llamar al controlador para obtener las inscripciones en ese rango de fechas
        ArrayList<Inscripcion> inscripcionesFiltradas = controlador.mostrarInscripcionesPorFecha(fechaInicio, fechaFin);

        // Mostrar los resultados
        if (inscripcionesFiltradas.isEmpty()) {
            System.out.println("No se encontraron inscripciones en el rango de fechas indicado.");
        } else {
            System.out.println("Inscripciones entre " + fechaInicio + " y " + fechaFin + ":");
            for (Inscripcion inscripcion : inscripcionesFiltradas) {
                System.out.println(inscripcion.toString());
            }
        }
    }

}
