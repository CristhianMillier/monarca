package Modelo.Negocio;

import Modelo.BBDD.ConexionImp;
import Modelo.BBDD.DetVentaDao;
import Modelo.BBDD.DetVentaDaoImp;
import Modelo.DetVenta;
import Modelo.ProductoMasVendido;
import java.sql.Connection;
import java.util.ArrayList;

public class DetVentaBo {
    public static boolean grabar(Object object) throws Exception {
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            DetVentaDao obj = new DetVentaDaoImp(con);
            obj.grabar(object);
            con.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    public static boolean eliminar(Object object) throws Exception {
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            DetVentaDao obj = new DetVentaDaoImp(con);
            obj.eliminar(object);
            con.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    public static ArrayList obtenerListaDetVenta(Object idVenta) throws Exception {
        Connection con = null;
        ArrayList<DetVenta> ltsDetVenta = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            DetVentaDao obj = new DetVentaDaoImp(con);
            ltsDetVenta = obj.obtenerListaDetVenta(idVenta);
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return ltsDetVenta;
    }
    
    public static ArrayList reporteProductoMasVendido(Object mes, Object anio) throws Exception {
        Connection con = null;
        ArrayList<ProductoMasVendido> ltsProductoMasVendido = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            DetVentaDao obj = new DetVentaDaoImp(con);
            ltsProductoMasVendido = obj.obtenerProductoMasVendido(mes, anio);
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return ltsProductoMasVendido;
    }
}
