package Modelo.Negocio;

import Modelo.BBDD.CategoriaDao;
import Modelo.BBDD.CategoriaDaoImp;
import Modelo.BBDD.ConexionImp;
import Modelo.Categoria;
import java.sql.Connection;
import java.util.ArrayList;

public class CategoriaBo {
    public static Categoria buscarCategoriargo(Object id) throws Exception {
        Connection con = null;
        Categoria objC = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            CategoriaDao obj = new CategoriaDaoImp(con);
            objC = obj.buscarIdCategoria(id);
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
    public static ArrayList obtenerListaCategoria() throws Exception {
        Connection con = null;
        ArrayList<Categoria> ltsCategoria = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            CategoriaDao obj = new CategoriaDaoImp(con);
            ltsCategoria = obj.obtenerListaCategoria();
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
        return ltsCategoria;
    }
    public static ArrayList obtenerListaCategoriaCombo() throws Exception {
        Connection con = null;
        ArrayList<Categoria> ltsCategoria = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            CategoriaDao obj = new CategoriaDaoImp(con);
            ltsCategoria = obj.obtenerListaCategoriaCombo();
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
        return ltsCategoria;
    }
    public static boolean grabar(Object object) throws Exception {
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            CategoriaDao obj = new CategoriaDaoImp(con);
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
            CategoriaDao obj = new CategoriaDaoImp(con);
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
            CategoriaDao obj = new CategoriaDaoImp(con);
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
