package Modelo.BBDD;

import Modelo.DetVenta;
import Modelo.ProductoMasVendido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DetVentaDaoImp implements DetVentaDao {

    private PreparedStatement pst;
    private Statement st;
    private ResultSet rs;
    private Connection con;
    private DetVenta objDT;
    private ProductoMasVendido objProMasV;

    public DetVentaDaoImp(Connection con) {
        this.con = con;
    }

    @Override
    public boolean grabar(Object object) throws SQLException {
        objDT = (DetVenta) object;
        try {
            String sql = "insert into detalleventa (idDetalleVenta, cantidad, subtotal, idVenta, idProducto) VALUES (NULL, "
                    + objDT.getCantidad() + ","
                    + objDT.getSubTotal() + ","
                    + objDT.getIdVenta() + ","
                    + objDT.getIdProd().getId() + ") ";
            pst = con.prepareStatement(sql);
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean eliminar(Object object) throws SQLException {
        objDT = (DetVenta) object;
        try {
            String sql = "DELETE FROM detalleventa where idDetalleVenta = " + objDT.getId();
            pst = con.prepareStatement(sql);
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean modificar(Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList obtenerListaDetVenta(Object idVenta) throws SQLException {
        ArrayList<DetVenta> ltsDetVenta = new ArrayList();
        try {
            String sql = "SELECT * FROM detalleventa where idVenta = " + (Integer) idVenta;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objDT = new DetVenta();
                objDT.setId(rs.getInt(1));
                objDT.setCantidad(rs.getInt(2));
                objDT.setSubTotal(rs.getDouble(3));
                objDT.setIdVenta(rs.getInt(4));
                ProductoDao obj = new ProductoDaoImp(con);
                objDT.setIdProd(obj.buscarIdProducto(rs.getInt(5)));
                ltsDetVenta.add(objDT);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsDetVenta;
    }

    @Override
    public ArrayList obtenerProductoMasVendido(Object mes, Object anio) throws SQLException {
        ArrayList<ProductoMasVendido> ltsDetVenta = new ArrayList();
        try {
            String sql = "SELECT  p.descripcion, m.nombre, p.precioVenta, SUM(dv.cantidad) AS cantidad_vendida FROM detalleventa dv JOIN venta v ON dv.idVenta = v.idVenta JOIN producto p ON dv.idProducto= p.idProducto JOIN marca m ON p.idMarca = m.idMarca where month(fecha) = " + Integer.parseInt((String) mes) + " AND year(fecha) = " + Integer.parseInt((String) anio)
                    + " GROUP BY p.idProducto, p.descripcion,p.precioVenta ORDER BY cantidad_vendida DESC ";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objProMasV = new ProductoMasVendido();
                objProMasV.setDescripcion(rs.getString(1));
                objProMasV.setMarca(rs.getString(2));
                objProMasV.setPrecioVenta(rs.getInt(3));
                objProMasV.setCantidadVenta(rs.getInt(4));

                ltsDetVenta.add(objProMasV);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsDetVenta;
    }
}
