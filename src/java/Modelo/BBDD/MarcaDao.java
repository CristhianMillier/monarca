package Modelo.BBDD;

import Modelo.Marca;
import java.sql.SQLException;
import java.util.ArrayList;

public interface MarcaDao extends General{
    public ArrayList obtenerListaMarca() throws SQLException;
    public ArrayList obtenerListaMarcaCombo() throws SQLException;
    public Marca buscarIdMarca(Object id) throws SQLException;
}
