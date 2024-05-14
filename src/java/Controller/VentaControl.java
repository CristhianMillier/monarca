package Controller;

import Modelo.Cliente;
import Modelo.DetVenta;
import Modelo.Empleado;
import Modelo.Negocio.DetVentaBo;
import Modelo.Negocio.ProductoBo;
import Modelo.Negocio.VentaBo;
import Modelo.Producto;
import Modelo.TicketImprimir;
import Modelo.Venta;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.BaseColor;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.Calendar;

@WebServlet("/Venta")
public class VentaControl extends HttpServlet {

    private String nroTicket = "";
    private double descuento = 0;
    private String fecha = "";
    private double pago = 0.0;
    private int cantidad = 0;
    private ArrayList<DetVenta> ltsDetVenta = new ArrayList();
    private Venta objVent = null;
    private Producto objProd = null;
    private DetVenta objDetV = null;
    private int idVentEdit = 0;
    private String cargo;

    private int idEmpleado = 0;
    private ArrayList<Producto> ltsProducto = new ArrayList();
    private ArrayList<Venta> ltsVenta = new ArrayList();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "venta.jsp";
        String accion = request.getParameter("accion");

        switch (accion) {
            case "listar":
                url = "venta.jsp";
                this.obtenerListaVenta();
                this.obtenerListaProd();
                this.limpiar();
                this.idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
                cargo = request.getParameter("cargo");
                break;
            case "cargarDatos":
                this.obtenerValores(request);
                this.buscarProducto(request);
                request.setAttribute("objVent", objVent);
                request.setAttribute("ProductoV", objProd);
                request.setAttribute("verModal", "VERMODAL");
                request.setAttribute("listaDetVenta", ltsDetVenta);
                request.setAttribute("pago", pago);
                break;
            case "agregar":
                pago = 0.0;
                this.obtenerValores(request);
                if (this.buscar() != 1) {
                    cantidad = Integer.parseInt(request.getParameter("cantidad"));
                    if (cantidad <= objProd.getStock()) {
                        objDetV = new DetVenta();
                        objDetV.setCantidad(cantidad);
                        objDetV.setSubTotal(objProd.getPrecio() * cantidad);
                        objProd.setModificadoProd(true);
                        objDetV.setIdProd(objProd);

                        ltsDetVenta.add(objDetV);
                    } else {
                        request.setAttribute("errorMsj", "Stock no Disponible.");
                    }
                } else {
                    request.setAttribute("errorMsj", "El producto ya se encuentra en la lista.");
                }
                for (int i = 0; i < ltsDetVenta.size(); i++) {
                    pago += ltsDetVenta.get(i).getSubTotal();
                }
                pago = pago - descuento;
                request.setAttribute("pago", pago);
                request.setAttribute("objVent", objVent);
                request.setAttribute("verModal", "VERMODAL");
                request.setAttribute("listaDetVenta", ltsDetVenta);
                url = "venta.jsp";
                objProd = null;
                break;
            case "editarCarrito":
                pago = 0.0;
                this.obtenerListaProd();
                for (int i = 0; i < ltsDetVenta.size(); i++) {
                    if (ltsDetVenta.get(i).getId() == Integer.parseInt(request.getParameter("idProdE"))) {
                        try {
                            if (idVentEdit != 0) {
                                ProductoBo.actualizarProducto(ltsDetVenta.get(i).getCantidad(), ltsDetVenta.get(i).getIdProd().getId());
                                DetVentaBo.eliminar(ltsDetVenta.get(i));
                            }
                            objProd = ProductoBo.buscarIdProducto(ltsDetVenta.get(i).getIdProd().getId());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        ltsDetVenta.remove(i);
                    }
                }
                for (int i = 0; i < ltsDetVenta.size(); i++) {
                    pago += ltsDetVenta.get(i).getSubTotal();
                }
                pago = pago - descuento;
                request.setAttribute("pago", pago);
                request.setAttribute("objVent", objVent);
                request.setAttribute("verModal", "VERMODAL");
                request.setAttribute("listaDetVenta", ltsDetVenta);
                request.setAttribute("ProductoV", objProd);
                url = "venta.jsp";
                break;
            case "elimCarrito":
                pago = 0.0;
                for (int i = 0; i < ltsDetVenta.size(); i++) {
                    if (ltsDetVenta.get(i).getId() == Integer.parseInt(request.getParameter("idProdE"))) {
                        if (idVentEdit != 0) {
                            try {
                                ProductoBo.actualizarProducto(ltsDetVenta.get(i).getCantidad(), ltsDetVenta.get(i).getIdProd().getId());
                                DetVentaBo.eliminar(ltsDetVenta.get(i));
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        ltsDetVenta.remove(i);
                    }
                }
                for (int i = 0; i < ltsDetVenta.size(); i++) {
                    pago += ltsDetVenta.get(i).getSubTotal();
                }
                pago = pago - descuento;
                request.setAttribute("pago", pago);
                request.setAttribute("objVent", objVent);
                request.setAttribute("verModal", "VERMODAL");
                request.setAttribute("listaDetVenta", ltsDetVenta);
                url = "venta.jsp";
                break;
            case "limpiar":
                this.limpiar();
                this.obtenerListaVenta();
                url = "venta.jsp";
                break;
            case "subEditar":
                pago = 0.0;
                this.cargarDatosVenta(request);
                for (int i = 0; i < ltsDetVenta.size(); i++) {
                    pago += ltsDetVenta.get(i).getSubTotal();
                }
                pago = pago - descuento;
                request.setAttribute("pago", pago);
                request.setAttribute("objVent", objVent);
                request.setAttribute("verModal", "VERMODAL");
                request.setAttribute("listaDetVenta", ltsDetVenta);
                url = "venta.jsp";
                break;
            case "guardar":
                this.obtenerValores(request);
                if (idVentEdit == 0) {
                    this.guardarVenta(request, response);
                } else {
                    this.editarVenta(request);
                }
                this.limpiar();
                this.obtenerListaVenta();
                this.obtenerListaProd();
                url = "venta.jsp";
                break;
            case "eliminar":
                this.elminarVenta(request);
                this.obtenerListaVenta();
                this.obtenerListaProd();
                this.limpiar();
                url = "venta.jsp";
                break;
            default:
                url = "venta.jsp";
        }

        request.setAttribute("listaVenta", ltsVenta);
        request.setAttribute("ticketNro", nroTicket);
        request.setAttribute("listaProducto", ltsProducto);
        request.setAttribute("cargo", cargo);

        request.getRequestDispatcher(url).forward(request, response);
    }

    private void obtenerListaProd() {
        ltsProducto = new ArrayList();
        try {
            ltsProducto = ProductoBo.obtenerListaProductoCombo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void obtenerListaVenta() {
        ltsProducto = new ArrayList();
        try {
            ltsVenta = VentaBo.obtenerListaVenta();
            nroTicket = VentaBo.buscarNroTicket();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void obtenerValores(HttpServletRequest request) {
        if (request.getParameter("descuento").length() > 0) {
            descuento = Double.parseDouble(request.getParameter("descuento"));
        }
        fecha = request.getParameter("fecha");
        Empleado objE = new Empleado();
        Cliente objC = new Cliente();
        objE.setId(idEmpleado);
        objC.setId(1);
        objVent = new Venta(0, fecha, 0.0, descuento, objE, objC, "Si", nroTicket);
    }

    private void buscarProducto(HttpServletRequest request) {
        int idProd = Integer.parseInt(request.getParameter("idProducto"));
        try {
            objProd = ProductoBo.buscarIdProducto(idProd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int buscar() {
        int encontro = 0;
        for (int i = 0; i < ltsDetVenta.size(); i++) {
            if (objProd.getId() == ltsDetVenta.get(i).getIdProd().getId()) {
                encontro = 1;
            }
        }
        return encontro;
    }

    private void limpiar() {
        descuento = 0;
        fecha = "";
        pago = 0.0;
        cantidad = 0;
        ltsDetVenta = new ArrayList();
        objVent = null;
        objProd = null;
        objDetV = null;
        idVentEdit = 0;
    }

    private void guardarVenta(HttpServletRequest request, HttpServletResponse response) {
        pago = 0.0;
        for (int i = 0; i < ltsDetVenta.size(); i++) {
            pago += ltsDetVenta.get(i).getSubTotal();
        }
        pago = pago - descuento;
        objVent.setPagoTotal(pago);
        try {
            if (VentaBo.grabar(objVent)) {
                //Guardamos detalle Venta
                for (int i = 0; i < ltsDetVenta.size(); i++) {
                    objDetV = new DetVenta();
                    objDetV.setCantidad(ltsDetVenta.get(i).getCantidad());
                    objDetV.setSubTotal(ltsDetVenta.get(i).getSubTotal());
                    objDetV.setIdVenta(VentaBo.obtenerIdVentaTicket(objVent.getNroTicket()));
                    objDetV.setIdProd(ltsDetVenta.get(i).getIdProd());
                    DetVentaBo.grabar(objDetV);
                    ProductoBo.actualizarProducto(-objDetV.getCantidad(), objDetV.getIdProd().getId());
                }
                request.setAttribute("success", "Venta Guardada Correctamente.");
                this.imprimirTicket(nroTicket, response);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            request.setAttribute("error", "No se pudo Guardar la Venta correctamente. Intentelo más tarde!!");
        }
    }

    private void cargarDatosVenta(HttpServletRequest request) {
        try {
            idVentEdit = Integer.parseInt(request.getParameter("idV"));
            objVent = VentaBo.buscarIdVenta(idVentEdit);
            descuento = objVent.getDescuento();
            nroTicket = objVent.getNroTicket();
            ltsDetVenta = DetVentaBo.obtenerListaDetVenta(objVent.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void editarVenta(HttpServletRequest request) {
        pago = 0.0;
        for (int i = 0; i < ltsDetVenta.size(); i++) {
            pago += ltsDetVenta.get(i).getSubTotal();
        }
        pago = pago - descuento;
        objVent.setPagoTotal(pago);
        objVent.setId(idVentEdit);
        try {
            if (VentaBo.modificar(objVent)) {
                //Guardamos Editar detalle Venta
                for (int i = 0; i < ltsDetVenta.size(); i++) {
                    if (ltsDetVenta.get(i).getIdProd().getModificadoProd()) {
                        objDetV = new DetVenta();
                        objDetV.setCantidad(ltsDetVenta.get(i).getCantidad());
                        objDetV.setSubTotal(ltsDetVenta.get(i).getSubTotal());
                        objDetV.setIdVenta(VentaBo.obtenerIdVentaTicket(objVent.getNroTicket()));
                        objDetV.setIdProd(ltsDetVenta.get(i).getIdProd());
                        DetVentaBo.grabar(objDetV);
                        ProductoBo.actualizarProducto(-objDetV.getCantidad(), objDetV.getIdProd().getId());
                    }
                }
                request.setAttribute("success", "Venta Modificada Correctamente.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            request.setAttribute("error", "No se pudo Modificar la Venta correctamente. Intentelo más tarde!!");
        }
    }

    private void elminarVenta(HttpServletRequest request) {
        idVentEdit = Integer.parseInt(request.getParameter("idV"));
        try {
            objVent = VentaBo.buscarIdVenta(idVentEdit);
            ltsDetVenta = DetVentaBo.obtenerListaDetVenta(objVent.getId());
            for (int i = 0; i < ltsDetVenta.size(); i++) {
                ProductoBo.actualizarProducto(ltsDetVenta.get(i).getCantidad(), ltsDetVenta.get(i).getIdProd().getId());
                DetVentaBo.eliminar(ltsDetVenta.get(i));
            }
            if (VentaBo.eliminar(objVent)) {
                request.setAttribute("success", "Venta Eliminada Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Eliminar la Venta correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void imprimirTicket(String nroTicket, HttpServletResponse response) {
        ArrayList<TicketImprimir> ltsImprimir = new ArrayList();
        try {
            ltsImprimir = VentaBo.generarTicketImprimir(nroTicket);

            //Estructura para archivo PDF
            String encabezado = "Ticket N° " + ltsImprimir.get(0).getNroTicket() + "\n";

            //Tipo de letra para el reporte
            Font fuente = new Font(Font.getFamily("ARIAL"), 18, Font.BOLD);

            //Creamos un parrafo
            Paragraph linea = new Paragraph(encabezado, fuente);
            linea.setAlignment(1);
            Paragraph fechaTicket = new Paragraph(ltsImprimir.get(0).getFecha() + "\n" + "\n");
            fechaTicket.setAlignment(2);

            //Creamos una tabla
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100);

            //Creamos el documento
            Document pdf = new Document(PageSize.A4);

            //Definimos destino de descarga
//            String file = "C:\\Users\\Millier\\OneDrive\\Documents\\TicketImprimir.pdf";
            String file = "C:\\Users\\Public\\Documents\\TicketImprimir.pdf";

            //Generamos el archivo PDF
            PdfWriter.getInstance(pdf, new FileOutputStream(file));

            //Creamos ñas ceñdas de cabecera
            PdfPCell celda1 = new PdfPCell(new Paragraph("Descripción", FontFactory.getFont("ARIAL", 12, Font.BOLD, BaseColor.RED)));
            celda1.setHorizontalAlignment(1);
            PdfPCell celda2 = new PdfPCell(new Paragraph("Marca", FontFactory.getFont("ARIAL", 12, Font.BOLD, BaseColor.RED)));
            celda2.setHorizontalAlignment(1);
            PdfPCell celda3 = new PdfPCell(new Paragraph("C/U", FontFactory.getFont("ARIAL", 12, Font.BOLD, BaseColor.RED)));
            celda3.setHorizontalAlignment(1);
            PdfPCell celda4 = new PdfPCell(new Paragraph("Cant.", FontFactory.getFont("ARIAL", 12, Font.BOLD, BaseColor.RED)));
            celda4.setHorizontalAlignment(1);
            PdfPCell celda5 = new PdfPCell(new Paragraph("Importe", FontFactory.getFont("ARIAL", 12, Font.BOLD, BaseColor.RED)));
            celda5.setHorizontalAlignment(1);

            //Unir Documentación
            pdf.open();
            pdf.add(linea);
            pdf.add(fechaTicket);
            tabla.addCell(celda1);
            tabla.addCell(celda2);
            tabla.addCell(celda3);
            tabla.addCell(celda4);
            tabla.addCell(celda5);
            for (int i = 0; i < ltsImprimir.size(); i++) {
                tabla.addCell(ltsImprimir.get(i).getDescripcionProd());
                tabla.addCell(ltsImprimir.get(i).getNombreMarca());
                tabla.addCell(String.valueOf(ltsImprimir.get(i).getPrecioVent()));
                tabla.addCell(String.valueOf(ltsImprimir.get(i).getCantidad()));
                tabla.addCell(String.valueOf(ltsImprimir.get(i).getSubtotal()));
            }
            float[] columnWidths = {3f, 2f, 1f, 1f, 2f};
            tabla.setWidths(columnWidths);

            //Creamos tabla para subTotal
            PdfPTable tabla2 = new PdfPTable(2);
            tabla2.setWidthPercentage(40);
            //Agregamos Sub Total a Pagar
            PdfPCell celda6 = new PdfPCell(new Paragraph("Sub. Total S/.", FontFactory.getFont("ARIAL", 12, Font.BOLD, BaseColor.BLACK)));
            celda6.setHorizontalAlignment(2);
            tabla2.addCell(celda6);
            PdfPCell celda9 = new PdfPCell(new Paragraph(String.valueOf(ltsImprimir.get(0).sinDescuento()), FontFactory.getFont("ARIAL", 12, Font.BOLD, BaseColor.BLACK)));
            celda9.setHorizontalAlignment(1);
            tabla2.addCell(celda9);
            //Agregamos descuento
            PdfPCell celda7 = new PdfPCell(new Paragraph("Descuento S/.", FontFactory.getFont("ARIAL", 12, Font.BOLD, BaseColor.BLACK)));
            celda7.setHorizontalAlignment(2);
            tabla2.addCell(celda7);
            PdfPCell celda10 = new PdfPCell(new Paragraph(String.valueOf(ltsImprimir.get(0).getDescuentoVenta()), FontFactory.getFont("ARIAL", 12, Font.BOLD, BaseColor.BLACK)));
            celda10.setHorizontalAlignment(1);
            tabla2.addCell(celda10);
            //Agregamos total pago
            PdfPCell celda8 = new PdfPCell(new Paragraph("Total Pagar S/.", FontFactory.getFont("ARIAL", 12, Font.BOLD, BaseColor.BLACK)));
            celda8.setHorizontalAlignment(2);
            tabla2.addCell(celda8);
            PdfPCell celda11 = new PdfPCell(new Paragraph(String.valueOf(ltsImprimir.get(0).getTotalPagoVenta()), FontFactory.getFont("ARIAL", 12, Font.BOLD, BaseColor.BLACK)));
            celda11.setHorizontalAlignment(1);
            tabla2.addCell(celda11);
            //Agregamos a la derecha la tabla
            tabla2.setHorizontalAlignment(2);

            //Agregamos la tabla hacia el documento
            pdf.add(tabla);
            pdf.add(tabla2);
            pdf.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
