package Modelo.Negocio;

import Modelo.BBDD.ConexionImp;
import Modelo.BBDD.MarcaDao;
import Modelo.BBDD.MarcaDaoImp;
import Modelo.Marca;
import java.sql.Connection;
import java.util.ArrayList;

public class MarcaBo {
    public static Marca buscarMarca(Object id) throws Exception {
        Connection con = null;
        Marca objM = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            MarcaDao obj = new MarcaDaoImp(con);
            objM = obj.buscarIdMarca(id);
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
        return objM;
    }
    public static ArrayList obtenerListaMarca() throws Exception {
        Connection con = null;
        ArrayList<Marca> ltsMarca = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            MarcaDao obj = new MarcaDaoImp(con);
            ltsMarca = obj.obtenerListaMarca();
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
        return ltsMarca;
    }
    public static ArrayList obtenerListaMarcaCombo() throws Exception {
        Connection con = null;
        ArrayList<Marca> ltsMarca = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            MarcaDao obj = new MarcaDaoImp(con);
            ltsMarca = obj.obtenerListaMarcaCombo();
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
        return ltsMarca;
    }
    public static boolean grabar(Object object) throws Exception {
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            MarcaDao obj = new MarcaDaoImp(con);
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
    public static boolean modificar(Object object) throws Exception {
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            MarcaDao obj = new MarcaDaoImp(con);
            obj.modificar(object);
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
            MarcaDao obj = new MarcaDaoImp(con);
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
}
