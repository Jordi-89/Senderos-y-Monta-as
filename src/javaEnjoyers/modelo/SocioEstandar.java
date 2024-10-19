package javaEnjoyers.modelo;

public class SocioEstandar extends Socio {

    //Atributos
    private String nif;
    private Seguro seguro;

    //Constructor
    public SocioEstandar(String numeroSocio, String nombre, String nif, Seguro seguro){
        super(numeroSocio, nombre);
        this.nif = nif;
        this.seguro = seguro;
    }

    //Getters y setters
    public String getNif() { return nif; }
    public Seguro getSeguro() { return seguro; }
    public void setNif(String nif) { this.nif = nif; }
    public void setSeguro(Seguro seguro) { this.seguro = seguro; }

    //Métodos
    public double calcularCuotaMensual() {
        return 10.0; // Cuota mensual estándar
    }

    public double calcularPrecioExcursion(Excursion excursion) {
        return excursion.getPrecioExcursion() + seguro.getPrecioSeguro(); // Precio de la excursión más el seguro
    }

    //toString
    @Override
    public String toString() {
        return "Socio Estándar: " +
                "\nNIF: " + nif +
                "\nTipo Seguro: " + seguro;
    }
}
