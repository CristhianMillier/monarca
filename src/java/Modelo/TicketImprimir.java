package Modelo;

public class TicketImprimir {
    private String fecha;
    private double totalPagoVenta;
    private double descuentoVenta;
    private String nroTicket;
    private String descripcionProd;
    private double precioVent;
    private String nombreMarca;
    private int cantidad;
    private double subtotal;

    public TicketImprimir() {
    }

    public TicketImprimir(String fecha, double totalPagoVenta, double descuentoVenta, String nroTicket, String descripcionProd, double precioVent, String nombreMarca, int cantidad, double subtotal) {
        this.fecha = fecha;
        this.totalPagoVenta = totalPagoVenta;
        this.descuentoVenta = descuentoVenta;
        this.nroTicket = nroTicket;
        this.descripcionProd = descripcionProd;
        this.precioVent = precioVent;
        this.nombreMarca = nombreMarca;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotalPagoVenta() {
        return totalPagoVenta;
    }

    public void setTotalPagoVenta(double totalPagoVenta) {
        this.totalPagoVenta = totalPagoVenta;
    }

    public double getDescuentoVenta() {
        return descuentoVenta;
    }

    public void setDescuentoVenta(double descuentoVenta) {
        this.descuentoVenta = descuentoVenta;
    }

    public String getNroTicket() {
        return nroTicket;
    }

    public void setNroTicket(String nroTicket) {
        this.nroTicket = nroTicket;
    }

    public String getDescripcionProd() {
        return descripcionProd;
    }

    public void setDescripcionProd(String descripcionProd) {
        this.descripcionProd = descripcionProd;
    }

    public double getPrecioVent() {
        return precioVent;
    }

    public void setPrecioVent(double precioVent) {
        this.precioVent = precioVent;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    
    public double sinDescuento(){
        return totalPagoVenta + descuentoVenta;
    }
}
