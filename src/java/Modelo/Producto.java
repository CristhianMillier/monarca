package Modelo;

public class Producto {
    private int id;
    private String descripcion;
    private double precio;
    private int stock;
    private String fechaCaducidad;
    private Categoria idCat;
    private Marca idMarca;
    private String estado;
    private boolean modificadoProd;
    private String nroProv;

    public Producto() {
    }

    public Producto(int id, String descripcion, double precio, int stock, String fechaCaducidad, Categoria idCat, Marca idMarca, String estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.fechaCaducidad = fechaCaducidad;
        this.idCat = idCat;
        this.idMarca = idMarca;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion.trim();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad.trim();
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Categoria getIdCat() {
        return idCat;
    }

    public void setIdCat(Categoria idCat) {
        this.idCat = idCat;
    }

    public Marca getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marca idMarca) {
        this.idMarca = idMarca;
    }

    public String getEstado() {
        return estado.trim();
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String comboProd(){
        return idMarca.getNombre() + " - " + descripcion.trim();
    }
    
    public boolean getModificadoProd(){
        return modificadoProd;
    }
    
    public void setModificadoProd(boolean modificadoProd){
        this.modificadoProd = modificadoProd;
    }

    public String getNroProv() {
        return nroProv;
    }

    public void setNroProv(String nroProv) {
        this.nroProv = nroProv;
    }
}
