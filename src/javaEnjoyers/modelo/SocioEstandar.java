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
    public double calcularPrecioExcursion(Excursion excursion) {
        return excursion.getPrecioExcursion() + seguro.getPrecioSeguro(); // Precio de la excursión más el seguro
    }

    @Override
    public double calcularCuotaMensual() {
        double cuotaMensual = 10.0;  // Cuota base

        // Sumar el costo de cada excursión más el seguro
        for (Inscripcion inscripcion : this.getInscripciones()) {
            cuotaMensual += inscripcion.getExcursion().getPrecioExcursion() + this.getSeguro().getPrecioSeguro();
        }
        return cuotaMensual;
    }


    //toString
    @Override
    public String toString() {
        return "Socio Estándar: " +
                "\nNIF: " + nif +
                "\nTipo Seguro: " + seguro;
    }
}
