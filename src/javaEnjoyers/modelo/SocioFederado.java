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

    //toString
    @Override
    public String toString() {
        return "Socio Federado: " +
                "\nNIF: " + nif +
                "\nFederación: " + federacion;
    }
}
