package Modelo.BBDD;

import Modelo.Categoria;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CategoriaDao extends General{
    public ArrayList obtenerListaCategoriaCombo() throws SQLException;
    public ArrayList obtenerListaCategoria() throws SQLException;
    public Categoria buscarIdCategoria(Object id) throws SQLException;
}
