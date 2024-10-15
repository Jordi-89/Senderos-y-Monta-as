public class seguro {

    //Atributos
    public tipoSeguro tipoSeguro;
    public double precioSeguro;

    //Constructor
    public seguro(tipoSeguro tipoSeguro, double precio){
        this.tipoSeguro = tipoSeguro;
        this.precioSeguro = precio;
    }

    //Getters y Setters
    public tipoSeguro getTipoSeguro() { return tipoSeguro; }
    public double getPrecioSeguro() { return precioSeguro; }
    public void setTipoSeguro(tipoSeguro tipoSeguro) { this.tipoSeguro = tipoSeguro; }
    public void setPrecioSeguro(double precio) { this.precioSeguro = precio; }

    //toString

    @Override
    public String toString() {
        return "Seguro: " +
                "\nTipo:" + tipoSeguro +
                "\nPrecio: " + precioSeguro;
    }
}
