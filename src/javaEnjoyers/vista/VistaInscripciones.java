package javaEnjoyers.vista;

import javaEnjoyers.controlador.Controlador;
import javaEnjoyers.modelo.*;
import javaEnjoyers.modelo.excepciones.InscripcionNoEliminableException;
import javaEnjoyers.modelo.excepciones.SocioNoEncontradoException;

import java.time.format.DateTimeParseException;
import java.util.List;
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
            System.out.println("-------------------------------------------------");
            System.out.println("              Menú de Inscripciones            ");
            System.out.println("-------------------------------------------------");
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
                case 1 -> agregarInscripcion();
                case 2 -> eliminarInscripcion();
                case 3 -> mostrarTodasLasInscripciones();
                case 4 -> mostrarInscripcionesPorSocio();
                case 5 -> mostrarInscripcionesPorExcursion();
                case 6 -> mostrarInscripcionesPorFecha();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void agregarInscripcion() {
        System.out.print("Código de inscripción: ");
        String codigoInscripcion = scanner.nextLine();

        Inscripcion inscripcionExistente = controlador.buscarInscripcionPorCodigo(codigoInscripcion);
        if (inscripcionExistente != null) {
            System.out.println("Error: Ya existe una inscripción con el código " + codigoInscripcion + ".");
            return;
        }

        System.out.print("Número de Socio: ");
        String numeroSocio = scanner.nextLine();
        Socio socio;
        try {
            socio = controlador.findByNumeroSocio(numeroSocio);  // Verificar si el socio existe
        } catch (SocioNoEncontradoException e) {
            System.out.println(e.getMessage());
            socio = agregarNuevoSocio(numeroSocio);
        }

        System.out.print("Código de la Excursión: ");
        String codigoExcursion = scanner.nextLine();
        Excursion excursion = controlador.buscarExcursionPorCodigo(codigoExcursion);
        if (excursion == null) {
            System.out.println("La excursión no existe.");
            return;
        }

        controlador.agregarInscripcion(codigoInscripcion, numeroSocio, codigoExcursion);
        System.out.println("** Inscripción añadida correctamente **");
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
            case 1 -> {
                nuevoSocio = agregarSocioEstandar(numeroSocio, nombre);
                controlador.agregarSocioEstandar(numeroSocio, nombre, ((SocioEstandar) nuevoSocio).getNif(), ((SocioEstandar) nuevoSocio).getSeguro());
            }
            case 2 -> {
                nuevoSocio = agregarSocioFederado(numeroSocio, nombre);
                controlador.agregarSocioFederado(numeroSocio, nombre, ((SocioFederado) nuevoSocio).getNif(), ((SocioFederado) nuevoSocio).getFederacion());
            }
            case 3 -> {
                nuevoSocio = agregarSocioInfantil(numeroSocio, nombre);
                controlador.agregarSocioInfantil(numeroSocio, nombre, ((SocioInfantil) nuevoSocio).getNumeroSocioAdulto());
            }
            default -> {
                System.out.println("Opción no válida.");
                return null;
            }
        }

        System.out.println("** Socio añadido correctamente **");
        return nuevoSocio;
    }

    private SocioEstandar agregarSocioEstandar(String numeroSocio, String nombre) {
        System.out.print("NIF: ");
        String nif = scanner.nextLine();

        System.out.println("Seleccione tipo de seguro:");
        System.out.println("1. Básico");
        System.out.println("2. Completo");
        int tipoSeguro = scanner.nextInt();
        scanner.nextLine();

        Seguro seguro = switch (tipoSeguro) {
            case 1 -> new Seguro(TipoSeguro.BASICO, 20.0);
            case 2 -> new Seguro(TipoSeguro.COMPLETO, 50.0);
            default -> {
                System.out.println("Opción de seguro no válida.");
                yield null;
            }
        };

        return new SocioEstandar(numeroSocio, nombre, nif, seguro);
    }

    private SocioFederado agregarSocioFederado(String numeroSocio, String nombre) {
        System.out.print("NIF: ");
        String nif = scanner.nextLine();

        List<Federacion> federaciones = controlador.mostrarFederaciones();
        if (federaciones.isEmpty()) {
            System.out.println("No hay federaciones disponibles.");
            return null;
        }

        System.out.println("Seleccione una federación:");
        for (int i = 0; i < federaciones.size(); i++) {
            System.out.println((i + 1) + ". " + federaciones.get(i).getNombre());
        }
        int opcionFederacion = scanner.nextInt();
        scanner.nextLine();

        if (opcionFederacion < 1 || opcionFederacion > federaciones.size()) {
            System.out.println("Opción no válida.");
            return null;
        }
        Federacion federacionSeleccionada = federaciones.get(opcionFederacion - 1);
        return new SocioFederado(numeroSocio, nombre, nif, federacionSeleccionada);
    }

    private SocioInfantil agregarSocioInfantil(String numeroSocio, String nombre) {
        System.out.print("Número de Socio del padre o madre: ");
        String numeroSocioAdulto = scanner.nextLine();

        try {
            controlador.findByNumeroSocio(numeroSocioAdulto);
            return new SocioInfantil(numeroSocio, nombre, numeroSocioAdulto);
        } catch (SocioNoEncontradoException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void eliminarInscripcion() {
        System.out.print("Código de inscripción a eliminar: ");
        String codigoInscripcion = scanner.nextLine();

        try {
            controlador.eliminarInscripcion(codigoInscripcion);
            System.out.println("** Inscripción eliminada correctamente **");
        } catch (InscripcionNoEliminableException e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarTodasLasInscripciones() {
        List<Inscripcion> inscripciones = controlador.mostrarInscripciones();
        if (inscripciones.isEmpty()) {
            System.out.println("No hay inscripciones registradas.");
        } else {
            System.out.println("| INSCRIPCIONES REGISTRADAS:");
            for (Inscripcion inscripcion : inscripciones) {
                System.out.println(inscripcion);
            }
        }
    }

    private void mostrarInscripcionesPorSocio() {
        System.out.print("Ingrese el número de socio: ");
        String numeroSocio = scanner.nextLine();

        try {
            Socio socio = controlador.findByNumeroSocio(numeroSocio);
            List<Inscripcion> inscripciones = controlador.mostrarInscripcionesPorSocio(numeroSocio);

            if (inscripciones.isEmpty()) {
                System.out.println("El socio no tiene inscripciones.");
            } else {
                System.out.println("| Inscripciones del socio " + socio.getNombre() + ":");
                for (Inscripcion inscripcion : inscripciones) {
                    System.out.println(inscripcion);
                }
            }
        } catch (SocioNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarInscripcionesPorExcursion() {
        System.out.print("Código de la Excursión: ");
        String codigoExcursion = scanner.nextLine();
        List<Inscripcion> inscripciones = controlador.mostrarInscripcionesPorExcursion(codigoExcursion);
        if (inscripciones.isEmpty()) {
            System.out.println("No hay inscripciones para esta excursión.");
        } else {
            System.out.println("| INSCRIPCIONES A LA EXCURSIÓN:");
            for (Inscripcion inscripcion : inscripciones) {
                System.out.println(inscripcion);
            }
        }
    }

    private void mostrarInscripcionesPorFecha() {
        try {
            System.out.println("Ingrese la fecha de inicio (dd/MM/yyyy): ");
            String fechaInicioStr = scanner.nextLine();
            LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            System.out.println("Ingrese la fecha de fin (dd/MM/yyyy): ");
            String fechaFinStr = scanner.nextLine();
            LocalDate fechaFin = LocalDate.parse(fechaFinStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            List<Inscripcion> inscripcionesFiltradas = controlador.mostrarInscripcionesPorFecha(fechaInicio, fechaFin);

            if (inscripcionesFiltradas.isEmpty()) {
                System.out.println("No se encontraron inscripciones en el rango de fechas indicado.");
            } else {
                System.out.println("| INSCRIPCIONES ENTRE " + fechaInicio + " Y " + fechaFin + ":");
                for (Inscripcion inscripcion : inscripcionesFiltradas) {
                    System.out.println(inscripcion);
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha incorrecto. Use el formato dd/MM/yyyy.");
        }
    }
}