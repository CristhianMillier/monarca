package Modelo.Negocio;

import Modelo.BBDD.ConexionImp;
import Modelo.BBDD.ProductoDao;
import Modelo.BBDD.ProductoDaoImp;
import Modelo.Producto;
import java.sql.Connection;
import java.util.ArrayList;

public class ProductoBo {
    public static boolean grabar(Object object) throws Exception {
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            ProductoDao obj = new ProductoDaoImp(con);
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
            ProductoDao obj = new ProductoDaoImp(con);
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
            ProductoDao obj = new ProductoDaoImp(con);
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
    public static ArrayList obtenerListaProducto() throws Exception {
        Connection con = null;
        ArrayList<Producto> ltsProducto = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            ProductoDao obj = new ProductoDaoImp(con);
            ltsProducto = obj.obtenerListaProducto();
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
        return ltsProducto;
    }
    public static ArrayList obtenerListaProductoCombo() throws Exception {
        Connection con = null;
        ArrayList<Producto> ltsProducto = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            ProductoDao obj = new ProductoDaoImp(con);
            ltsProducto = obj.obtenerListaProductoCombo();
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
        return ltsProducto;
    }
    public static Producto buscarIdProducto(Object id) throws Exception {
        Connection con = null;
        Producto objProd = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            ProductoDao obj = new ProductoDaoImp(con);
            objProd = obj.buscarIdProducto(id);
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
        return objProd;
    }
    public static ArrayList listaCaducidad() throws Exception {
        Connection con = null;
        ArrayList<Producto> ltsProducto = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            ProductoDao obj = new ProductoDaoImp(con);
            ltsProducto = obj.listaCaducidad();
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
        return ltsProducto;
    }
    public static ArrayList listaStockMinimo() throws Exception {
        Connection con = null;
        ArrayList<Producto> ltsProducto = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            ProductoDao obj = new ProductoDaoImp(con);
            ltsProducto = obj.listaStockMinimo();
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
        return ltsProducto;
    }
    public static boolean actualizarProducto(Object cantidad, Object idProd) throws Exception {
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            ProductoDao obj = new ProductoDaoImp(con);
            obj.actualizarProducto(cantidad, idProd);
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
    public static boolean actualizarProductoPrecio(Object precio, Object idProd) throws Exception {
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            ProductoDao obj = new ProductoDaoImp(con);
            obj.actualizarProductoPrecio(precio, idProd);
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
    public static int cantProducto() throws Exception {
        Connection con = null;
        int cant = 0;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            ProductoDao obj = new ProductoDaoImp(con);
            cant = obj.cantProducto();
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
