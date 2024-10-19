package javaEnjoyers.modelo;

public class SocioFederado extends Socio {

    //Atributos
    public String nif;
    public Federacion federacion;

    //Constructor
    public SocioFederado(String numeroSocio, String nombre, String nif, Federacion federacion){
        super(numeroSocio, nombre);
        this.nif = nif;
        this.federacion = federacion;
    }

    //Getters y Setters
    public String getNif() { return nif; }
    public Federacion getFederacion() { return federacion; }

    //Métodos
    public double calcularCuotaMensual() {
        return 10.0 * 0.95; // Cuota mensual con un 5% de descuento
    }

    public double calcularPrecioExcursion(Excursion excursion) {
        return excursion.getPrecioExcursion() * 0.90; // Precio de la excursión con un 10% de descuento
    }

    //toString
    @Override
    public String toString() {
        return "Socio Federado: " +
                "\nNIF: " + nif +
                "\nFederación: " + federacion;
    }
}
