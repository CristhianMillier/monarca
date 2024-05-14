package Modelo.BBDD;

import java.sql.SQLException;

/**
 *
 * @author Cristhian Millier Flores Pasapera
 * @gamail 7050423891@untrm.edu.pe
 * @telefono 934576998
 */
public interface General {
    public boolean grabar(Object object) throws SQLException;
    public boolean eliminar(Object object) throws SQLException;
    public boolean modificar(Object object) throws SQLException;
}
