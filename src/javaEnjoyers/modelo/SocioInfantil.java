package javaEnjoyers.modelo;

public class SocioInfantil extends Socio {

    //Atributos
    private String numeroSocioAdulto;

    //Constructor
    public SocioInfantil(String numeroSocio, String nombre, String numeroSocioAdulto){
        super(numeroSocio, nombre);
        this.numeroSocioAdulto = numeroSocioAdulto;
    }

    //Getters y Setters
    public String getNumeroSocioAdulto() { return numeroSocioAdulto; }
    public void setNumeroSocioAdulto(String numeroSocioAdulto) { this.numeroSocioAdulto = numeroSocioAdulto; }

    //Métodos
    public double calcularCuotaMensual() {
        return 10.0 * 0.50; // Cuota mensual con un 50% de descuento
    }

    public double calcularPrecioExcursion(Excursion excursion) {
        return excursion.getPrecioExcursion(); // Los socios infantiles no tienen cargos adicionales
    }

    //toString
    @Override
    public String toString() {
        return "SocioInfantil: " +
                "\nNúmero Socio Adulto: " + numeroSocioAdulto;
    }
}
