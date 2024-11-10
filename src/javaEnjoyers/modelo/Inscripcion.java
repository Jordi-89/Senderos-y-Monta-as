package javaEnjoyers.modelo;

public class Inscripcion {

    //Atributos
    private String codigoInscripcion;
    private Socio socio;
    private Excursion excursion;

    //Constructor
    public Inscripcion(String codigoInscripcion, Socio socio, Excursion excursion){
        this.codigoInscripcion = codigoInscripcion;
        this.socio = socio;
        this.excursion = excursion;
    }

    //Getters y Setters
    public String getCodigoInscripcion() { return codigoInscripcion; }
    public Socio getSocio() { return socio; }
    public Excursion getExcursion() { return excursion; }
    public void setCodigoInscripcion(String codigoInscripcion) { this.codigoInscripcion = codigoInscripcion; }
    public void setSocio(Socio socio) {
        if (socio == null) {
            throw new IllegalArgumentException("El socio no puede ser nulo.");
        }
        this.socio = socio;
    }
    public void setExcursion(Excursion excursion) {
        if (excursion == null) {
            throw new IllegalArgumentException("La excursión no puede ser nula.");
        }
        this.excursion = excursion;
    }

    //toString
    @Override
    public String toString() {
        return "------------------------------------" +
                "\nInscripción: " + codigoInscripcion +
                "\nSocio: " + socio +
                "\nExcursion: " + excursion;

    }
}
