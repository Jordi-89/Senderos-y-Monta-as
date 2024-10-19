package javaEnjoyers.modelo;

import java.util.ArrayList;

public class CentroExcursionista {

    //Atributos
    private ArrayList<Socio> socios;
    private ArrayList<Inscripcion> inscripciones;
    private ArrayList<Excursion> excursiones;

    // Constructor
    public CentroExcursionista(ArrayList<Socio> socios, ArrayList<Inscripcion> inscripciones, ArrayList<Excursion> excursiones) {
        this.socios = socios != null ? socios : new ArrayList<>();
        this.inscripciones = inscripciones != null ? inscripciones : new ArrayList<>();
        this.excursiones = excursiones != null ? excursiones : new ArrayList<>();
    }

    // Sobrecarga del constructor sin parámetros
    public CentroExcursionista() {
        this.socios = new ArrayList<>();
        this.inscripciones = new ArrayList<>();
        this.excursiones = new ArrayList<>();
    }

    //Getters y Setters
    public ArrayList<Socio> getSocios() { return socios; }
    public ArrayList<Inscripcion> getInscripciones() { return inscripciones; }
    public ArrayList<Excursion> getExcursiones() { return excursiones; }

    public void setSocios(ArrayList<Socio> socios) { this.socios = socios; }
    public void setInscripciones(ArrayList<Inscripcion> inscripciones) { this.inscripciones = inscripciones; }
    public void setExcursiones(ArrayList<Excursion> excursiones) { this.excursiones = excursiones; }

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

    //toString
    @Override
    public String toString() {
        return "CentroExcursionista: " +
                "\nSocios: " + socios +
                "\nInscripciones: " + inscripciones +
                "\nExcursiones: " + excursiones;
    }
}
