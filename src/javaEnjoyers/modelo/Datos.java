package javaEnjoyers.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que actúa como repositorio de los datos de la aplicación.
 */
public class Datos {
    // Listas para almacenar los datos de las entidades
    private ArrayList<Socio> listaSocios;
    private ArrayList<Excursion> listaExcursiones;
    private ArrayList<Inscripcion> listaInscripciones;

    // Constructor que inicializa las listas
    public Datos() {
        listaSocios = new ArrayList<>();
        listaExcursiones = new ArrayList<>();
        listaInscripciones = new ArrayList<>();
    }

    // Métodos de gestión para Socios

    /**
     * Añadir un socio a la lista de socios.
     *
     * @param socio El socio a añadir.
     * @return true si se añadió correctamente, false si el socio ya existe.
     */
    public boolean añadirSocio(Socio socio) {
        if (!listaSocios.contains(socio)) {
            listaSocios.add(socio);
            return true;
        }
        return false; // Si el socio ya existe, no se añade
    }

    /**
     * Eliminar un socio de la lista.
     *
     * @param numeroSocio El número del socio a eliminar.
     * @return true si se eliminó correctamente, false si no se encontró el socio o tiene inscripciones.
     */
    public boolean eliminarSocio(int numeroSocio) {
        Socio socio = buscarSocioPorNumero(numeroSocio);
        if (socio != null && !tieneInscripciones(socio)) {
            listaSocios.remove(socio);
            return true;
        }
        return false;
    }

    /**
     * Modificar el tipo de seguro de un socio estándar.
     *
     * @param numeroSocio El número del socio.
     * @param nuevoTipoSeguro El nuevo tipo de seguro.
     * @return true si se modificó correctamente, false si no se encontró el socio o no es estándar.
     */
    public boolean modificarSeguroSocio(int numeroSocio, String nuevoSeguro) {
        Socio socio = buscarSocioPorNumero(numeroSocio);
        if (socio instanceof SocioEstandar) {
            ((SocioEstandar) socio).setSeguro(nuevoSeguro);
            return true;
        }
        return false;
    }

    /**
     * Buscar un socio por su número.
     *
     * @param numeroSocio El número del socio.
     * @return El socio encontrado o null si no existe.
     */
    public Socio buscarSocioPorNumero(int numeroSocio) {
        for (Socio socio : listaSocios) {
            if (socio.getNumeroSocio() == numeroSocio) {
                return socio;
            }
        }
        return null;
    }

    /**
     * Verificar si un socio tiene inscripciones.
     *
     * @param socio El socio a verificar.
     * @return true si el socio tiene inscripciones, false en caso contrario.
     */
    private boolean tieneInscripciones(Socio socio) {
        for (Inscripcion inscripcion : listaInscripciones) {
            if (inscripcion.getSocio().equals(socio)) {
                return true;
            }
        }
        return false;
    }

    // Métodos de gestión para Excursiones

    /**
     * Añadir una excursión a la lista.
     *
     * @param excursion La excursión a añadir.
     * @return true si se añadió correctamente, false si ya existe.
     */
    public boolean añadirExcursion(Excursion excursion) {
        if (!listaExcursiones.contains(excursion)) {
            listaExcursiones.add(excursion);
            return true;
        }
        return false;
    }

    /**
     * Eliminar una excursión de la lista.
     *
     * @param codigoExcursion El código de la excursión a eliminar.
     * @return true si se eliminó correctamente, false si no se encontró o hay inscripciones asociadas.
     */
    public boolean eliminarExcursion(String codigoExcursion) {
        Excursion excursion = buscarExcursionPorCodigo(codigoExcursion);
        if (excursion != null && !tieneInscripciones(excursion)) {
            listaExcursiones.remove(excursion);
            return true;
        }
        return false;
    }

    /**
     * Buscar una excursión por su código.
     *
     * @param codigoExcursion El código de la excursión.
     * @return La excursión encontrada o null si no existe.
     */
    public Excursion buscarExcursionPorCodigo(String codigoExcursion) {
        for (Excursion excursion : listaExcursiones) {
            if (excursion.getCodigo().equals(codigoExcursion)) {
                return excursion;
            }
        }
        return null;
    }

    // Métodos adicionales para gestionar inscripciones y otros datos

    // Implementar métodos similares para gestionar inscripciones
}
