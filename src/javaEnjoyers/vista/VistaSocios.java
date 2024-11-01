package javaEnjoyers.vista;

import javaEnjoyers.controlador.Controlador;
import javaEnjoyers.modelo.Federacion;
import javaEnjoyers.modelo.Seguro;
import javaEnjoyers.modelo.Socio;
import javaEnjoyers.modelo.SocioEstandar;
import javaEnjoyers.modelo.SocioFederado;
import javaEnjoyers.modelo.SocioInfantil;
import javaEnjoyers.modelo.TipoSeguro;
import javaEnjoyers.modelo.excepciones.SocioNoEncontradoException;

import java.util.ArrayList;
import java.util.Scanner;

public class VistaSocios {

    private Controlador controlador;
    private Scanner scanner;

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
                case 1:
                    System.out.println("\n| AÑADIR SOCIO ESTANDAR\n");
                    agregarSocioEstandar();
                    break;
                case 2:
                    System.out.println("\n| AÑADIR SOCIO FEDERADO\n");
                    agregarSocioFederado();
                    break;
                case 3:
                    System.out.println("\n| AÑADIR SOCIO INFANTIL\n");
                    agregarSocioInfantil();
                    break;
                case 4:
                    System.out.println("\n| ELIMINAR SOCIO\n");
                    eliminarSocio();
                    break;
                case 5:
                    System.out.println("\n| LISTADO DE SOCIOS\n");
                    mostrarTodosLosSocios();
                    break;
                case 6:
                    System.out.println("\n| MODIFICAR SEGUROS\n");
                    modificarSeguroSocioEstandar();
                    break;
                case 7:
                    System.out.println("\n| FACTURA MENSUAL\n");
                    mostrarFacturaMensual();
                    break;
                case 0:
                    System.out.println("Volviendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }


    private void agregarSocioEstandar() {
        // Pedir el número de socio
        System.out.print("Número de Socio: ");
        String numeroSocio = scanner.nextLine();

        // Verificar si el número de socio ya existe
        try {
            Socio socioExistente = controlador.buscarSocioPorNumero(numeroSocio);
            if (socioExistente != null) {
                System.out.println("Error: Ya existe un socio con el número " + numeroSocio + ". No se puede agregar un socio con el mismo número.");
                return;
            }
        } catch (SocioNoEncontradoException e) {
            // Continuar si no existe el socio
        }

        // Pedir los datos restantes
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("NIF: ");
        String nif = scanner.nextLine();

        // Mostrar opciones de seguros
        System.out.println("Seleccione el tipo de seguro:");
        System.out.println("1. Básico (20.0€)");
        System.out.println("2. Completo (50.0€)");
        System.out.print("Seleccione una opción (1 o 2): ");
        int opcionSeguro = scanner.nextInt();
        scanner.nextLine();  // Limpiar el buffer de entrada

        // Asignar el seguro en función de la opción seleccionada
        Seguro seguro;
        if (opcionSeguro == 1) {
            seguro = new Seguro(TipoSeguro.BASICO, 20.0);  // Seguro básico
        } else if (opcionSeguro == 2) {
            seguro = new Seguro(TipoSeguro.COMPLETO, 50.0);  // Seguro completo
        } else {
            System.out.println("Opción de seguro no válida. No se ha podido agregar el socio.");
            return;
        }

        // Llamar al controlador para agregar el socio estándar
        controlador.agregarSocioEstandar(numeroSocio, nombre, nif, seguro);
        System.out.println("** Socio estándar añadido correctamente **");
    }

    private void agregarSocioFederado() {
        // Pedir el número de socio
        System.out.print("Número de Socio: ");
        String numeroSocio = scanner.nextLine();

        // Verificar si el número de socio ya existe
        try {
            Socio socioExistente = controlador.buscarSocioPorNumero(numeroSocio);
            if (socioExistente != null) {
                System.out.println("Error: Ya existe un socio con el número " + numeroSocio + ". No se puede agregar un socio con el mismo número.");
                return;
            }
        } catch (SocioNoEncontradoException e) {
            // Continuar si no existe el socio
        }

        // Pedir el resto de los datos del socio
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("NIF: ");
        String nif = scanner.nextLine();

        // Mostrar las federaciones disponibles
        ArrayList<Federacion> federaciones = controlador.mostrarFederaciones();
        if (federaciones.isEmpty()) {
            System.out.println("No hay federaciones disponibles.");
            return;
        }

        // Mostrar las federaciones para que el usuario seleccione
        System.out.println("Federaciones disponibles:");
        for (int i = 0; i < federaciones.size(); i++) {
            System.out.println((i + 1) + ". " + federaciones.get(i).getNombre());
        }

        System.out.print("Seleccione el número de la federación: ");
        int opcionFederacion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        if (opcionFederacion < 1 || opcionFederacion > federaciones.size()) {
            System.out.println("Opción no válida.");
            return;
        }

        Federacion federacionSeleccionada = federaciones.get(opcionFederacion - 1);

        // Llamar al controlador para agregar el socio federado
        controlador.agregarSocioFederado(numeroSocio, nombre, nif, federacionSeleccionada);
        System.out.println("** Socio federado añadido correctamente **");
    }


    private void agregarSocioInfantil() {
        // Pedir el número de socio infantil
        System.out.print("Número de Socio del niño: ");
        String numeroSocioInfantil = scanner.nextLine();

        // Verificar si el número de socio ya existe
        try {
            Socio socioExistente = controlador.buscarSocioPorNumero(numeroSocioInfantil);
            if (socioExistente != null) {
                System.out.println("Error: Ya existe un socio con el número " + numeroSocioInfantil + ". No se puede agregar un socio con el mismo número.");
                return;
            }
        } catch (SocioNoEncontradoException e) {
            // Continuar si no existe el socio (este es el comportamiento esperado)
        }

        // Pedir los datos restantes
        System.out.print("Nombre del niño: ");
        String nombre = scanner.nextLine();

        // Verificar el número de socio del padre o madre
        System.out.print("Número de Socio del padre o madre: ");
        String numeroSocioAdulto = scanner.nextLine();

        try {
            Socio socioAdulto = controlador.buscarSocioPorNumero(numeroSocioAdulto);
            if (socioAdulto == null) {
                System.out.println("El número de socio del padre o madre no existe.");
                return;
            }
        } catch (SocioNoEncontradoException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Llamar al controlador para agregar el socio infantil
        controlador.agregarSocioInfantil(numeroSocioInfantil, nombre, numeroSocioAdulto);
        System.out.println("** Socio infantil añadido correctamente **");
    }


    private void eliminarSocio() {
        System.out.print("Número de Socio a eliminar: ");
        String numeroSocio = scanner.nextLine();
        boolean eliminado = controlador.eliminarSocio(numeroSocio);
        if (eliminado) {
            System.out.println("** Socio eliminado correctamente **");
        } else {
            System.out.println("No se pudo eliminar el socio.");
        }
    }

    private void mostrarTodosLosSocios() {
        ArrayList<Socio> socios = controlador.mostrarSocios();
        if (socios.isEmpty()) {
            System.out.println("No hay socios registrados.");
        } else {
            System.out.println("Lista de Socios:");
            for (Socio socio : socios) {
                System.out.println(socio.toString());
            }
        }
    }

    private void modificarSeguroSocioEstandar() {
        System.out.print("Ingrese el número del socio estándar: ");
        String numeroSocio = scanner.nextLine();

        Socio socio;
        try {
            socio = controlador.buscarSocioPorNumero(numeroSocio);
        } catch (SocioNoEncontradoException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (!(socio instanceof SocioEstandar)) {
            System.out.println("El socio no es un socio estándar.");
            return;
        }

        // Mostrar opciones de seguros
        System.out.println("Tipos de seguro disponibles:");
        System.out.println("1. Básico");
        System.out.println("2. Completo");
        System.out.print("Seleccione el nuevo tipo de seguro: ");
        int tipoSeguro = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Seguro nuevoSeguro;
        if (tipoSeguro == 1) {
            nuevoSeguro = new Seguro(TipoSeguro.BASICO, 20.0);  // Precio de ejemplo
        } else if (tipoSeguro == 2) {
            nuevoSeguro = new Seguro(TipoSeguro.COMPLETO, 50.0);  // Precio de ejemplo
        } else {
            System.out.println("Opción no válida.");
            return;
        }

        controlador.modificarSeguroSocioEstandar(numeroSocio, nuevoSeguro);
        System.out.println("** El tipo de seguro ha sido actualizado **");
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
