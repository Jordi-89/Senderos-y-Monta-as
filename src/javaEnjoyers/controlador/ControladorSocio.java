package javaEnjoyers.controlador;

import javaEnjoyers.modelo.Datos;
import javaEnjoyers.modelo.Socio;
import javaEnjoyers.vista.Vista;

import java.util.List;

/**
 * Clase que maneja la lógica relacionada con la gestión de socios.
 */
public class ControladorSocio {
    private Datos datos; // Clase que gestiona los datos de la aplicación
    private Vista vista; // Clase para interactuar con la interfaz de usuario

    // Constructor
    public ControladorSocio() {
        datos = new Datos(); // Inicializa los datos, podría recibir como parámetro si ya se ha creado en ControladorPrincipal
        vista = new Vista(); // Inicializa la vista
    }

    /**
     * Método para mostrar el menú de gestión de socios.
     */
    public void mostrarMenuSocios() {
        boolean salir = false;
        while (!salir) {
            // Mostrar el menú de opciones para los socios
            int opcion = vista.mostrarMenuSocios();
            switch (opcion) {
                case 1: // Añadir socio
                    añadirSocio();
                    break;
                case 2: // Modificar tipo de seguro de un socio estándar
                    modificarSeguroSocio();
                    break;
                case 3: // Eliminar socio
                    eliminarSocio();
                    break;
                case 4: // Mostrar todos los socios
                    mostrarSocios();
                    break;
                case 5: // Salir del menú de gestión de socios
                    salir = true;
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida. Por favor, selecciona una opción del menú.");
                    break;
            }
        }
    }

    /**
     * Método para añadir un nuevo socio.
     */
    private void añadirSocio() {
        Socio nuevoSocio = vista.obtenerDatosNuevoSocio(); // Solicitar datos del nuevo socio
        boolean añadido = datos.añadirSocio(nuevoSocio); // Añadir el socio a los datos
        if (añadido) {
            vista.mostrarMensaje("Socio añadido correctamente.");
        } else {
            vista.mostrarMensaje("Error al añadir el socio. Puede que ya exista.");
        }
    }

    /**
     * Método para modificar el seguro de un socio estándar.
     */
    private void modificarSeguroSocio() {
        int numeroSocio = vista.solicitarNumeroSocio(); // Solicitar el número del socio
        boolean modificado = datos.modificarSeguroSocio(numeroSocio, vista.obtenerNuevoSeguro()); // Modificar el seguro
        if (modificado) {
            vista.mostrarMensaje("Seguro del socio modificado correctamente.");
        } else {
            vista.mostrarMensaje("Error al modificar el seguro. Verifica el número de socio.");
        }
    }

    /**
     * Método para eliminar un socio.
     */
    private void eliminarSocio() {
        int numeroSocio = vista.solicitarNumeroSocio(); // Solicitar el número del socio a eliminar
        boolean eliminado = datos.eliminarSocio(numeroSocio); // Intentar eliminar el socio
        if (eliminado) {
            vista.mostrarMensaje("Socio eliminado correctamente.");
        } else {
            vista.mostrarMensaje("No se pudo eliminar el socio. Asegúrate de que no tiene inscripciones.");
        }
    }

    /**
     * Método para mostrar todos los socios.
     */
    private void mostrarSocios() {
        List<Socio> listaSocios = datos.obtenerTodosLosSocios(); // Obtener la lista de socios
        vista.mostrarListaSocios(listaSocios); // Mostrar la lista de socios
    }
}
