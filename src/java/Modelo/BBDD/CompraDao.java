package Modelo.BBDD;

import Modelo.Compra;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CompraDao extends General{
    public ArrayList obtenerListaCompra() throws SQLException;
    public Compra buscarIdCompra(Object id)throws SQLException;
    public int obtenerUltimoID()throws SQLException;
    public ArrayList reporteMes(Object mes, Object anio) throws SQLException;
    public ArrayList reporteRangoFecha(Object fechaInicio, Object fechaFin) throws SQLException;
}
