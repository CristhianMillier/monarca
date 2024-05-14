package Modelo.BBDD;

import Modelo.Cargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CargoDaoImp implements CargoDao{
    private PreparedStatement pst;
    private Statement st;
    private ResultSet rs;
    private Connection con;
    private Cargo objC;
    
    public CargoDaoImp(Connection con) {
        this.con = con;
    }

    @Override
    public boolean grabar(Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cargo buscarCargo(Object id) throws SQLException {
        try {
            String sql = "SELECT * FROM cargo where idCargo = " + (Integer)id;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objC = new Cargo();
                objC.setId(rs.getInt(1));
                objC.setNombre(rs.getString(2));
                objC.setEstado(rs.getString(3));
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return objC;
    }

    @Override
    public ArrayList obtenerListaCargoCombo() throws SQLException {
        ArrayList<Cargo> ltsCargo = new ArrayList();
        try {
            String sql = "SELECT * FROM cargo where estado = 'Si' order by nombre";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objC = new Cargo();
                objC.setId(rs.getInt(1));
                objC.setNombre(rs.getString(2));
                objC.setEstado(rs.getString(3));
                ltsCargo.add(objC);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsCargo;
    }
    
    @Override
    public ArrayList obtenerListaCargo() throws SQLException {
        ArrayList<Cargo> ltsCargo = new ArrayList();
        try {
            String sql = "SELECT * FROM cargo order by nombre";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objC = new Cargo();
                objC.setId(rs.getInt(1));
                objC.setNombre(rs.getString(2));
                objC.setEstado(rs.getString(3));
                ltsCargo.add(objC);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsCargo;
    }
}
