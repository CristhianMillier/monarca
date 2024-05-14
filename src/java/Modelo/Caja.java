package Modelo;

public class Caja {
    private int id;
    private String nroTicket;
    private String fecha;
    private double pago;
    private Venta idVenta;
    private String estado;

    public Caja() {
    }

    public Caja(int id, String nroTicket, String fecha, double pago, Venta idVenta, String estado) {
        this.id = id;
        this.nroTicket = nroTicket;
        this.fecha = fecha;
        this.pago = pago;
        this.idVenta = idVenta;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNroTicket() {
        return nroTicket.trim();
    }

    public void setNroTicket(String nroTicket) {
        this.nroTicket = nroTicket;
    }

    public String getFecha() {
        return fecha.trim();
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public Venta getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Venta idVenta) {
        this.idVenta = idVenta;
    }

    public String getEstado() {
        return estado.trim();
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
