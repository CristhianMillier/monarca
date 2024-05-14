package Modelo;

public class Proveedor {
    private int id;
    private String razoSocial;
    private String telefono;
    private String ruc;
    private String estado;

    public Proveedor(int id, String razoSocial, String telefono, String ruc, String estado) {
        this.id = id;
        this.razoSocial = razoSocial;
        this.telefono = telefono;
        this.ruc = ruc;
        this.estado = estado;
    }

    public Proveedor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazoSocial() {
        return razoSocial.trim();
    }

    public void setRazoSocial(String razoSocial) {
        this.razoSocial = razoSocial;
    }

    public String getTelefono() {
        return telefono.trim();
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRuc() {
        return ruc.trim();
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getEstado() {
        return estado.trim();
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
