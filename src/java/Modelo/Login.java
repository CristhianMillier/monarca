package Modelo;

public class Login {
    private int id;
    private String user;
    private String password;
    private int idEmp;
    private String estado;

    public Login() {
    }

    public Login(int id, String user, String password, int idEmp, String estado) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.idEmp = idEmp;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user.trim();
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password.trim();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public String getEstado() {
        return estado.trim();
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
