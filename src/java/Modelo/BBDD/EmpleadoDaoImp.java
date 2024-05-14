package Modelo.BBDD;

import Modelo.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmpleadoDaoImp implements EmpleadoDao{
    private PreparedStatement pst;
    private Statement st;
    private ResultSet rs;
    private Connection con;
    private Empleado objP;
    
    public EmpleadoDaoImp(Connection con) {
        this.con = con;
    }

    @Override
    public boolean grabar(Object object) throws SQLException {
        objP = (Empleado) object;
        try {
            String sql = "insert into empleado (idEmpleado, nombre, apellido, telefono, direccion, dni, estado, "
                    + "idCargo, correo) VALUES (NULL, '" + objP.getNombre() + "','"
                    + objP.getApellido() + "','"
                    + objP.getTelefono() + "','"
                    + objP.getDireccion() + "','"
                    + objP.getDni() + "','"
                    + objP.getEstado()  + "',"
                    + objP.getIdCargo().getId() + ",'"
                    + objP.getCorreo() + "') ";
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
        objP = (Empleado) object;
        try {
            String sql = "UPDATE empleado SET estado=? where idEmpleado = " + objP.getId();
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
        objP = (Empleado) object;
        try {
            String sql = "UPDATE empleado SET nombre=?, apellido=?, telefono=?, direccion=?, "
                    + "dni=?, estado=?, idCargo=?, correo=? where idEmpleado = " + objP.getId();
            pst = con.prepareStatement(sql);
            pst.setString(1, objP.getNombre());
            pst.setString(2, objP.getApellido());
            pst.setString(3, objP.getTelefono());
            pst.setString(4, objP.getDireccion());
            pst.setString(5, objP.getDni());
            pst.setString(6, objP.getEstado());
            pst.setInt(7, objP.getIdCargo().getId());
            pst.setString(8, objP.getCorreo());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public ArrayList obtenerListaEmpleadoCombo() throws SQLException {
        ArrayList<Empleado> ltsEmpleado = new ArrayList();
        try {
            String sql = "SELECT * FROM empleado where estado = 'Si' order by apellido";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objP = new Empleado();
                objP.setId(rs.getInt(1));
                objP.setNombre(rs.getString(2));
                objP.setApellido(rs.getString(3));
                objP.setTelefono(rs.getString(4));
                objP.setDireccion(rs.getString(5));
                objP.setDni(rs.getString(6));
                objP.setEstado(rs.getString(7));
                CargoDao objGra = new CargoDaoImp(con);
                objP.setIdCargo(objGra.buscarCargo(rs.getInt(8)));
                objP.setCorreo(rs.getString(9));
                ltsEmpleado.add(objP);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsEmpleado;
    }
    
    @Override
    public ArrayList obtenerListaEmpleado() throws SQLException {
        ArrayList<Empleado> ltsEmpleado = new ArrayList();
        try {
            String sql = "SELECT * FROM empleado order by apellido";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objP = new Empleado();
                objP.setId(rs.getInt(1));
                objP.setNombre(rs.getString(2));
                objP.setApellido(rs.getString(3));
                objP.setTelefono(rs.getString(4));
                objP.setDireccion(rs.getString(5));
                objP.setDni(rs.getString(6));
                objP.setEstado(rs.getString(7));
                objP.setCorreo(rs.getString(9));
                CargoDao objGra = new CargoDaoImp(con);
                objP.setIdCargo(objGra.buscarCargo(rs.getInt(8)));
                ltsEmpleado.add(objP);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsEmpleado;
    }

    @Override
    public int obtenerUltimoId(String dni) throws SQLException {
        int id = 0;
        try {
            String sql = "select idEmpleado from empleado where dni = '" + dni + "'";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getInt(1);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return id;
    }

    @Override
    public Empleado buscarIdEmpleado(Object id) throws SQLException {
        try {
            String sql = "SELECT E.idEmpleado, E.nombre, E.apellido, E.telefono, E.direccion, E.dni, E.estado, E.idCargo, E.correo, L.user "
                    + "FROM empleado as E inner join login as L "
                    + "on L.idEmpleado = E.idEmpleado where E.idEmpleado =" + (Integer)id;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objP = new Empleado();
                objP.setId(rs.getInt(1));
                objP.setNombre(rs.getString(2));
                objP.setApellido(rs.getString(3));
                objP.setTelefono(rs.getString(4));
                objP.setDireccion(rs.getString(5));
                objP.setDni(rs.getString(6));
                objP.setEstado(rs.getString(7));
                CargoDao objGra = new CargoDaoImp(con);
                objP.setIdCargo(objGra.buscarCargo(rs.getInt(8)));
                objP.setCorreo(rs.getString(9));
                objP.setUser(rs.getString(10));
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return objP;
    }

    @Override
    public boolean grabarUser(Object object) throws SQLException {
        objP = (Empleado) object;
        try {
            String sql = "UPDATE empleado SET nombre=?, apellido=?, telefono=?, direccion=? "
                    + "where idEmpleado = " + objP.getId();
            pst = con.prepareStatement(sql);
            pst.setString(1, objP.getNombre());
            pst.setString(2, objP.getApellido());
            pst.setString(3, objP.getTelefono());
            pst.setString(4, objP.getDireccion());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }
}
