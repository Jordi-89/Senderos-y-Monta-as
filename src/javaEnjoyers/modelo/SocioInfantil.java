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
    @Override
    public double calcularCuotaMensual() {
        double cuotaBase = 10.0 * 0.50;  // Cuota base con 50% de descuento
        double totalExcursiones = 0.0;

        // Calcular el costo total de las excursiones (sin descuentos adicionales)
        for (Inscripcion inscripcion : this.getInscripciones()) {
            totalExcursiones += inscripcion.getExcursion().getPrecioExcursion();
        }

        // Desglose de los componentes de la factura
        System.out.println("Desglose de la factura mensual (Socio Infantil):");
        System.out.println("Cuota base con descuento (50%): " + cuotaBase + " €");
        System.out.println("Costo de excursiones: " + totalExcursiones + " €");
        System.out.println("Seguro: Cubierto por el padre o madre");

        // Calcular el total
        double total = cuotaBase + totalExcursiones;
        System.out.println("Total a pagar: " + total + " €");

        return total;
    }

    public double calcularPrecioExcursion(Excursion excursion) {
        return excursion.getPrecioExcursion(); // Los socios infantiles no tienen cargos adicionales
    }

    //toString
    @Override
    public String toString() {
        return "----SocioInfantil: " +
                "\nNumero socio: " + getNumeroSocio() +
                "\nNombre: " + getNombre() +
                "\nNúmero Socio Adulto: " + numeroSocioAdulto;
    }
}
