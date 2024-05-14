package Modelo.Negocio;

import Modelo.BBDD.ConexionImp;
import Modelo.BBDD.ProveedorDao;
import Modelo.BBDD.ProveedorDaoImp;
import Modelo.Proveedor;
import java.sql.Connection;
import java.util.ArrayList;

public class ProveedorBo {

    public static boolean grabar(Object object) throws Exception {
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            ProveedorDao obj = new ProveedorDaoImp(con);
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
            ProveedorDao obj = new ProveedorDaoImp(con);
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
            ProveedorDao obj = new ProveedorDaoImp(con);
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

    public static ArrayList obtenerListaProveedor() throws Exception {
        Connection con = null;
        ArrayList<Proveedor> ltsProveedor = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            ProveedorDao objPv = new ProveedorDaoImp(con);
            ltsProveedor = objPv.obtenerListaProveedor();
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
        return ltsProveedor;
    }

    public static ArrayList obtenerListaProveedorCombo() throws Exception {
        Connection con = null;
        ArrayList<Proveedor> ltsProveedor = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            ProveedorDao objPv = new ProveedorDaoImp(con);
            ltsProveedor = objPv.obtenerListaProveedorCombo();
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
        return ltsProveedor;
    }

    public static Proveedor buscarIdProveedor(Object id) throws Exception {
        Connection con = null;
        Proveedor objPv = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            ProveedorDao obj = new ProveedorDaoImp(con);
            objPv = obj.buscarIdProveedor(id);
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
        return objPv;
    }

    public static int cantProveedor()  throws Exception {
        Connection con = null;
        int cant = 0;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            ProveedorDao obj = new ProveedorDaoImp(con);
            cant = obj.cantProveedor();
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
        return cant;
    }
}
