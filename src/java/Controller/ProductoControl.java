package Controller;

import Modelo.Categoria;
import Modelo.Marca;
import Modelo.Negocio.CategoriaBo;
import Modelo.Negocio.MarcaBo;
import Modelo.Negocio.ProductoBo;
import Modelo.Producto;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Producto")
public class ProductoControl extends HttpServlet {

    private int idPEdit = 0;
    private String descripcion = "";
    private String caducidad = "";
    private int idCategoria = 0;
    private int idMarca = 0;
    private String estado = "";

    private ArrayList<Producto> ltsProducto = new ArrayList();
    private ArrayList<Categoria> ltsCategoria = new ArrayList();
    private ArrayList<Marca> ltsMarca = new ArrayList();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "producto.jsp";
        String accion = request.getParameter("accion");

        switch (accion) {
            case "listar":
                url = "producto.jsp";
                this.obtenerListaCatMar();
                this.obtenerListaProd();
                break;
            case "subEditar":
                idPEdit = Integer.parseInt(request.getParameter("idP"));
                for (Producto obj : ltsProducto) {
                    if (obj.getId() == idPEdit) {
                        request.setAttribute("objProd", obj);
                    }
                }
                url = "producto.jsp";
                break;
            case "eliminar":
                this.eliminar(Integer.parseInt(request.getParameter("idProducto")), request);
                this.obtenerListaProd();
                url = "producto.jsp";
                break;
            case "guardar":
                boolean check = this.validarLlenado(request);
                Producto objPro = new Producto(0, this.descripcion, 0, 0, this.caducidad,
                        new Categoria(this.idCategoria, "", ""), new Marca(this.idMarca, "", ""), this.estado);
                if (check) {
                    if (this.idPEdit == 0) {
                        this.guardar(request, objPro);
                    } else {
                        this.editar(request, objPro);
                    }
                } else {
                    request.setAttribute("errorMsj", "Complete correctamente los campos obligatorios.");
                    request.setAttribute("objProd", objPro);
                }
                this.obtenerListaProd();
                this.limpiar();
                break;
            default:
                url = "producto.jsp";
        }

        request.setAttribute("listaProducto", ltsProducto);
        request.setAttribute("listaMarca", ltsMarca);
        request.setAttribute("listaCategoria", ltsCategoria);

        request.getRequestDispatcher(url).forward(request, response);
    }

    private void obtenerListaProd() {
        ltsProducto = new ArrayList();
        try {
            ltsProducto = ProductoBo.obtenerListaProducto();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void obtenerListaCatMar() {
        ltsCategoria = new ArrayList();
        ltsMarca = new ArrayList();
        try {
            ltsCategoria = CategoriaBo.obtenerListaCategoriaCombo();
            ltsMarca = MarcaBo.obtenerListaMarcaCombo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminar(int idEliminar, HttpServletRequest request) {
        try {
            Producto obj = new Producto();
            obj.setId(idEliminar);
            if (ProductoBo.eliminar(obj)) {
                request.setAttribute("success", "Producto Eliminado Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Eliminar el Producto correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void limpiar() {
        this.idPEdit = 0;
        this.descripcion = "";
        this.caducidad = "";
        this.idCategoria = 0;
        this.idMarca = 0;
        this.estado = "";
    }

    private boolean validarLlenado(HttpServletRequest request) {
        this.descripcion = request.getParameter("descripcionProd");
        this.caducidad = request.getParameter("caducaProd");
        this.idCategoria = Integer.parseInt(request.getParameter("categoria"));
        this.idMarca = Integer.parseInt(request.getParameter("marca"));
        this.estado = request.getParameter("estado");

        if (this.caducidad.trim().equals("")) {
            this.caducidad = this.cagar_Fecha();
            System.out.println(caducidad);
        }
        if (this.descripcion.trim().equals("") || this.idCategoria == 0 
                || this.idMarca == 0 || this.estado.equals("0")) {
            return false;
        } else {
            return true;
        }
    }

    private void guardar(HttpServletRequest request, Producto objPro) {
        try {
            if (ProductoBo.grabar(objPro)) {
                request.setAttribute("success", "Producto Guardado Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Guardar el Producto correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void editar(HttpServletRequest request, Producto objPro) {
        objPro.setId(this.idPEdit);
        try {
            if (ProductoBo.modificar(objPro)) {
                request.setAttribute("success", "Producto Modificado Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Modificar el Producto correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private String cagar_Fecha() {
        Calendar cal = Calendar.getInstance();
        Timestamp fecha_hora = new Timestamp(cal.getTimeInMillis());
        String fechaSistema = String.valueOf(fecha_hora);
        String anio = String.valueOf(Integer.parseInt(fechaSistema.substring(0, 4)) + 2);
        String mes = fechaSistema.substring(5, 7);
        String dia = fechaSistema.substring(8, 10);
        return anio + "-" + mes + "-" + dia;
    }
}
