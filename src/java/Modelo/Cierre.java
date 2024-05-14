package Modelo;

public class Cierre {
    private int id;
    private String fechaAbrir;
    private String fechaCierre;
    private double monto;
    private Empleado idEmp;

    public Cierre() {
    }

    public Cierre(int id, String fechaAbrir, String fechaCierre, double monto, Empleado idEmp) {
        this.id = id;
        this.fechaAbrir = fechaAbrir;
        this.fechaCierre = fechaCierre;
        this.monto = monto;
        this.idEmp = idEmp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaAbrir() {
        return fechaAbrir.trim();
    }

    public void setFechaAbrir(String fechaAbrir) {
        this.fechaAbrir = fechaAbrir;
    }

    public String getFechaCierre() {
        return fechaCierre.trim();
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Empleado getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Empleado idEmp) {
        this.idEmp = idEmp;
    }
    
}
