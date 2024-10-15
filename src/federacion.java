public class federacion {

    //Atributos
    private String codigoFederacion;
    private String nombre;

    //Constructor
    public federacion(String codigoFederacion, String nombre){
        this.codigoFederacion = codigoFederacion;
        this.nombre = nombre;
    }

    //Getters y Setters
    public String getcodigoFederacion() { return codigoFederacion; }
    public String getNombre() { return nombre; }
    public void setcodigoFederacion(String codigoFederacion) { this.codigoFederacion = codigoFederacion; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    //toString
    @Override
    public String toString() {
        return "Federación: " +
                "\nID federación: " + codigoFederacion +
                "\nNombre: " + nombre;
    }
}
