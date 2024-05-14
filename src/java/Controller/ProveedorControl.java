package Controller;

import Modelo.Negocio.ProveedorBo;
import Modelo.Proveedor;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Proveedor")
public class ProveedorControl extends HttpServlet {

    private int idPro = 0;
    private String razonSocial = "";
    private String Telefono = "";
    private String Ruc = "";
    private String Estado = "";

    private ArrayList<Proveedor> ltsProveedor = new ArrayList();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "proveedor.jsp";
        String accion = request.getParameter("accion");
        switch (accion) {
            case "listar":
                url = "proveedor.jsp";
                this.obtenerListaProveedor();
                break;
            case "subEditar":
                idPro = Integer.parseInt(request.getParameter("idP"));
                for (Proveedor obj : ltsProveedor) {
                    if (obj.getId() == idPro) {
                        request.setAttribute("objPro", obj);
                    }
                }
                url = "proveedor.jsp";
                break;
            case "eliminar":
                this.eliminar(Integer.parseInt(request.getParameter("idProveedor")), request);
                this.obtenerListaProveedor();
                url = "proveedor.jsp";
                break;
            case "guardar":
                boolean check = this.validarLlenado(request);
                Proveedor objPro = new Proveedor(0, this.razonSocial, this.Telefono, this.Ruc, this.Estado);
                if (check) {
                    if (this.idPro == 0) {
                        this.guardar(request, objPro);
                    } else {
                        this.editar(request, objPro);
                    }
                } else {
                    request.setAttribute("errorMsj", "Complete correctamente los campos obligatorios.");
                    request.setAttribute("objPro", objPro);
                }
                this.obtenerListaProveedor();
                this.limpiar();
                url = "proveedor.jsp";
                break;
            default:
                url = "proveedor.jsp";
        }

        request.setAttribute("listaProveedor", ltsProveedor);

        request.getRequestDispatcher(url).forward(request, response);
    }

    private void obtenerListaProveedor() {
        ltsProveedor = new ArrayList();
        try {
            ltsProveedor = ProveedorBo.obtenerListaProveedor();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminar(int idEliminar, HttpServletRequest request) {
        try {
            Proveedor obj = new Proveedor();
            obj.setId(idEliminar);
            if (ProveedorBo.eliminar(obj)) {
                request.setAttribute("success", "Proveedor Eliminado Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Eliminar el Proveedor correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void limpiar() {
        this.idPro = 0;
        this.razonSocial = "";
        this.Telefono = "";
        this.Ruc = "";
        this.Estado = "";
    }

    private boolean validarLlenado(HttpServletRequest request) {
        this.razonSocial = request.getParameter("RazonProve");
        this.Telefono = request.getParameter("telefonoPro");
        this.Ruc = request.getParameter("rucPro");
        this.Estado = request.getParameter("estado");

        if (this.razonSocial.trim().equals("") || this.Telefono.trim().equals("") || this.Ruc.trim().equals("") || this.Estado.equals("0")) {
            return false;
        } else {
            return true;
        }
    }

    private void guardar(HttpServletRequest request, Proveedor objPro) {
        try {
            if (ProveedorBo.grabar(objPro)) {
                request.setAttribute("success", "Proveedor Guardada Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Guardar el Proveedor correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void editar(HttpServletRequest request, Proveedor objPro) {
        objPro.setId(this.idPro);
        try {
            if (ProveedorBo.modificar(objPro)) {
                request.setAttribute("success", "Proveedor Modificada Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Modificar el Proveedor correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
