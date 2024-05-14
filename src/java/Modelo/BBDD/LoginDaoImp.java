package Modelo.BBDD;

import Modelo.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDaoImp implements LoginDao{
    private PreparedStatement pst;
    private Statement st;
    private ResultSet rs;
    private Connection con;
    private Login objL;

    public LoginDaoImp(Connection con) {
        this.con = con;
    }
    
    @Override
    public int Logeo(Object user, Object password) throws SQLException {
        int idP = 0;
        try {
            String sql = "SELECT idEmpleado FROM login "
                    + "where user = '" + (String) user + "' and password = '" + (String) password + "' and estado = 'Si'";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                idP = rs.getInt(1);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return idP;
    }

    @Override
    public String buscarEmpleado(Object id) throws SQLException {
        String user = "";
        try {
            String sql = "SELECT user FROM login where idEmpleado = " + (Integer) id;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                user = rs.getString(1);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return user;
    }

    @Override
    public boolean grabar(Object object) throws SQLException {
        objL = (Login) object;
        try {
            String sql = "insert into login (idLogin, user, password, estado, idEmpleado) VALUES (NULL, '"
                    + objL.getUser() + "', '"
                    + objL.getPassword() + "', '"
                    + objL.getEstado() + "', "
                    + objL.getIdEmp() + ") ";
            pst = con.prepareStatement(sql);
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean eliminar(Object object) throws SQLException {
        try {
            String sql = "UPDATE login SET estado=? where idEmpleado = " + (Integer) object;
            pst = con.prepareStatement(sql);
            pst.setString(1, "No");
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean modificar(Object object) throws SQLException {
        objL = (Login) object;
        try {
            String sql = "UPDATE login SET user=?, estado=? where idEmpleado = " + objL.getIdEmp();
            pst = con.prepareStatement(sql);
            pst.setString(1, objL.getUser());
            pst.setString(2, objL.getEstado());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean modificarPasword(Object object, Object idEmp) throws SQLException {
        String password = (String) object;
        try {
            String sql = "UPDATE login SET password=? where idEmpleado = " + (Integer) idEmp;
            pst = con.prepareStatement(sql);
            pst.setString(1, password);
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }
}
