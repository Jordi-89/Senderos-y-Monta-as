package javaEnjoyers.modelo;

public class Federacion {

    //Atributos
    private String codigoFederacion;
    private String nombreFederacion;

    //Constructor
    public Federacion(String codigoFederacion, String nombreFederacion){
        this.codigoFederacion = codigoFederacion;
        this.nombreFederacion = nombreFederacion;
    }

    //Getters y Setters
    public String getCodigoFederacion() { return codigoFederacion; }
    public String getNombre() { return nombreFederacion; }
    public void setCodigoFederacion(String codigoFederacion) {
        if (codigoFederacion == null || codigoFederacion.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de federación no puede estar vacío.");
        }
        this.codigoFederacion = codigoFederacion;
    }
    public void setNombreFederacion(String nombreFederacion) {
        if (nombreFederacion == null || nombreFederacion.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la federación no puede estar vacío.");
        }
        this.nombreFederacion = nombreFederacion;
    }

    //toString
    @Override
    public String toString() {
        return "\nCódigo federación: " + codigoFederacion +
                "\nNombre: " + nombreFederacion;
    }
}
