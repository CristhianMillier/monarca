package Controller;

import Modelo.Negocio.CompraBo;
import Modelo.Negocio.DetVentaBo;
import Modelo.Negocio.VentaBo;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Reporte")
public class ReportesControl extends HttpServlet {

    private String cargo = "";
    private ArrayList ltsLista;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";
        String accion = request.getParameter("accion");
        switch (accion) {
            case "compra":
                cargo = request.getParameter("cargo");
                url = "repMesCompra.jsp";
                break;
            case "buscarRepComp":
                if (request.getParameter("mes").trim().equals("") || request.getParameter("anio").trim().equals("")) {
                    request.setAttribute("errorMsj", "Ingrese los campos obligatorios para el Reporte Mensual!!");
                } else {
                    this.reporteCompraMes(request);
                }
                url = "repMesCompra.jsp";
                break;
            case "buscarRepCompRang":
                if (request.getParameter("fechaInicio").trim().equals("") || request.getParameter("fechaFin").trim().equals("")) {
                    request.setAttribute("errorMsj", "Ingrese los campos obligatorios para el Reporte Por Rango de Fecha!!");
                } else {
                    this.reporteCompraRangFecha(request);
                }
                url = "repMesCompra.jsp";
                break;
            case "producto":
                cargo = request.getParameter("cargo");
                url = "reporteProductoMasVendido.jsp";
                break;
            case "buscarRepProducto":
                this.reporteProductoMasVendido(request);
                url = "reporteProductoMasVendido.jsp";
                break;
            case "Venta":
                cargo = request.getParameter("cargo");
                url = "ReporteVenta.jsp";
                break;
            case "buscarRepVentDia":
                this.reporteVentaDia(request);
                url = "ReporteVenta.jsp";
                break;

            case "buscarRepVentRango":
                this.reporteVentaRango(request);
                url = "ReporteVenta.jsp";
                break;
            case "buscarRepVentMensual":
                this.reporteVentaMensual(request);
                url = "ReporteVenta.jsp";
                break;
        }
        request.setAttribute("lista", ltsLista);
        request.setAttribute("cargo", cargo);
        ltsLista = new ArrayList();
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void reporteCompraMes(HttpServletRequest request) {
        try {
            ltsLista = CompraBo.reporteMes(request.getParameter("mes"), request.getParameter("anio"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void reporteCompraRangFecha(HttpServletRequest request) {
        try {
            ltsLista = CompraBo.reporteRangoFecha(request.getParameter("fechaInicio"), request.getParameter("fechaFin"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void reporteProductoMasVendido(HttpServletRequest request) {
        try {
            ltsLista = DetVentaBo.reporteProductoMasVendido(request.getParameter("mes"), request.getParameter("anio"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void reporteVentaDia(HttpServletRequest request) {
        try {
            ltsLista = VentaBo.reporteDia(request.getParameter("Fecha"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void reporteVentaRango(HttpServletRequest request) {
        try {
            ltsLista = VentaBo.reporteRango(request.getParameter("FechaIni"), request.getParameter("FechaFin"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void reporteVentaMensual(HttpServletRequest request) {
        try {
            ltsLista = VentaBo.reporteMensual(request.getParameter("mes"), request.getParameter("anio"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
