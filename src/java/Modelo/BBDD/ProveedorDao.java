package Modelo.BBDD;

import Modelo.Proveedor;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProveedorDao extends General {

    public ArrayList obtenerListaProveedorCombo() throws SQLException;

    public ArrayList obtenerListaProveedor() throws SQLException;

    public Proveedor buscarIdProveedor(Object id) throws SQLException;
    
    public int cantProveedor() throws SQLException;

}
