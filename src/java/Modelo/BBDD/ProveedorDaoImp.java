package Modelo.BBDD;

import Modelo.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProveedorDaoImp implements ProveedorDao {

    private PreparedStatement pst;
    private Statement st;
    private ResultSet rs;
    private Connection con;
    private Proveedor objPv;

    public ProveedorDaoImp(Connection con) {
        this.con = con;
    }

    @Override
    public boolean grabar(Object object) throws SQLException {
        objPv = (Proveedor) object;
        try {
            String sql = "insert into proveedor (idProveedor, razonSocial, telefono, ruc, estado) "
                    + "VALUES (NULL, '" + objPv.getRazoSocial() + "','"
                    + objPv.getTelefono() + "','"
                    + objPv.getRuc() + "','"
                    + objPv.getEstado() + "') ";
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
        objPv = (Proveedor) object;
        try {
            String sql = "UPDATE proveedor SET estado=? where idProveedor = " + objPv.getId();
            pst = con.prepareStatement(sql);
            pst.setString(1, "No");
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean modificar(Object object) throws SQLException {
        objPv = (Proveedor) object;
        try {
            String sql = "UPDATE proveedor SET razonSocial=?,telefono=?, ruc=?, "
                    + "estado=? where idProveedor = " + objPv.getId();
            pst = con.prepareStatement(sql);
            pst.setString(1, objPv.getRazoSocial());
            pst.setString(2, objPv.getTelefono());
            pst.setString(3, objPv.getRuc());
            pst.setString(4, objPv.getEstado());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public ArrayList obtenerListaProveedor() throws SQLException {
        ArrayList<Proveedor> ltsProveedor = new ArrayList();
        try {
            String sql = "SELECT * FROM proveedor order by razonSocial";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objPv = new Proveedor();
                objPv.setId(rs.getInt(1));
                objPv.setRazoSocial(rs.getString(2));
                objPv.setTelefono(rs.getString(3));
                objPv.setRuc(rs.getString(4));
                objPv.setEstado(rs.getString(5));
                ltsProveedor.add(objPv);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsProveedor;
    }

    @Override
    public Proveedor buscarIdProveedor(Object id) throws SQLException {
        try {
            String sql = "SELECT * FROM proveedor where idProveedor = " + (Integer) id;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objPv = new Proveedor();
                objPv.setId(rs.getInt(1));
                objPv.setRazoSocial(rs.getString(2));
                objPv.setTelefono(rs.getString(3));
                objPv.setRuc(rs.getString(4));
                objPv.setEstado(rs.getString(5));
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return objPv;
    }

    @Override
    public ArrayList obtenerListaProveedorCombo() throws SQLException {
        ArrayList<Proveedor> ltsProveedor = new ArrayList();
        try {
            String sql = "SELECT * FROM proveedor where estado = 'Si' order by razonSocial";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objPv = new Proveedor();
                objPv.setId(rs.getInt(1));
                objPv.setRazoSocial(rs.getString(2));
                objPv.setTelefono(rs.getString(3));
                objPv.setRuc(rs.getString(4));
                objPv.setEstado(rs.getString(5));

                ltsProveedor.add(objPv);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsProveedor;
    }

    @Override
    public int cantProveedor() throws SQLException {
        int cant = 0;
        try {
            String sql = "SELECT count(*) FROM proveedor;";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                cant = rs.getInt(1);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return cant;
    }
}
