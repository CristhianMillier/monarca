package Modelo.BBDD;

import java.sql.SQLException;

public interface LoginDao extends General{
    public int Logeo(Object user, Object password) throws SQLException;
    public String buscarEmpleado(Object id) throws SQLException;   
    public boolean modificarPasword(Object object, Object idEmp) throws SQLException;
}
