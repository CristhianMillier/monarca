package Modelo.BBDD;

import Modelo.Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CompraDaoImp implements CompraDao {

    private PreparedStatement pst;
    private Statement st;
    private ResultSet rs;
    private Connection con;
    private Compra objC;

    public CompraDaoImp(Connection con) {
        this.con = con;
    }

    @Override
    public ArrayList obtenerListaCompra() throws SQLException {
        ArrayList<Compra> ltsCompra = new ArrayList();
        try {
            String sql = "SELECT * FROM compra";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objC = new Compra();
                objC.setId(rs.getInt(1));
                objC.setPago(rs.getDouble(2));
                objC.setFecha(rs.getString(3));
                objC.setEstado(rs.getString(4));
                ProveedorDao objPr = new ProveedorDaoImp(con);
                objC.setIdProve(objPr.buscarIdProveedor(rs.getInt(5)));
                EmpleadoDao objEm = new EmpleadoDaoImp(con);
                objC.setIdEmp(objEm.buscarIdEmpleado(rs.getInt(6)));

                ltsCompra.add(objC);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsCompra;
    }

    @Override
    public boolean grabar(Object object) throws SQLException {
        objC = (Compra) object;
        try {

            String sql = "insert into compra (idCompra, pago, fecha, estado, "
                    + "idProveedor, idEmpleado) VALUES (NULL, " + objC.getPago() + ",'"
                    + objC.getFecha() + "','"
                    + objC.getEstado() + "',"
                    + objC.getIdProve().getId()+ ","
                    + objC.getIdEmp().getId()+ ") ";
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
         objC = (Compra) object;
        try {
            String sql = "DELETE FROM compra where idCompra = " + objC.getId();
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
        objC = (Compra) object;
        try {
            String sql = "UPDATE compra SET pago=? where idCompra = " + objC.getId();
            pst = con.prepareStatement(sql);
            pst.setDouble(1, objC.getPago());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public Compra buscarIdCompra(Object id) throws SQLException {
        try {
            String sql = "SELECT * FROM compra where idCompra = " + (Integer) id;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objC = new Compra();
                objC.setId(rs.getInt(1));
                objC.setPago(rs.getDouble(2));
                objC.setFecha(rs.getString(3));
                objC.setEstado(rs.getString(4));
                ProveedorDao objP = new ProveedorDaoImp(con);
                EmpleadoDao objEmp = new EmpleadoDaoImp(con);
                objC.setIdProve(objP.buscarIdProveedor(rs.getInt(5)));
                objC.setIdEmp(objEmp.buscarIdEmpleado(rs.getInt(6)));

            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return objC;
    }

    @Override
    public int obtenerUltimoID() throws SQLException {
        int id = 0;
        try {
            String sql = "SELECT max(idCompra) FROM compra";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt(1);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return id;
    }

    @Override
    public ArrayList reporteMes(Object mes, Object anio) throws SQLException {
        ArrayList<Compra> ltsCompra = new ArrayList();
        try {
            String sql = "SELECT * FROM licoreriamonarca.compra where month(fecha) = " + Integer.parseInt((String) mes) + " AND year(fecha) = " + Integer.parseInt((String) anio);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objC = new Compra();
                objC.setId(rs.getInt(1));
                objC.setPago(rs.getDouble(2));
                objC.setFecha(rs.getString(3));
                objC.setEstado(rs.getString(4));
                ProveedorDao objPr = new ProveedorDaoImp(con);
                objC.setIdProve(objPr.buscarIdProveedor(rs.getInt(5)));
                EmpleadoDao objEm = new EmpleadoDaoImp(con);
                objC.setIdEmp(objEm.buscarIdEmpleado(rs.getInt(6)));

                ltsCompra.add(objC);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsCompra;
    }

    @Override
    public ArrayList reporteRangoFecha(Object fechaInicio, Object fechaFin) throws SQLException {
        ArrayList<Compra> ltsCompra = new ArrayList();
        try {
            String sql = "SELECT * FROM licoreriamonarca.compra where fecha >= '" + (String) fechaInicio + "' AND fecha <= '" + (String) fechaFin + "'";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objC = new Compra();
                objC.setId(rs.getInt(1));
                objC.setPago(rs.getDouble(2));
                objC.setFecha(rs.getString(3));
                objC.setEstado(rs.getString(4));
                ProveedorDao objPr = new ProveedorDaoImp(con);
                objC.setIdProve(objPr.buscarIdProveedor(rs.getInt(5)));
                EmpleadoDao objEm = new EmpleadoDaoImp(con);
                objC.setIdEmp(objEm.buscarIdEmpleado(rs.getInt(6)));

                ltsCompra.add(objC);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsCompra;
    }

}
