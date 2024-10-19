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
    public String getcodigoInscripcion() { return codigoInscripcion; }
    public Socio getsocio() { return socio; }
    public Excursion getExcursion() { return excursion; }
    public void setcodigoInscripcion(String codigoInscripcion) { this.codigoInscripcion = codigoInscripcion; }
    public void setsocio(Socio socio) { this.socio = socio; }
    public void setExcursion(Excursion excursion) { this.excursion = excursion; }

    //toString
    @Override
    public String toString() {
        return "Inscripción: " + codigoInscripcion +
                "\nSocio: " + socio +
                "\nExcursion: " + excursion;
    }
}
