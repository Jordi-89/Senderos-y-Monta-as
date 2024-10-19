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
    public String getnumeroSocio() { return numeroSocio; }
    public String getNombre() { return nombre; }
    public void setnumeroSocio(String numeroSocio) { this.numeroSocio = numeroSocio; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    //toString
    @Override
    public String toString() {
        return "Socio: " +
                "\nNúmero de javaEnjoyers.modulo.socio: " + numeroSocio +
                "\nNombre: " + nombre;
    }
}
