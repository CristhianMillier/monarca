package Modelo.BBDD;

import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductoDaoImp implements ProductoDao{
    private PreparedStatement pst;
    private Statement st;
    private ResultSet rs;
    private Connection con;
    private Producto objP;
    
    public ProductoDaoImp(Connection con) {
        this.con = con;
    }

    @Override
    public boolean grabar(Object object) throws SQLException {
        objP = (Producto) object;
        try {
            String sql = "insert into producto (idProducto, descripcion, precioVenta, "
                    + "stock, fechaCaducidad, estado, idCategoria, idMarca) VALUES (NULL, '"
                    + objP.getDescripcion() + "',"
                    + objP.getPrecio() + ","
                    + objP.getStock() + ",'"
                    + objP.getFechaCaducidad() + "','"
                    + objP.getEstado() + "', "
                    + objP.getIdCat().getId() + ", "
                    + objP.getIdMarca().getId() + ") ";
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
        objP = (Producto) object;
        try {
            String sql = "UPDATE producto SET estado=? where idProducto = " + objP.getId();
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
        objP = (Producto) object;
        try {
            String sql = "UPDATE producto SET descripcion=?, precioVenta=?, stock=?, fechaCaducidad=?, "
                    + "estado=?, idCategoria=?, idMarca=? where idProducto = " + objP.getId();
            pst = con.prepareStatement(sql);
            pst.setString(1, objP.getDescripcion());
            pst.setDouble(2, objP.getPrecio());
            pst.setInt(3, objP.getStock());
            pst.setString(4, objP.getFechaCaducidad());
            pst.setString(5, objP.getEstado());
            pst.setInt(6, objP.getIdCat().getId());
            pst.setInt(7, objP.getIdMarca().getId());
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }
    
    @Override
    public ArrayList obtenerListaProducto() throws SQLException {
        ArrayList<Producto> ltsProducto = new ArrayList();
        try {
            String sql = "SELECT * FROM producto order by descripcion";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objP = new Producto();
                objP.setId(rs.getInt(1));
                objP.setDescripcion(rs.getString(2));
                objP.setPrecio(rs.getDouble(3));
                objP.setStock(rs.getInt(4));
                objP.setFechaCaducidad(rs.getString(5));
                objP.setEstado(rs.getString(6));
                CategoriaDao objCat = new CategoriaDaoImp(con);
                objP.setIdCat(objCat.buscarIdCategoria(rs.getInt(7)));
                MarcaDao objMar = new MarcaDaoImp(con);
                objP.setIdMarca(objMar.buscarIdMarca(rs.getInt(8)));
                ltsProducto.add(objP);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsProducto;
    }
    
    @Override
    public ArrayList obtenerListaProductoCombo() throws SQLException {
        ArrayList<Producto> ltsProducto = new ArrayList();
        try {
            String sql = "SELECT * FROM producto where estado = 'Si' order by descripcion";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objP = new Producto();
                objP.setId(rs.getInt(1));
                objP.setDescripcion(rs.getString(2));
                objP.setPrecio(rs.getDouble(3));
                objP.setStock(rs.getInt(4));
                objP.setFechaCaducidad(rs.getString(5));
                objP.setEstado(rs.getString(6));
                CategoriaDao objCat = new CategoriaDaoImp(con);
                objP.setIdCat(objCat.buscarIdCategoria(rs.getInt(7)));
                MarcaDao objMar = new MarcaDaoImp(con);
                objP.setIdMarca(objMar.buscarIdMarca(rs.getInt(8)));
                ltsProducto.add(objP);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsProducto;
    }

    @Override
    public Producto buscarIdProducto(Object id) throws SQLException {
        try {
            String sql = "SELECT * FROM producto where idProducto = " + (Integer)id;
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objP = new Producto();
                objP.setId(rs.getInt(1));
                objP.setDescripcion(rs.getString(2));
                objP.setPrecio(rs.getDouble(3));
                objP.setStock(rs.getInt(4));
                objP.setFechaCaducidad(rs.getString(5));
                objP.setEstado(rs.getString(6));
                CategoriaDao objCat = new CategoriaDaoImp(con);
                objP.setIdCat(objCat.buscarIdCategoria(rs.getInt(7)));
                MarcaDao objMar = new MarcaDaoImp(con);
                objP.setIdMarca(objMar.buscarIdMarca(rs.getInt(8)));
                objP.setModificadoProd(false);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return objP;
    }

    @Override
    public ArrayList listaCaducidad() throws SQLException {
        ArrayList<Producto> ltsProducto = new ArrayList();
        try {
            String sql = "SELECT * FROM producto WHERE DATEDIFF (fechaCaducidad, CURDATE()) <= 30 and estado = 'Si' and stock > 0";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objP = new Producto();
                objP.setId(rs.getInt(1));
                objP.setDescripcion(rs.getString(2));
                objP.setPrecio(rs.getDouble(3));
                objP.setStock(rs.getInt(4));
                objP.setFechaCaducidad(rs.getString(5));
                objP.setEstado(rs.getString(6));
                CategoriaDao objCat = new CategoriaDaoImp(con);
                objP.setIdCat(objCat.buscarIdCategoria(rs.getInt(7)));
                MarcaDao objMar = new MarcaDaoImp(con);
                objP.setIdMarca(objMar.buscarIdMarca(rs.getInt(8)));
                ltsProducto.add(objP);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsProducto;
    }

    @Override
    public ArrayList listaStockMinimo() throws SQLException {
        ArrayList<Producto> ltsProducto = new ArrayList();
        try {
            String sql = "SELECT P.idProducto, P.descripcion, P.precioVenta, P.stock, P.fechaCaducidad, P.estado, P.idCategoria, P.idMarca, Pr.telefono "
                    + "FROM producto as P inner join detallecompra as D on D.idProducto = P.idProducto inner join compra as C on D.idCompra = C.idCompra "
                    + "inner join proveedor as Pr on C.idProveedor = Pr.idProveedor where P.stock <= 10 and P.stock > 0 and P.estado = 'Si'";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                objP = new Producto();
                objP.setId(rs.getInt(1));
                objP.setDescripcion(rs.getString(2));
                objP.setPrecio(rs.getDouble(3));
                objP.setStock(rs.getInt(4));
                objP.setFechaCaducidad(rs.getString(5));
                objP.setEstado(rs.getString(6));
                CategoriaDao objCat = new CategoriaDaoImp(con);
                objP.setIdCat(objCat.buscarIdCategoria(rs.getInt(7)));
                MarcaDao objMar = new MarcaDaoImp(con);
                objP.setIdMarca(objMar.buscarIdMarca(rs.getInt(8)));
                objP.setNroProv(rs.getString(9));
                ltsProducto.add(objP);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return ltsProducto;
    }

    @Override
    public boolean actualizarProducto(Object cantidad, Object idProd) throws SQLException {
        int stock = 0;
        try {
            String modificar = "select stock from producto where idProducto = " + (Integer) idProd;
            st = con.createStatement();
            rs = st.executeQuery(modificar);
            while (rs.next()) {
                stock = rs.getInt(1);
            }
            stock = stock + (Integer) cantidad;
            String sql = "UPDATE  producto SET stock=? WHERE idProducto = " + (Integer) idProd;
            pst = con.prepareStatement(sql);
            pst.setInt(1, stock);
            pst.execute();
            st.close();
            rs.close();
            pst.close();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean actualizarProductoPrecio(Object precio, Object idProd) throws SQLException {
        double venta = (Double) precio;
        try {
            String sql = "UPDATE producto SET precioVenta=? WHERE idProducto = " + (Integer) idProd;
            pst = con.prepareStatement(sql);
            pst.setDouble(1, venta);
            pst.execute();
            pst.close();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public int cantProducto() throws SQLException {
        int cant = 0;
        try {
            String sql = "SELECT count(*) FROM producto";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                cant = rs.getInt(1);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return cant;
    }
}
