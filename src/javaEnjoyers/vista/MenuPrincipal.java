package javaEnjoyers.vista;

import java.util.Scanner;

/**
 * Clase que representa el menú principal de la aplicación.
 */
public class MenuPrincipal {
    private Scanner scanner;

    // Constructor
    public MenuPrincipal() {
        scanner = new Scanner(System.in);
    }

    /**
     * Muestra el menú principal y devuelve la opción seleccionada por el usuario.
     * @return la opción seleccionada
     */
    public int mostrarMenuPrincipal() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Gestión de Excursiones");
        System.out.println("2. Gestión de Socios");
        System.out.println("3. Gestión de Inscripciones");
        System.out.println("4. Salir");
        System.out.print("Selecciona una opción: ");

        return scanner.nextInt();
    }
}
