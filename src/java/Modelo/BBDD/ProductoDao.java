package Modelo.BBDD;

import Modelo.Producto;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductoDao extends General{
    public ArrayList obtenerListaProducto() throws SQLException;
    public ArrayList obtenerListaProductoCombo() throws SQLException;
    public Producto buscarIdProducto(Object id) throws SQLException;
    public ArrayList listaCaducidad() throws SQLException;
    public ArrayList listaStockMinimo() throws SQLException;
    public boolean actualizarProducto(Object cantidad, Object idProd) throws SQLException;
    public boolean actualizarProductoPrecio(Object precio, Object idProd) throws SQLException;
    public int cantProducto() throws SQLException;
}
