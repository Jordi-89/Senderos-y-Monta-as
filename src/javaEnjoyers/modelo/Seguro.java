package javaEnjoyers.modelo;

public class Seguro {

    //Atributos
    private TipoSeguro tipoSeguro;
    private double precioSeguro;

    //Constructor
    public Seguro(TipoSeguro tipoSeguro, double precioSeguro){
        this.tipoSeguro = tipoSeguro;
        this.precioSeguro = precioSeguro;
    }

    //Getters y Setters
    public TipoSeguro getTipoSeguro() { return tipoSeguro; }
    public double getPrecioSeguro() { return precioSeguro; }
    public void setTipoSeguro(TipoSeguro tipoSeguro) { this.tipoSeguro = tipoSeguro; }
    public void setPrecioSeguro(double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio del seguro debe ser mayor que 0.");
        }
        this.precioSeguro = precio;
    }

    //toString

    @Override
    public String toString() {
        return "Seguro: " +
                "\nTipo:" + tipoSeguro +
                "\nPrecio: " + precioSeguro;
    }
}
