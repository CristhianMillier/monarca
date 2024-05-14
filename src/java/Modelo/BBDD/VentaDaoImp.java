package Modelo.BBDD;

import Modelo.TicketImprimir;
import Modelo.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VentaDaoImp implements VentaDao {

    private PreparedStatement pst;
    private Statement st;
    private ResultSet rs;
    private Connection con;
    private Venta objV;

    public VentaDaoImp(Connection con) {
        this.con = con;
    }

    @Override
    public boolean grabar(Object object) throws SQLException {
        objV = (Venta) object;
        try {
            String sql = "insert into venta (idVenta, fecha, totalPago, descuento, estado, idEmpleado, idCliente, nroTicket) VALUES (NULL, '"
                    + objV.getFecha() + "',"
                    + objV.getPagoTotal() + ","
                    + objV.getDescuento() + ",'"
                    + objV.getEstado() + "',"
                    + objV.getIdEmp().getId() + ","
                    + 1 + ", '"
                    + objV.getNroTicket() + "' ) ";
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
        objV = (Venta) object;
        try {
            String sql = "DELETE FROM venta where idVenta = " + objV.getId();
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
        objV = (Venta) object;
        try {
            String sql = "UPDATE venta SET totalPago=?, descuento=? where idVenta = " + objV.getId();
            pst = con.prepareStatement(sql);
            pst.setDouble(1, objV.getPagoTotal());
            pst.setDouble(2, objV.getDescuento());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public ArrayList obtenerListaVenta() throws SQLException {
        ArrayList<Venta> ltsVenta = new ArrayList();
        try {
            String sql = "SELECT * FROM venta";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objV = new Venta();
                objV.setId(rs.getInt(1));
                objV.setFecha(rs.getString(2));
                objV.setPagoTotal(rs.getDouble(3));
                objV.setDescuento(rs.getDouble(4));
                objV.setEstado(rs.getString(5));
                EmpleadoDao objEmp = new EmpleadoDaoImp(con);
                objV.setIdEmp(objEmp.buscarIdEmpleado(rs.getInt(6)));
                objV.setNroTicket(rs.getString(8));
                ltsVenta.add(objV);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsVenta;
    }

    @Override
    public Venta buscarIdVenta(Object id) throws SQLException {
        try {
            String sql = "SELECT * FROM venta where idVenta = " + (Integer) id;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objV = new Venta();
                objV.setId(rs.getInt(1));
                objV.setFecha(rs.getString(2));
                objV.setPagoTotal(rs.getDouble(3));
                objV.setDescuento(rs.getDouble(4));
                objV.setEstado(rs.getString(5));
                EmpleadoDao objEmp = new EmpleadoDaoImp(con);
                objV.setIdEmp(objEmp.buscarIdEmpleado(rs.getInt(6)));
                objV.setNroTicket(rs.getString(8));
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return objV;
    }

    @Override
    public int obtenerTicket() throws SQLException {
        int ticket = 0;
        try {
            String sql = "SELECT max(idVenta) FROM venta;";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                ticket = rs.getInt(1);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ticket;
    }

    @Override
    public int obteneridVentaTicket(Object ticket) throws SQLException {
        int id = 0;
        try {
            String sql = "SELECT idVenta FROM venta where nroTicket ='" + (String) ticket + "'";
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
    public int cantVenta() throws SQLException {
        int cant = 0;
        try {
            String sql = "SELECT count(*) FROM venta;";
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

    @Override
    public ArrayList obtenerDashboar() throws SQLException {
        ArrayList<Venta> ltsVenta = new ArrayList();
        try {
            String sql = "SELECT * FROM venta where fecha = CURDATE()";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objV = new Venta();
                objV.setId(rs.getInt(1));
                objV.setFecha(rs.getString(2));
                objV.setPagoTotal(rs.getDouble(3));
                objV.setDescuento(rs.getDouble(4));
                objV.setEstado(rs.getString(5));
                EmpleadoDao objEmp = new EmpleadoDaoImp(con);
                objV.setIdEmp(objEmp.buscarIdEmpleado(rs.getInt(6)));
                objV.setNroTicket(rs.getString(8));
                ltsVenta.add(objV);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsVenta;
    }

    @Override
    public ArrayList obtenerDashboarBusca(String fechaInicio, String finFecha, int idEmpleado) throws SQLException {
        ArrayList<Venta> ltsVenta = new ArrayList();
        try {
            String sql = "SELECT * FROM licoreriamonarca.venta where fecha >= '" + fechaInicio + "' and fecha <= '" + finFecha + "' and idEmpleado = " + idEmpleado;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objV = new Venta();
                objV.setId(rs.getInt(1));
                objV.setFecha(rs.getString(2));
                objV.setPagoTotal(rs.getDouble(3));
                objV.setDescuento(rs.getDouble(4));
                objV.setEstado(rs.getString(5));
                EmpleadoDao objEmp = new EmpleadoDaoImp(con);
                objV.setIdEmp(objEmp.buscarIdEmpleado(rs.getInt(6)));
                objV.setNroTicket(rs.getString(8));
                ltsVenta.add(objV);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsVenta;
    }

    @Override
    public ArrayList reportedia(Object Fecha) throws SQLException {
        ArrayList<Venta> ltsVenta = new ArrayList();
        try {
            String sql = "SELECT fecha, sum(totalPago) FROM venta where fecha = '" + Fecha + "'";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objV = new Venta();
                objV.setFecha(rs.getString(1));
                objV.setPagoTotal(rs.getDouble(2));
                ltsVenta.add(objV);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsVenta;
    }

    @Override
    public ArrayList reporteRango(Object Fechaini, Object FechaFin) throws SQLException {
        ArrayList<Venta> ltsVenta = new ArrayList();
        try {
            String sql = "SELECT fecha, sum(totalPago) FROM venta where fecha >= '" + Fechaini + "' and fecha <= '" + FechaFin + "'";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objV = new Venta();
                objV.setFecha(rs.getString(1));
                objV.setPagoTotal(rs.getDouble(2));
                ltsVenta.add(objV);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsVenta;
    }

    @Override
    public ArrayList reporteMensual(Object mes, Object anio) throws SQLException {
        ArrayList<Venta> ltsVenta = new ArrayList();
        try {
            String sql = "SELECT fecha, sum(totalPago) FROM venta where month(fecha) = " + Integer.parseInt((String) mes) + " AND year(fecha) = " + Integer.parseInt((String) anio);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objV = new Venta();
                objV.setFecha(rs.getString(1));
                objV.setPagoTotal(rs.getDouble(2));
                ltsVenta.add(objV);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsVenta;
    }

    @Override
    public ArrayList generarTicketImprimir(Object nroTicket) throws SQLException {
        ArrayList<TicketImprimir> ltsImprimir = new ArrayList();
        TicketImprimir objVI = null;
        try {
            String sql = "SELECT V.fecha, V.totalPago, V.descuento, V.nroTicket, P.descripcion, P.precioVenta, M.nombre, D.cantidad, D.subtotal "
                    + "FROM detalleventa as D inner join venta as V on D.idVenta = V.idVenta "
                    + "inner join producto as P on D.idProducto = P.idProducto "
                    + "inner join marca as M on P.idMarca = M.idMarca where V.nroTicket = '" + (String) nroTicket + "'";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objVI = new TicketImprimir();
                objVI.setFecha(rs.getString(1));
                objVI.setTotalPagoVenta(rs.getDouble(2));
                objVI.setDescuentoVenta(rs.getDouble(3));
                objVI.setNroTicket(rs.getString(4));
                objVI.setDescripcionProd(rs.getString(5));
                objVI.setPrecioVent(rs.getDouble(6));
                objVI.setNombreMarca(rs.getString(7));
                objVI.setCantidad(rs.getInt(8));
                objVI.setSubtotal(rs.getDouble(9));
                ltsImprimir.add(objVI);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsImprimir;
    }
}
