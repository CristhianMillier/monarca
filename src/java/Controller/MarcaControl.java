package Controller;

import Modelo.Marca;
import Modelo.Negocio.MarcaBo;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Marca")
public class MarcaControl extends HttpServlet {

    private int idMar = 0;
    private String nombre = "";
    private String estado = "";

    private ArrayList<Marca> ltsMarca = new ArrayList();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "marca.jsp";
        String accion = request.getParameter("accion");
        switch (accion) {
            case "listar":
                url = "marca.jsp";
                this.obtenerListaMarca();
                break;
            case "subEditar":
                idMar = Integer.parseInt(request.getParameter("idM"));
                for (Marca obj : ltsMarca) {
                    if (obj.getId() == idMar) {
                        request.setAttribute("objMar", obj);
                    }
                }
                url = "marca.jsp";
                break;
            case "eliminar":
                this.eliminar(Integer.parseInt(request.getParameter("idMarca")), request);
                this.obtenerListaMarca();
                url = "marca.jsp";
                break;
            case "guardar":
                boolean check = this.validarLlenado(request);
                Marca objM = new Marca(0, this.nombre, this.estado);
                if (check) {
                    if (this.idMar == 0) {
                        this.guardar(request, objM);
                    } else {
                        this.editar(request, objM);
                    }
                } else {
                    request.setAttribute("errorMsj", "Complete correctamente los campos obligatorios.");
                    request.setAttribute("objMar", objM);
                }
                this.obtenerListaMarca();
                this.limpiar();
                url = "marca.jsp";
                break;
            default:
                url = "marca.jsp";
        }
        
        request.setAttribute("listaMarca", ltsMarca);
        
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void obtenerListaMarca() {
        ltsMarca = new ArrayList();
        try {
            ltsMarca = MarcaBo.obtenerListaMarca();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void eliminar(int idEliminar, HttpServletRequest request) {
        try {
            Marca obj = new Marca();
            obj.setId(idEliminar);
            if (MarcaBo.eliminar(obj)) {
                request.setAttribute("success", "Marca Eliminada Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Eliminar la Marca correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void limpiar() {
        this.idMar = 0;
        this.nombre = "";
        this.estado = "";
    }
    private boolean validarLlenado(HttpServletRequest request) {
        this.nombre = request.getParameter("nombreMar");
        this.estado = request.getParameter("estado");

        if (this.nombre.trim().equals("") || this.estado.equals("0")) {
            return false;
        } else {
            return true;
        }
    }
    private void guardar(HttpServletRequest request, Marca objMar) {
        try {
            if (MarcaBo.grabar(objMar)) {
                request.setAttribute("success", "Marca Guardada Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Guardar la Marca correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void editar(HttpServletRequest request, Marca objMar) {
        objMar.setId(this.idMar);
        try {
            if (MarcaBo.modificar(objMar)) {
                request.setAttribute("success", "Marca Modificada Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Modificar la Marca correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
