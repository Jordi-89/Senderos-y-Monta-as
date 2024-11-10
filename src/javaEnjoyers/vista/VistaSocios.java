package javaEnjoyers.vista;

import javaEnjoyers.controlador.Controlador;
import javaEnjoyers.modelo.*;
import javaEnjoyers.modelo.excepciones.SocioNoEncontradoException;

import java.util.List;
import java.util.Scanner;

public class VistaSocios {

    private final Controlador controlador;
    private final Scanner scanner;

    public VistaSocios(Controlador controlador) {
        this.controlador = controlador;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuSocios() {
        int opcion;
        do {
            System.out.println("-------------------------------------------------");
            System.out.println("                 Menú de Socios             ");
            System.out.println("-------------------------------------------------");
            System.out.println("1. Añadir Socio Estándar");
            System.out.println("2. Añadir Socio Federado");
            System.out.println("3. Añadir Socio Infantil");
            System.out.println("4. Eliminar Socio");
            System.out.println("5. Mostrar todos los socios");
            System.out.println("6. Modificar tipo de seguro de un socio estándar");
            System.out.println("7. Mostrar factura mensual por socio");
            System.out.println("0. Volver al Menú Principal");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            switch (opcion) {
                case 1 -> agregarSocioEstandar();
                case 2 -> agregarSocioFederado();
                case 3 -> agregarSocioInfantil();
                case 4 -> eliminarSocio();
                case 5 -> mostrarTodosLosSocios();
                case 6 -> modificarSeguroSocioEstandar();
                case 7 -> mostrarFacturaMensual();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void agregarSocioEstandar() {
        System.out.print("Número de Socio: ");
        String numeroSocio = scanner.nextLine();
        try {
            Socio socioExistente = controlador.findByNumeroSocio(numeroSocio);
            System.out.println("Error: Ya existe un socio con el número " + numeroSocio);
            return;
        } catch (SocioNoEncontradoException e) {
            // Continuar si el socio no existe
        }

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("NIF: ");
        String nif = scanner.nextLine();

        System.out.println("Seleccione el tipo de seguro:");
        System.out.println("1. Básico (20.0€)");
        System.out.println("2. Completo (50.0€)");
        int opcionSeguro = scanner.nextInt();
        scanner.nextLine();

        Seguro seguro = switch (opcionSeguro) {
            case 1 -> new Seguro(TipoSeguro.BASICO, 20.0);
            case 2 -> new Seguro(TipoSeguro.COMPLETO, 50.0);
            default -> {
                System.out.println("Opción de seguro no válida.");
                yield null;
            }
        };

        controlador.agregarSocioEstandar(numeroSocio, nombre, nif, seguro);
        System.out.println("** Socio estándar añadido correctamente **");
    }

    private void agregarSocioFederado() {
        System.out.print("Número de Socio: ");
        String numeroSocio = scanner.nextLine();
        try {
            Socio socioExistente = controlador.findByNumeroSocio(numeroSocio);
            System.out.println("Error: Ya existe un socio con el número " + numeroSocio);
            return;
        } catch (SocioNoEncontradoException e) {
            // Continuar si el socio no existe
        }

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("NIF: ");
        String nif = scanner.nextLine();

        List<Federacion> federaciones = controlador.mostrarFederaciones();
        System.out.println("Seleccione una federación:");
        for (int i = 0; i < federaciones.size(); i++) {
            System.out.println((i + 1) + ". " + federaciones.get(i).getNombre());
        }
        int opcionFederacion = scanner.nextInt();
        scanner.nextLine();

        if (opcionFederacion < 1 || opcionFederacion > federaciones.size()) {
            System.out.println("Opción no válida.");
            return;
        }
        controlador.agregarSocioFederado(numeroSocio, nombre, nif, federaciones.get(opcionFederacion - 1));
        System.out.println("** Socio federado añadido correctamente **");
    }

    private void agregarSocioInfantil() {
        System.out.print("Número de Socio del niño: ");
        String numeroSocioInfantil = scanner.nextLine();

        try {
            Socio socioExistente = controlador.findByNumeroSocio(numeroSocioInfantil);
            System.out.println("Error: Ya existe un socio con el número " + numeroSocioInfantil);
            return;
        } catch (SocioNoEncontradoException e) {
            // Continuar si el socio no existe
        }

        System.out.print("Nombre del niño: ");
        String nombre = scanner.nextLine();
        System.out.print("Número de Socio del padre o madre: ");
        String numeroSocioAdulto = scanner.nextLine();

        try {
            Socio socioAdulto = controlador.findByNumeroSocio(numeroSocioAdulto);
            controlador.agregarSocioInfantil(numeroSocioInfantil, nombre, numeroSocioAdulto);
            System.out.println("** Socio infantil añadido correctamente **");
        } catch (SocioNoEncontradoException e) {
            System.out.println("Error: El padre o madre no existe.");
        }
    }

    private void eliminarSocio() {
        System.out.print("Número de Socio a eliminar: ");
        String numeroSocio = scanner.nextLine();
        if (controlador.eliminarSocio(numeroSocio)) {
            System.out.println("** Socio eliminado correctamente **");
        } else {
            System.out.println("No se pudo eliminar el socio.");
        }
    }

    private void mostrarTodosLosSocios() {
        List<Socio> socios = controlador.mostrarSocios();
        if (socios.isEmpty()) {
            System.out.println("No hay socios registrados.");
        } else {
            System.out.println("Lista de Socios:");
            for (Socio socio : socios) {
                System.out.println(socio);
            }
        }
    }

    private void modificarSeguroSocioEstandar() {
        System.out.print("Ingrese el número del socio estándar: ");
        String numeroSocio = scanner.nextLine();

        try {
            Socio socio = controlador.findByNumeroSocio(numeroSocio);
            if (!(socio instanceof SocioEstandar)) {
                System.out.println("El socio no es un socio estándar.");
                return;
            }

            System.out.println("Seleccione el nuevo tipo de seguro:");
            System.out.println("1. Básico");
            System.out.println("2. Completo");
            int tipoSeguro = scanner.nextInt();
            scanner.nextLine();

            Seguro nuevoSeguro = switch (tipoSeguro) {
                case 1 -> new Seguro(TipoSeguro.BASICO, 20.0);
                case 2 -> new Seguro(TipoSeguro.COMPLETO, 50.0);
                default -> {
                    System.out.println("Opción no válida.");
                    yield null;
                }
            };

            controlador.modificarSeguroSocioEstandar(numeroSocio, nuevoSeguro);
            System.out.println("** El tipo de seguro ha sido actualizado **");
        } catch (SocioNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarFacturaMensual() {
        System.out.print("Ingrese el número del socio: ");
        String numeroSocio = scanner.nextLine();
        double factura = controlador.calcularFacturaMensual(numeroSocio);
        if (factura == -1) {
            System.out.println("El socio no existe.");
        } else {
            System.out.println("La factura mensual del socio " + numeroSocio + " es: " + factura + " €");
        }
    }
}
