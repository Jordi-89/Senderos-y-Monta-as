package javaEnjoyers.modelo;

import java.time.LocalDate;

public class Excursion {

    //Atributos
    private String codigoExcursion;
    private String descripcion;
    private LocalDate fecha;
    private int numeroDias;
    private double precioExcursion;

    //Constructor
    public Excursion(String codigoExcursion, String descripcion, LocalDate fecha, int numeroDias, double precioExcursion){
        this.codigoExcursion = codigoExcursion;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.numeroDias = numeroDias;
        this.precioExcursion = precioExcursion;
    }

    //Getters y Setters
    public String getCodigoExcursion() { return codigoExcursion; }
    public String getDescripcion() { return descripcion; }
    public LocalDate getFecha() { return fecha; }
    public int getNumeroDias() { return numeroDias;}
    public double getPrecioExcursion() { return precioExcursion; }
    public void setcodigoExcursion(String codigoExcursion) { this.codigoExcursion = codigoExcursion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public void setNumeroDias(int numeroDias) { this.numeroDias = numeroDias; }
    public void setPrecioExcursion(double precioExcursion) {
        if (precioExcursion < 0) {
            throw new IllegalArgumentException("El precio de la excursión no puede ser negativo.");
        }
        this.precioExcursion = precioExcursion;
    }

    //toString
    @Override
    public String toString() {
        return "\nCódigo excursión: " + codigoExcursion +
                "\nDescripción: " + descripcion +
                "\nFecha: " + fecha +
                "\nNumero Días: " + numeroDias +
                "\nPrecio Excursión: " + precioExcursion;
    }
}
