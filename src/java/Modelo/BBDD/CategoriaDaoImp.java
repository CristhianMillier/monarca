package Modelo.BBDD;

import Modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoriaDaoImp implements CategoriaDao{
    private PreparedStatement pst;
    private Statement st;
    private ResultSet rs;
    private Connection con;
    private Categoria objC;

    public CategoriaDaoImp(Connection con) {
        this.con = con;
    }

    @Override
    public boolean grabar(Object object) throws SQLException {
        objC = (Categoria) object;
        try {
            String sql = "insert into categoria (idCategoria, nombre, estado) VALUES (NULL, '"
                    + objC.getNombre()+ "','"
                    + objC.getEstado() + "') ";
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
        objC = (Categoria) object;
        try {
            String sql = "UPDATE categoria SET estado=? where idCategoria = " + objC.getId();
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
        objC = (Categoria) object;
        try {
            String sql = "UPDATE categoria SET nombre=?, estado=? where idCategoria = " + objC.getId();
            pst = con.prepareStatement(sql);
            pst.setString(1, objC.getNombre());
            pst.setString(2, objC.getEstado());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public ArrayList obtenerListaCategoriaCombo() throws SQLException {
        ArrayList<Categoria> ltsCategoria = new ArrayList();
        try {
            String sql = "SELECT * FROM categoria where estado = 'Si' order by nombre";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objC = new Categoria();
                objC.setId(rs.getInt(1));
                objC.setNombre(rs.getString(2));
                objC.setEstado(rs.getString(3));
                ltsCategoria.add(objC);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsCategoria;
    }
    
    @Override
    public ArrayList obtenerListaCategoria() throws SQLException {
        ArrayList<Categoria> ltsCategoria = new ArrayList();
        try {
            String sql = "SELECT * FROM categoria order by nombre";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objC = new Categoria();
                objC.setId(rs.getInt(1));
                objC.setNombre(rs.getString(2));
                objC.setEstado(rs.getString(3));
                ltsCategoria.add(objC);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsCategoria;
    }

    @Override
    public Categoria buscarIdCategoria(Object id) throws SQLException {
        try {
            String sql = "SELECT * FROM categoria where idCategoria = " + (Integer)id;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objC = new Categoria();
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
}
