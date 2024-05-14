package Modelo.Negocio;

import java.sql.Connection;
import Modelo.BBDD.ConexionImp;
import Modelo.BBDD.LoginDao;
import Modelo.BBDD.LoginDaoImp;
import Modelo.Login;

public class LoginBo {
    public static int Logeo(Object user, Object password) throws Exception {
        Connection con = null;
        int id = 0;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            LoginDao obj = new LoginDaoImp(con);
            id = obj.Logeo(user, password);
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
    public static boolean grabarLogin(Object object)throws Exception{
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            LoginDao obj = new LoginDaoImp(con);
            obj.grabar(object);
            con.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        }finally{
            if(con!=null)
                con.close();
        }
    }
    public static boolean eliminarLogin(Object object)throws Exception{
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            LoginDao obj = new LoginDaoImp(con);
            obj.eliminar(object);
            con.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        }finally{
            if(con!=null)
                con.close();
        }
    }
    public static boolean modificarLogin(Object object)throws Exception{
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            LoginDao obj = new LoginDaoImp(con);
            obj.modificar(object);
            con.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        }finally{
            if(con!=null)
                con.close();
        }
    }
    public static boolean modificarLoginPassword(Object object, Object idEmp)throws Exception{
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            LoginDao obj = new LoginDaoImp(con);
            obj.modificarPasword(object, idEmp);
            con.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        }finally{
            if(con!=null)
                con.close();
        }
    }
    public static String buscarEmpleado(Object id)throws Exception{
        Connection con = null;
        String user = "";
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            LoginDao obj = new LoginDaoImp(con);
            user = obj.buscarEmpleado(id);
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        }finally{
            if(con!=null)
                con.close();
        }
        return user;
    }
}
