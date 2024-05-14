package Modelo;

public class ProductoMasVendido {

    private String descripcion;
    private String marca;
    private float precioVenta;
    private int cantidadVenta;

    public ProductoMasVendido() {
    }

    public ProductoMasVendido(String descripcion, String marca, float precioVenta, int cantidadVenta) {
        this.descripcion = descripcion;
        this.marca = marca;
        this.precioVenta = precioVenta;
        this.cantidadVenta = cantidadVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(int cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }

}
