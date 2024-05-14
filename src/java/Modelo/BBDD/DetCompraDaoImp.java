package Modelo.BBDD;

import Modelo.DetCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DetCompraDaoImp implements DetCompraDao{
    private PreparedStatement pst;
    private Statement st;
    private ResultSet rs;
    private Connection con;
    private DetCompra objDC;
    
    public DetCompraDaoImp(Connection con) {
        this.con = con;
    }
    
    @Override
    public boolean grabar(Object object) throws SQLException {
        objDC = (DetCompra) object;
        try {
            String sql = "insert into detallecompra (idDetalleCompra, precioCompra, cantidad, idCompra, idProducto) VALUES (NULL, "
                    + objDC.getCantidad()+ ","
                    + objDC.getPrecioCompra()+ ","
                    + objDC.getIdCompra()+ ","
                    + objDC.getIdProd().getId()+ ") ";
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
        objDC = (DetCompra) object;
        try {
            String sql = "DELETE FROM detallecompra where idDetalleCompra = " + objDC.getId();
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
    public ArrayList obtenerListaDetCompra(Object idCompra) throws SQLException {
        ArrayList<DetCompra> ltsDetCompra = new ArrayList();
        try {
            String sql = "SELECT * FROM detallecompra where idCompra = " + (Integer) idCompra;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objDC = new DetCompra();
                objDC.setId(rs.getInt(1));
                objDC.setPrecioCompra(rs.getDouble(2));
                objDC.setCantidad(rs.getInt(3));
                objDC.setIdCompra(rs.getInt(4));
                ProductoDao obj = new ProductoDaoImp(con);
                objDC.setIdProd(obj.buscarIdProducto(rs.getInt(5)));
                ltsDetCompra.add(objDC);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsDetCompra;
    }
}
