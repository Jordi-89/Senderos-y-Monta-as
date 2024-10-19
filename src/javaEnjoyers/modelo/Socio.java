package javaEnjoyers.modelo;

public abstract class Socio {

    //Atributos
    private String numeroSocio;
    private String nombre;

    //Constructor
    public Socio(String numeroSocio, String nombre){
        this.numeroSocio = numeroSocio;
        this.nombre = nombre;
    }

    //Getters y Setters
    public String getNumeroSocio() { return numeroSocio; }
    public String getNombre() { return nombre; }
    public void setNumeroSocio(String numeroSocio) { this.numeroSocio = numeroSocio; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    //Métodos
    public double calcularCuotaMensual() {
        return 10.0;  // Cuota mensual fija de 10€
    }

    //toString
    @Override
    public String toString() {
        return "Socio: " +
                "\nNúmero de javaEnjoyers.modulo.socio: " + numeroSocio +
                "\nNombre: " + nombre;
    }
}
