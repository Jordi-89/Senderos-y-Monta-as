package javaEnjoyers.modelo;

import java.util.ArrayList;

public abstract class Socio {

    //Atributos
    private String numeroSocio;
    private String nombre;
    private ArrayList<Inscripcion> inscripciones;

    //Constructor
    public Socio(String numeroSocio, String nombre){
        this.numeroSocio = numeroSocio;
        this.nombre = nombre;
        this.inscripciones = new ArrayList<>();
    }

    //Getters y Setters
    public String getNumeroSocio() { return numeroSocio; }
    public String getNombre() { return nombre; }
    public ArrayList<Inscripcion> getInscripciones() { return inscripciones; }
    public void setNumeroSocio(String numeroSocio) { this.numeroSocio = numeroSocio; }
    public void setNombre(String nombre) { this.nombre = nombre; }

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
        return "Socio: " +
                "\nNúmero de javaEnjoyers.modulo.socio: " + numeroSocio +
                "\nNombre: " + nombre;
    }
}
