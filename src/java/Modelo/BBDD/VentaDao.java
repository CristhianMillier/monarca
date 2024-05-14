package Modelo.BBDD;

import Modelo.Venta;
import java.sql.SQLException;
import java.util.ArrayList;

public interface VentaDao extends General{
    public ArrayList obtenerListaVenta() throws SQLException;
    public Venta buscarIdVenta(Object id) throws SQLException;
    public int obtenerTicket() throws SQLException;
    public int obteneridVentaTicket(Object ticket) throws SQLException;
    public int cantVenta() throws SQLException;
    public ArrayList obtenerDashboar() throws SQLException;
    public ArrayList obtenerDashboarBusca(String fechaInicio, String finFecha, int idEmpleado) throws SQLException;
    public ArrayList reportedia(Object Fecha) throws SQLException;
    public ArrayList reporteRango(Object FechaIni, Object FechaFin) throws SQLException;
    public ArrayList reporteMensual(Object mes, Object anio) throws SQLException;
    public ArrayList generarTicketImprimir(Object nroTicket) throws SQLException;
}
