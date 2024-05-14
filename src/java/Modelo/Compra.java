package Modelo;

public class Compra {
    private int id;
    private double pago;
    private String fecha;
    private Proveedor idProve;
    private Empleado idEmp;
    private String estado;

    public Compra() {
    }

    public Compra(int id, double pago, String fecha, Proveedor idProve, Empleado idEmp, String estado) {
        this.id = id;
        this.pago = pago;
        this.fecha = fecha;
        this.idProve = idProve;
        this.idEmp = idEmp;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPago() {
        return this.redondear(pago);
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public String getFecha() {
        return fecha.trim();
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Proveedor getIdProve() {
        return idProve;
    }

    public void setIdProve(Proveedor idProve) {
        this.idProve = idProve;
    }

    public Empleado getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Empleado idEmp) {
        this.idEmp = idEmp;
    }

    public String getEstado() {
        return estado.trim();
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    private double redondear(double num) {
        return Math.rint(num * 100) / 100;
    }
}
