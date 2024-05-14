package Modelo;

public class Venta {
    private int id;
    private String fecha;
    private double pagoTotal;
    private double descuento;
    private Empleado idEmp;
    private Cliente idClient;
    private String estado;
    private String nroTicket;

    public Venta() {
    }

    public Venta(int id, String fecha, double pagoTotal, double descuento, Empleado idEmp, Cliente idClient, String estado, String nroTicket) {
        this.id = id;
        this.fecha = fecha;
        this.pagoTotal = pagoTotal;
        this.descuento = descuento;
        this.idEmp = idEmp;
        this.idClient = idClient;
        this.estado = estado;
        this.nroTicket = nroTicket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha.trim();
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPagoTotal() {
        return pagoTotal;
    }

    public void setPagoTotal(double pagoTotal) {
        this.pagoTotal = pagoTotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public Empleado getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Empleado idEmp) {
        this.idEmp = idEmp;
    }

    public Cliente getIdClient() {
        return idClient;
    }

    public void setIdClient(Cliente idClient) {
        this.idClient = idClient;
    }

    public String getNroTicket() {
        return nroTicket;
    }

    public void setNroTicket(String nroTicket) {
        this.nroTicket = nroTicket;
    }

    public String getEstado() {
        return estado.trim();
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
