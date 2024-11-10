package javaEnjoyers.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Socio {

    //Atributos
    private String numeroSocio;
    private String nombre;
    private List<Inscripcion> inscripciones;

    //Constructor
    public Socio(String numeroSocio, String nombre){
        this.numeroSocio = numeroSocio;
        this.nombre = nombre;
        this.inscripciones = new ArrayList<>();
    }

    //Getters y Setters
    public String getNumeroSocio() { return numeroSocio; }
    public String getNombre() { return nombre; }
    public List<Inscripcion> getInscripciones() { return inscripciones; }
    public void setNumeroSocio(String numeroSocio) { this.numeroSocio = numeroSocio; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setInscripciones(List<Inscripcion> inscripciones) { this.inscripciones = inscripciones; }

    //Métodos
    public double calcularCuotaMensual() {
        return 10.0;  // Cuota mensual fija de 10€
    }
    public void agregarInscripcionSocios(Inscripcion inscripcion) {
        inscripciones.add(inscripcion);
    }

    //toString
    @Override
    public String toString() {
        return "\nNúmero de socio: " + numeroSocio +
                "\nNombre: " + nombre;
    }
}
