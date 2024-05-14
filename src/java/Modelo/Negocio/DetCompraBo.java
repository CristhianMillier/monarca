package Modelo.Negocio;

import Modelo.BBDD.ConexionImp;
import Modelo.BBDD.DetCompraDao;
import Modelo.BBDD.DetCompraDaoImp;
import Modelo.DetCompra;
import java.sql.Connection;
import java.util.ArrayList;

public class DetCompraBo {
    public static boolean grabar(Object object) throws Exception {
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            DetCompraDao obj = new DetCompraDaoImp(con);
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
            DetCompraDao obj = new DetCompraDaoImp(con);
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
    public static ArrayList obtenerListaDetCompra(Object idCompra) throws Exception {
        Connection con = null;
        ArrayList<DetCompra> ltsDetCompra = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            DetCompraDao obj = new DetCompraDaoImp(con);
            ltsDetCompra = obj.obtenerListaDetCompra(idCompra);
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
        return ltsDetCompra;
    }
}
