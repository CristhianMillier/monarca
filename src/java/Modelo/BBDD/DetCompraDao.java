package Modelo.BBDD;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DetCompraDao extends General{
    public ArrayList obtenerListaDetCompra(Object idCompra) throws SQLException;
}
