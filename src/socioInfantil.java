public class socioInfantil extends socio {

    //Atributos
    private String numeroSocioAdulto;

    //Constructor
    public socioInfantil(String numeroSocio, String nombre){
        super(numeroSocio, nombre);
        this.numeroSocioAdulto = numeroSocioAdulto;
    }

    //Getters y Setters
    public String getNumeroSocioAdulto() { return numeroSocioAdulto; }
    public void setNumeroSocioAdulto(String numeroSocioAdulto) { this.numeroSocioAdulto = numeroSocioAdulto; }

    //toString
    @Override
    public String toString() {
        return "SocioInfantil: " +
                "\nNúmero Socio Adulto: " + numeroSocioAdulto;
    }
}
