package javaEnjoyers.modelo;

public class Federacion {

    //Atributos
    private String codigoFederacion;
    private String nombre;

    //Constructor
    public Federacion(String codigoFederacion, String nombre){
        this.codigoFederacion = codigoFederacion;
        this.nombre = nombre;
    }

    //Getters y Setters
    public String getCodigoFederacion() { return codigoFederacion; }
    public String getNombre() { return nombre; }
    public void setCodigoFederacion(String codigoFederacion) {
        if (codigoFederacion == null || codigoFederacion.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de federación no puede estar vacío.");
        }
        this.codigoFederacion = codigoFederacion;
    }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la federación no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    //toString
    @Override
    public String toString() {
        return "Federación: " +
                "\nID federación: " + codigoFederacion +
                "\nNombre: " + nombre;
    }
}
