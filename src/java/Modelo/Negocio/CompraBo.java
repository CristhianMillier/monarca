package Modelo.Negocio;

import Modelo.BBDD.CompraDao;
import Modelo.BBDD.CompraDaoImp;
import Modelo.BBDD.ConexionImp;
import Modelo.Compra;
import java.sql.Connection;
import java.util.ArrayList;

public class CompraBo {

    public static boolean grabar(Object object) throws Exception {
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            CompraDao obj = new CompraDaoImp(con);
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
            CompraDao obj = new CompraDaoImp(con);
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

    public static boolean modificar(Object object) throws Exception {
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            CompraDao obj = new CompraDaoImp(con);
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

    public static Compra buscarIdCompra(Object id) throws Exception {
        Connection con = null;
        Compra objC = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            CompraDao obj = new CompraDaoImp(con);
            objC = obj.buscarIdCompra(id);
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
        return objC;
    }

    public static ArrayList obtenerListaCompra() throws Exception {
        Connection con = null;
        ArrayList<Compra> ltsCompra = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            CompraDao obj = new CompraDaoImp(con);
            ltsCompra = obj.obtenerListaCompra();
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
        return ltsCompra;
    }

    public static int obtenerUltimoID() throws Exception {
        Connection con = null;
        int id = 0;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            CompraDao obj = new CompraDaoImp(con);
            id = obj.obtenerUltimoID();
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
        return id;
    }
    
    public static ArrayList reporteMes(Object mes, Object anio) throws Exception {
        Connection con = null;
        ArrayList<Compra> ltsCompra = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            CompraDao obj = new CompraDaoImp(con);
            ltsCompra = obj.reporteMes(mes, anio);
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
        return ltsCompra;
    }
    
    public static ArrayList reporteRangoFecha(Object fechaInicio, Object fechaFin) throws Exception {
        Connection con = null;
        ArrayList<Compra> ltsCompra = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            CompraDao obj = new CompraDaoImp(con);
            ltsCompra = obj.reporteRangoFecha(fechaInicio, fechaFin);
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
        return ltsCompra;
    }
}
