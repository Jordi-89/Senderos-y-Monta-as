public class socioEstandar extends socio {

    //Atributos
    private String nif;
    private tipoSeguro seguroContratado;

    //Constructor
    public socioEstandar(String numeroSocio, String nombre, String nif, tipoSeguro seguroContratado){
        super(numeroSocio, nombre);
        this.nif = nif;
        this.seguroContratado = seguroContratado;
    }

    //Getters y setters
    public String getNif() { return nif; }
    public tipoSeguro getseguroContratado() { return seguroContratado; }

    //toString
    @Override
    public String toString() {
        return "Socio Estándar: " +
                "\nNIF: " + nif +
                "\nTipo Seguro: " + seguroContratado;
    }
}
