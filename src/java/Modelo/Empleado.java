package Modelo;

public class Empleado {
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String dni;
    private Cargo idCargo;
    private String estado;
    private String correo;
    private String user;

    public Empleado(int id, String nombre, String apellido, String telefono, String direccion, String dni, Cargo idCargo, String estado, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.dni = dni;
        this.idCargo = idCargo;
        this.estado = estado;
        this.correo = correo;
    }

    public Empleado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre.trim();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido.trim();
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono.trim();
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion.trim();
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni.trim();
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Cargo getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargo idCargo) {
        this.idCargo = idCargo;
    }

    public String getEstado() {
        return estado.trim();
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCorreo() {
        return correo.trim();
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUser() {
        return user.trim();
    }

    public void setUser(String user) {
        this.user = user;
    }
    
}
