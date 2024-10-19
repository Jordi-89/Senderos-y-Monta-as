package javaEnjoyers.modelo;

public class Seguro {

    //Atributos
    public TipoSeguro tipoSeguro;
    public double precioSeguro;

    //Constructor
    public Seguro(TipoSeguro tipoSeguro, double precio){
        this.tipoSeguro = tipoSeguro;
        this.precioSeguro = precio;
    }

    //Getters y Setters
    public TipoSeguro getTipoSeguro() { return tipoSeguro; }
    public double getPrecioSeguro() { return precioSeguro; }
    public void setTipoSeguro(TipoSeguro tipoSeguro) { this.tipoSeguro = tipoSeguro; }
    public void setPrecioSeguro(double precio) { this.precioSeguro = precio; }

    //toString

    @Override
    public String toString() {
        return "Seguro: " +
                "\nTipo:" + tipoSeguro +
                "\nPrecio: " + precioSeguro;
    }
}
