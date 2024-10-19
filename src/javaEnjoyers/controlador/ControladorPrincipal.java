package javaEnjoyers.controlador;

import javaEnjoyers.vista.MenuPrincipal;
import javaEnjoyers.vista.Vista;

/**
 * Clase ControladorPrincipal que coordina las acciones iniciales
 * y delega a los controladores específicos según la opción seleccionada.
 */
public class ControladorPrincipal {
    // Instancias de los controladores específicos
    private ControladorSocio controladorSocio;
    private ControladorExcursion controladorExcursion;
    private ControladorInscripcion controladorInscripcion;

    // Instancia de la vista principal (interfaz de usuario)
    private MenuPrincipal menuPrincipal;
    private Vista vista;

    // Constructor
    public ControladorPrincipal() {
        // Inicialización de los controladores específicos
        controladorSocio = new ControladorSocio();
        controladorExcursion = new ControladorExcursion();
        controladorInscripcion = new ControladorInscripcion();

        // Inicialización de la vista principal
        menuPrincipal = new MenuPrincipal();
        vista = new Vista();
    }

    /**
     * Método para iniciar la aplicación y mostrar el menú principal.
     */
    public void iniciarAplicacion() {
        boolean salir = false;

        // Bucle principal del programa para mostrar el menú y gestionar las opciones
        while (!salir) {
            // Mostrar el menú principal y capturar la opción seleccionada
            int opcion = menuPrincipal.mostrarMenuPrincipal();

            // Gestionar la opción seleccionada por el usuario
            switch (opcion) {
                case 1: // Gestión de excursiones
                    gestionarExcursiones();
                    break;
                case 2: // Gestión de socios
                    gestionarSocios();
                    break;
                case 3: // Gestión de inscripciones
                    gestionarInscripciones();
                    break;
                case 4: // Salir
                    salir = true;
                    vista.mostrarMensaje("Saliendo de la aplicación...");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida. Por favor, selecciona una opción del menú.");
                    break;
            }
        }
    }

    /**
     * Método para gestionar las excursiones delegando en el ControladorExcursion.
     */
    private void gestionarExcursiones() {
        controladorExcursion.mostrarMenuExcursiones();
    }

    /**
     * Método para gestionar los socios delegando en el ControladorSocio.
     */
    private void gestionarSocios() {
        controladorSocio.mostrarMenuSocios();
    }

    /**
     * Método para gestionar las inscripciones delegando en el ControladorInscripcion.
     */
    private void gestionarInscripciones() {
        controladorInscripcion.mostrarMenuInscripciones();
    }
}
