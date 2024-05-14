package Modelo;

public class DetVenta {
    private int id;
    private int cantidad;
    private double subTotal;
    private int idVenta;
    private Producto idProd;

    public DetVenta() {
    }

    public DetVenta(int id, int cantidad, double subTotal, int idVenta, Producto idProd) {
        this.id = id;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.idVenta = idVenta;
        this.idProd = idProd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Producto getIdProd() {
        return idProd;
    }

    public void setIdProd(Producto idProd) {
        this.idProd = idProd;
    }
    
}
