public class socioFederado extends socio {

    //Atributos
    public String nif;
    public federacion federacion;

    //Constructor
    public socioFederado(String numeroSocio, String nombre, String nif, federacion federacion){
        super(numeroSocio, nombre);
        this.nif = nif;
        this.federacion = federacion;
    }

    //Getters y Setters
    public String getNif() { return nif; }
    public federacion getFederacion() { return federacion; }

    //toString
    @Override
    public String toString() {
        return "Socio Federado: " +
                "\nNIF: " + nif +
                "\nFederación: " + federacion;
    }
}
