package Modelo;

public class DetCompra {
    private int id;
    private double precioCompra;
    private int cantidad;
    private int idCompra;
    private Producto idProd;
    private double precioVenta;

    public DetCompra() {
    }

    public DetCompra(int id, double precioCompra, int cantidad, int idCompra, Producto idProd) {
        this.id = id;
        this.precioCompra = precioCompra;
        this.cantidad = cantidad;
        this.idCompra = idCompra;
        this.idProd = idProd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Producto getIdProd() {
        return idProd;
    }

    public void setIdProd(Producto idProd) {
        this.idProd = idProd;
    }
    
    public double calcularSubTotal(){
        return this.redondear(this.cantidad * this.precioCompra);
    }
    
    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    private double redondear(double num) {
        return Math.rint(num * 100) / 100;
    }
}
