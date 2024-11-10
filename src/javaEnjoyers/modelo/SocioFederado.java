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

    public double calcularPrecioExcursion(Excursion excursion) {
        return excursion.getPrecioExcursion() * 0.90; // Precio de la excursión con un 10% de descuento
    }

    @Override
    public double calcularCuotaMensual() {
        double cuotaBase = 10.0 * 0.95;  // Cuota base con 5% de descuento
        double totalExcursiones = 0.0;

        // Calcular el costo total de las excursiones con un 10% de descuento
        for (Inscripcion inscripcion : this.getInscripciones()) {
            totalExcursiones += inscripcion.getExcursion().getPrecioExcursion() * 0.90;
        }

        // Desglose de los componentes de la factura
        System.out.println("Desglose de la factura mensual (Socio Federado):");
        System.out.println("Cuota base con descuento (5%): " + cuotaBase + " €");
        System.out.println("Costo de excursiones con descuento (10%): " + totalExcursiones + " €");
        System.out.println("Seguro: Cubierto por la federación");

        // Calcular el total
        double total = cuotaBase + totalExcursiones;
        System.out.println("Total a pagar: " + total + " €");

        return total;
    }

    //toString
    @Override
    public String toString() {
        return "----Socio Federado: " +
                "\nNumero socio: " + getNumeroSocio() +
                "\nNombre: " + getNombre() +
                "\nNIF: " + nif +
                "\nFederación: " + federacion;
    }
}
