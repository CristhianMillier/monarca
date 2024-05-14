package Modelo.BBDD;

import Modelo.Empleado;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EmpleadoDao extends General{
    public ArrayList obtenerListaEmpleadoCombo() throws SQLException;
    public ArrayList obtenerListaEmpleado() throws SQLException;
    public int obtenerUltimoId(String dni) throws SQLException;
    public Empleado buscarIdEmpleado(Object id) throws SQLException;
    public boolean grabarUser(Object object) throws SQLException;
}
