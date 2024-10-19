package javaEnjoyers.modelo;

import java.util.ArrayList;

public class CentroExcursionista {

    //Atributos
    private ArrayList<Socio> socios;
    private ArrayList<Inscripcion> inscripciones;
    private ArrayList<Excursion> excursiones;

    //Constructor
    public CentroExcursionista(ArrayList<Socio> socios, ArrayList<Inscripcion> inscripciones, ArrayList<Excursion> excursiones) {
        socios = new ArrayList<>();
        inscripciones = new ArrayList<>();
        excursiones = new ArrayList<>();
    }

    //Getters y Setters
    public ArrayList<Socio> getSocios() { return socios; }
    public ArrayList<Inscripcion> getInscripciones() { return inscripciones; }
    public ArrayList<Excursion> getExcursiones() { return excursiones; }

    public void setSocios(ArrayList<Socio> socios) { this.socios = socios; }
    public void setInscripciones(ArrayList<Inscripcion> inscripciones) { this.inscripciones = inscripciones; }
    public void setExcursiones(ArrayList<Excursion> excursiones) { this.excursiones = excursiones; }

    //toString
    @Override
    public String toString() {
        return "javaEnjoyers.modulo.centroExcursionista{" +
                "socios=" + socios +
                ", inscripciones=" + inscripciones +
                ", excursiones=" + excursiones +
                '}';
    }
}
