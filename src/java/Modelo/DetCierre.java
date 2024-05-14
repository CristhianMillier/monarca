package Modelo;

public class DetCierre {
    private int id;
    private Caja idCaja;
    private int idCierre;

    public DetCierre() {
    }

    public DetCierre(int id, Caja idCaja, int idCierre) {
        this.id = id;
        this.idCaja = idCaja;
        this.idCierre = idCierre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Caja getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(Caja idCaja) {
        this.idCaja = idCaja;
    }

    public int getIdCierre() {
        return idCierre;
    }

    public void setIdCierre(int idCierre) {
        this.idCierre = idCierre;
    }
    
}
