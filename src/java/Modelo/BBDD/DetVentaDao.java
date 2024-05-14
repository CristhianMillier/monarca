package Modelo.BBDD;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DetVentaDao extends General{
    public ArrayList obtenerListaDetVenta(Object idVenta) throws SQLException;
    public ArrayList obtenerProductoMasVendido(Object mes, Object anio) throws SQLException;
}
