public class inscripcion {

    //Atributos
    private String codigoInscripcion;
    private socio socio;
    private String id_excursion;

    //Constructor
    public inscripcion(String codigoInscripcion, socio socio, String id_excursion){
        this.codigoInscripcion = codigoInscripcion;
        this.socio = socio;
        this.id_excursion = id_excursion;
    }

    //Getters y Setters
    public String getcodigoInscripcion() { return codigoInscripcion; }
    public socio getsocio() { return socio; }
    public String getId_excursion() { return id_excursion;}
    public void setcodigoInscripcion(String codigoInscripcion) { this.codigoInscripcion = codigoInscripcion; }
    public void setsocio(socio socio) { this.socio = socio; }
    public void setId_excursion(String id_excursion) { this.id_excursion = id_excursion;}

    //toString
    @Override
    public String toString() {
        return "Inscripción: " + codigoInscripcion +
                "\nID socio: " + socio +
                "\nID_excursion='" + id_excursion;
    }
}
