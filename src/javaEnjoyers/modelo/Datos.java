package javaEnjoyers.modelo;

import java.util.ArrayList;

public class Datos {

    // Listas de entidades gestionadas por la aplicación
    private ArrayList<Socio> socios;
    private ArrayList<Inscripcion> inscripciones;
    private ArrayList<Excursion> excursiones;
    private ArrayList<Seguro> seguros;
    private ArrayList<Federacion> federaciones;

    // Constructor que precarga algunos datos iniciales
    public Datos() {
        socios = new ArrayList<>();
        inscripciones = new ArrayList<>();
        excursiones = new ArrayList<>();
        seguros = precargarSeguros();
        federaciones = precargarFederaciones();
    }

    // Getters y Setters
    public ArrayList<Socio> getSocios() {
        return socios;
    }

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public ArrayList<Excursion> getExcursiones() {
        return excursiones;
    }

    public ArrayList<Seguro> getSeguros() {
        return seguros;
    }

    public ArrayList<Federacion> getFederaciones() {
        return federaciones;
    }

    // Precarga de seguros
    private ArrayList<Seguro> precargarSeguros() {
        ArrayList<Seguro> seguros = new ArrayList<>();
        seguros.add(new Seguro(TipoSeguro.BASICO, 20.0));  // Ejemplo de seguro básico
        seguros.add(new Seguro(TipoSeguro.COMPLETO, 50.0)); // Ejemplo de seguro completo
        return seguros;
    }

    // Precarga de federaciones
    private ArrayList<Federacion> precargarFederaciones() {
        ArrayList<Federacion> federaciones = new ArrayList<>();
        federaciones.add(new Federacion("FED01", "Federación de Montaña"));
        federaciones.add(new Federacion("FED02", "Federación de Senderismo"));
        return federaciones;
    }

    // Métodos para agregar nuevos socios, inscripciones y excursiones
    public void agregarSocio(Socio socio) {
        socios.add(socio);
    }

    public void agregarInscripcion(Inscripcion inscripcion) {
        inscripciones.add(inscripcion);
    }

    public void agregarExcursion(Excursion excursion) {
        excursiones.add(excursion);
    }

    // Métodos para eliminar socios, inscripciones y excursiones
    public boolean eliminarSocio(Socio socio) {
        return socios.remove(socio);  // Devuelve true si se elimina correctamente
    }

    public boolean eliminarInscripcion(Inscripcion inscripcion) {
        return inscripciones.remove(inscripcion);
    }

    public boolean eliminarExcursion(Excursion excursion) {
        return excursiones.remove(excursion);
    }

    // Métodos para buscar (ejemplos sencillos, se pueden mejorar)
    public Socio buscarSocioPorNumero(String numeroSocio) {
        for (Socio socio : socios) {
            if (socio.getNumeroSocio().equals(numeroSocio)) {
                return socio;
            }
        }
        return null;  // Si no se encuentra el socio
    }

    public Excursion buscarExcursionPorCodigo(String codigoExcursion) {
        for (Excursion excursion : excursiones) {
            if (excursion.getCodigoExcursion().equals(codigoExcursion)) {
                return excursion;
            }
        }
        return null;  // Si no se encuentra la excursión
    }

    public Inscripcion buscarInscripcionPorCodigo(String codigoInscripcion) {
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getCodigoInscripcion().equals(codigoInscripcion)) {
                return inscripcion;
            }
        }
        return null;  // Si no se encuentra la inscripción
    }

    public ArrayList<Inscripcion> buscarInscripcionesPorSocio(String numeroSocio) {
        ArrayList<Inscripcion> inscripcionesPorSocio = new ArrayList<>();

        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getSocio().getNumeroSocio().equals(numeroSocio)) {
                inscripcionesPorSocio.add(inscripcion);
            }
        }
        return inscripcionesPorSocio;
    }

    public ArrayList<Inscripcion> mostrarInscripcionesPorExcursion(String codigoExcursion) {
        ArrayList<Inscripcion> resultado = new ArrayList<>();

        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getExcursion().getCodigoExcursion().equals(codigoExcursion)) {
                resultado.add(inscripcion);
            }
        }
        return resultado;
    }

    // toString
    @Override
    public String toString() {
        return "Datos: " +
                "\nSocios: " + socios +
                "\nInscripciones: " + inscripciones +
                "\nExcursiones: " + excursiones;
    }
}