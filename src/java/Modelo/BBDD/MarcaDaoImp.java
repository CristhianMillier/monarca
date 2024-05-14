package Modelo.BBDD;

import Modelo.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MarcaDaoImp implements MarcaDao{
    private PreparedStatement pst;
    private Statement st;
    private ResultSet rs;
    private Connection con;
    private Marca objM;

    public MarcaDaoImp(Connection con) {
        this.con = con;
    }

    @Override
    public boolean grabar(Object object) throws SQLException {
        objM = (Marca) object;
        try {
            String sql = "insert into marca (idMarca, nombre, estado) VALUES (NULL, '"
                    + objM.getNombre()+ "','"
                    + objM.getEstado() + "') ";
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
        objM = (Marca) object;
        try {
            String sql = "UPDATE marca SET estado=? where idMarca = " + objM.getId();
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
        objM = (Marca) object;
        try {
            String sql = "UPDATE marca SET nombre=?, estado=? where idMarca = " + objM.getId();
            pst = con.prepareStatement(sql);
            pst.setString(1, objM.getNombre());
            pst.setString(2, objM.getEstado());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public ArrayList obtenerListaMarcaCombo() throws SQLException {
        ArrayList<Marca> ltsMarca = new ArrayList();
        try {
            String sql = "SELECT * FROM marca where estado = 'Si' order by nombre";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objM = new Marca();
                objM.setId(rs.getInt(1));
                objM.setNombre(rs.getString(2));
                objM.setEstado(rs.getString(3));
                ltsMarca.add(objM);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsMarca;
    }
    
    @Override
    public ArrayList obtenerListaMarca() throws SQLException {
        ArrayList<Marca> ltsMarca = new ArrayList();
        try {
            String sql = "SELECT * FROM marca order by nombre";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objM = new Marca();
                objM.setId(rs.getInt(1));
                objM.setNombre(rs.getString(2));
                objM.setEstado(rs.getString(3));
                ltsMarca.add(objM);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsMarca;
    }

    @Override
    public Marca buscarIdMarca(Object id) throws SQLException {
        try {
            String sql = "SELECT * FROM marca where idMarca = " + (Integer)id;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objM = new Marca();
                objM.setId(rs.getInt(1));
                objM.setNombre(rs.getString(2));
                objM.setEstado(rs.getString(3));
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return objM;
    }
}
