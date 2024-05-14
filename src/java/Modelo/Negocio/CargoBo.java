package Modelo.Negocio;

import Modelo.BBDD.CargoDao;
import Modelo.BBDD.CargoDaoImp;
import Modelo.BBDD.ConexionImp;
import Modelo.Cargo;
import java.sql.Connection;
import java.util.ArrayList;

public class CargoBo {
    public static Cargo buscarCargo(Object id) throws Exception {
        Connection con = null;
        Cargo objC = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            CargoDao obj = new CargoDaoImp(con);
            objC = obj.buscarCargo(id);
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
    public static ArrayList obtenerListaCargo() throws Exception {
        Connection con = null;
        ArrayList<Cargo> ltsCargo = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            CargoDao obj = new CargoDaoImp(con);
            ltsCargo = obj.obtenerListaCargo();
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
        return ltsCargo;
    }
    
    public static ArrayList obtenerListaCargoCombo() throws Exception {
        Connection con = null;
        ArrayList<Cargo> ltsCargo = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            CargoDao obj = new CargoDaoImp(con);
            ltsCargo = obj.obtenerListaCargoCombo();
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
        return ltsCargo;
    }
}
