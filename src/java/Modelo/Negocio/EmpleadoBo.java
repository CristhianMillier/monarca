package Modelo.Negocio;

import Modelo.BBDD.ConexionImp;
import Modelo.BBDD.EmpleadoDao;
import Modelo.BBDD.EmpleadoDaoImp;
import Modelo.Empleado;
import java.sql.Connection;
import java.util.ArrayList;

public class EmpleadoBo {
    public static boolean grabar(Object object) throws Exception {
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            EmpleadoDao obj = new EmpleadoDaoImp(con);
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
            EmpleadoDao obj = new EmpleadoDaoImp(con);
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
            EmpleadoDao obj = new EmpleadoDaoImp(con);
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
    public static boolean modificarUser(Object object) throws Exception {
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            EmpleadoDao obj = new EmpleadoDaoImp(con);
            obj.grabarUser(object);
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
    public static ArrayList obtenerListaEmpleado() throws Exception {
        Connection con = null;
        ArrayList<Empleado> ltsEmpleado = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            EmpleadoDao obj = new EmpleadoDaoImp(con);
            ltsEmpleado = obj.obtenerListaEmpleado();
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
        return ltsEmpleado;
    }
    public static ArrayList obtenerListaEmpleadoCombo() throws Exception {
        Connection con = null;
        ArrayList<Empleado> ltsEmpleado = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            EmpleadoDao obj = new EmpleadoDaoImp(con);
            ltsEmpleado = obj.obtenerListaEmpleadoCombo();
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
        return ltsEmpleado;
    }
    public static int obtenerUltimoId(String dni) throws Exception {
        Connection con = null;
        int id = 0;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            EmpleadoDao obj = new EmpleadoDaoImp(con);
            id  = obj.obtenerUltimoId(dni);
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
    public static Empleado buscarIdEmpleado(Object id) throws Exception {
        Connection con = null;
        Empleado objE = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            EmpleadoDao obj = new EmpleadoDaoImp(con);
            objE  = obj.buscarIdEmpleado(id);
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
        return objE;
    }
}
