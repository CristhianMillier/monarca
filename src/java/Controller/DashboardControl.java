package Controller;

import Modelo.Empleado;
import Modelo.Negocio.EmpleadoBo;
import Modelo.Negocio.ProductoBo;
import Modelo.Negocio.ProveedorBo;
import Modelo.Negocio.VentaBo;
import Modelo.Venta;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Dashboard")
public class DashboardControl extends HttpServlet{
    private int prov = 0, vent = 0, prod = 0;
    private ArrayList<Venta> ltsVenta = new ArrayList();
    private ArrayList<Empleado> ltsEmpleado = new ArrayList();
    
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "dashboard.jsp";
        String accion = request.getParameter("accion");
        switch (accion) {
            case "listar":
                url = "dashboard.jsp";
                this.obtenerDatos(request);
                break;
            case "buscar":
                url = "dashboard.jsp";
                this.obtenerDatos(request);
                this.buscarDashboar(request);
                break;
            default:
                url = "dashboard.jsp";
        }
        request.setAttribute("proveedor", prov);
        request.setAttribute("venta", vent);
        request.setAttribute("producto", prod);
        request.setAttribute("lista", ltsVenta);
        request.setAttribute("listaEmpleado", ltsEmpleado);
        
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void obtenerDatos(HttpServletRequest request) {
        try {
            prov = ProveedorBo.cantProveedor();
            vent = VentaBo.cantVenta();
            prod = ProductoBo.cantProducto();
            ltsVenta = VentaBo.obtenerDashboar();
            ltsEmpleado = EmpleadoBo.obtenerListaEmpleadoCombo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            request.setAttribute("error", "No se pudo conectar con la Base de Datos. Intentelo más tarde!!");
        }
    }

    private void buscarDashboar(HttpServletRequest request) {
        String inicio = request.getParameter("fechaInicio");
        String fin = request.getParameter("fechaFin");
        int idEmpl = Integer.parseInt(request.getParameter("empleado"));
        try {
            ltsVenta = VentaBo.obtenerDashboarBusca(inicio, fin, idEmpl);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            request.setAttribute("error", "No se pudo conectar con la Base de Datos. Intentelo más tarde!!");
        }
    }
}
