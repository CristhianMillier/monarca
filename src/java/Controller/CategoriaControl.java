package Controller;

import Modelo.Categoria;
import Modelo.Negocio.CategoriaBo;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Categoria")
public class CategoriaControl extends HttpServlet{
    private int idCat = 0;
    private String nombre = "";
    private String estado = "";

    private ArrayList<Categoria> ltsCategoria = new ArrayList();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "categoria.jsp";
        String accion = request.getParameter("accion");
        switch (accion) {
            case "listar":
                url = "categoria.jsp";
                this.obtenerListaCategoria();
                break;
            case "subEditar":
                idCat = Integer.parseInt(request.getParameter("idC"));
                for (Categoria obj : ltsCategoria) {
                    if (obj.getId() == idCat) {
                        request.setAttribute("objCat", obj);
                    }
                }
                url = "categoria.jsp";
                break;
            case "eliminar":
                this.eliminar(Integer.parseInt(request.getParameter("idCategoria")), request);
                this.obtenerListaCategoria();
                url = "categoria.jsp";
                break;
            case "guardar":
                boolean check = this.validarLlenado(request);
                Categoria objC = new Categoria(0, this.nombre, this.estado);
                if (check) {
                    if (this.idCat == 0) {
                        this.guardar(request, objC);
                    } else {
                        this.editar(request, objC);
                    }
                } else {
                    request.setAttribute("errorMsj", "Complete correctamente los campos obligatorios.");
                    request.setAttribute("objMar", objC);
                }
                this.obtenerListaCategoria();
                this.limpiar();
                url = "categoria.jsp";
                break;
            default:
                url = "categoria.jsp";
        }
        
        request.setAttribute("listaCategoria", ltsCategoria);
        
        request.getRequestDispatcher(url).forward(request, response);
    }
    
    private void obtenerListaCategoria() {
        ltsCategoria = new ArrayList();
        try {
            ltsCategoria = CategoriaBo.obtenerListaCategoria();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void eliminar(int idEliminar, HttpServletRequest request) {
        try {
            Categoria obj = new Categoria();
            obj.setId(idEliminar);
            if (CategoriaBo.eliminar(obj)) {
                request.setAttribute("success", "Categoría Eliminada Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Eliminar la Categoría correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void limpiar() {
        this.idCat = 0;
        this.nombre = "";
        this.estado = "";
    }
    private boolean validarLlenado(HttpServletRequest request) {
        this.nombre = request.getParameter("nombreCat");
        this.estado = request.getParameter("estado");

        if (this.nombre.trim().equals("") || this.estado.equals("0")) {
            return false;
        } else {
            return true;
        }
    }
    private void guardar(HttpServletRequest request, Categoria objCat) {
        try {
            if (CategoriaBo.grabar(objCat)) {
                request.setAttribute("success", "Categoría Guardada Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Guardar la Categoría correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void editar(HttpServletRequest request, Categoria objCat) {
        objCat.setId(this.idCat);
        try {
            if (CategoriaBo.modificar(objCat)) {
                request.setAttribute("success", "Categoría Modificada Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Modificar la Categoría correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
