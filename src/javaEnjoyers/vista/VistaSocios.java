package javaEnjoyers.vista;

import javaEnjoyers.controlador.Controlador;
import javaEnjoyers.modelo.Federacion;
import javaEnjoyers.modelo.Seguro;
import javaEnjoyers.modelo.Socio;
import javaEnjoyers.modelo.SocioEstandar;
import javaEnjoyers.modelo.TipoSeguro;

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
            System.out.println("Menú de Socios");
            System.out.println("1. Añadir Socio Estándar");
            System.out.println("2. Añadir Socio Federado");
            System.out.println("3. Añadir Socio Infantil");
            System.out.println("4. Eliminar Socio");
            System.out.println("5. Mostrar todos los socios");
            System.out.println("6. Modificar tipo de seguro de un socio estándar");
            System.out.println("0. Volver al Menú Principal");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            switch (opcion) {
                case 1:
                    agregarSocioEstandar();
                    break;
                case 2:
                    agregarSocioFederado();
                    break;
                case 3:
                    agregarSocioInfantil();
                    break;
                case 4:
                    eliminarSocio();
                    break;
                case 5:
                    mostrarTodosLosSocios();
                    break;
                case 6:
                    modificarSeguroSocioEstandar();
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
        // Ejemplo de interacción para agregar un socio estándar
        System.out.print("Número de Socio: ");
        String numeroSocio = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("NIF: ");
        String nif = scanner.nextLine();
        // Aquí se debería obtener el seguro disponible
        Seguro seguro = new Seguro(TipoSeguro.BASICO, 20.0); // Ejemplo de seguro
        controlador.agregarSocioEstandar(numeroSocio, nombre, nif, seguro);
    }

    private void agregarSocioFederado() {
        System.out.print("Número de Socio: ");
        String numeroSocio = scanner.nextLine();
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
        System.out.println("Socio federado añadido correctamente.");
    }

    private void agregarSocioInfantil() {
        System.out.print("Número de Socio del niño: ");
        String numeroSocioInfantil = scanner.nextLine();
        System.out.print("Nombre del niño: ");
        String nombre = scanner.nextLine();

        System.out.print("Número de Socio del padre o madre: ");
        String numeroSocioAdulto = scanner.nextLine();

        // Verificar si el número de socio adulto existe
        Socio socioAdulto = controlador.buscarSocioPorNumero(numeroSocioAdulto);
        if (socioAdulto == null) {
            System.out.println("El número de socio del padre o madre no existe.");
            return;
        }

        // Llamar al controlador para agregar el socio infantil
        controlador.agregarSocioInfantil(numeroSocioInfantil, nombre, numeroSocioAdulto);
        System.out.println("Socio infantil añadido correctamente.");
    }

    private void eliminarSocio() {
        System.out.print("Número de Socio a eliminar: ");
        String numeroSocio = scanner.nextLine();
        boolean eliminado = controlador.eliminarSocio(numeroSocio);
        if (eliminado) {
            System.out.println("Socio eliminado correctamente.");
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

        Socio socio = controlador.buscarSocioPorNumero(numeroSocio);
        if (socio == null || !(socio instanceof SocioEstandar)) {
            System.out.println("El socio no existe o no es un socio estándar.");
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
        System.out.println("El tipo de seguro ha sido actualizado.");
    }


}
