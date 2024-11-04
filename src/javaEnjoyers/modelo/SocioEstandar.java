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
        double cuotaBase = 10.0;  // Cuota base
        double costoSeguro = this.getSeguro().getPrecioSeguro();  // Precio del seguro
        double totalExcursiones = 0.0;

        // Calcular el costo total de las excursiones
        for (Inscripcion inscripcion : this.getInscripciones()) {
            totalExcursiones += inscripcion.getExcursion().getPrecioExcursion();
        }

        // Desglose de los componentes de la factura
        System.out.println("Desglose de la factura mensual:");
        System.out.println("Cuota base: " + cuotaBase + " €");
        System.out.println("Costo del seguro: " + costoSeguro + " €");
        System.out.println("Costo de excursiones: " + totalExcursiones + " €");

        // Calcular y retornar el total
        double total = cuotaBase + costoSeguro + totalExcursiones;
        System.out.println("Total a pagar: " + total + " €");

        return total;
    }

    //toString
    @Override
    public String toString() {
        return "Socio Estándar: " +
                "\nNumero socio: " + getNumeroSocio() +
                "\nNombre: " + getNombre() +
                "\nNIF: " + nif +
                "\nTipo Seguro: " + seguro;
    }
}
