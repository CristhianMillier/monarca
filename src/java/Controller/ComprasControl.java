package Controller;

import Modelo.Compra;
import Modelo.DetCompra;
import Modelo.Empleado;
import Modelo.Negocio.CompraBo;
import Modelo.Negocio.DetCompraBo;
import Modelo.Negocio.ProductoBo;
import Modelo.Negocio.ProveedorBo;
import Modelo.Producto;
import Modelo.Proveedor;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Compra")
public class ComprasControl extends HttpServlet {

    private String fecha = "";
    private double pago = 0.0;
    private int idCompEdit = 0;
    private Producto objProd = null;
    private Compra objComp = null;
    private DetCompra objDetC = null;
    private ArrayList<DetCompra> ltsDetCompra = new ArrayList();

    private int idEmpleado = 0;
    private ArrayList<Compra> ltsCompra = new ArrayList();
    private ArrayList<Producto> ltsProducto = new ArrayList();
    private ArrayList<Proveedor> ltsProveedor = new ArrayList();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "compra.jsp";
        String accion = request.getParameter("accion");

        switch (accion) {
            case "listar":
                url = "compra.jsp";
                this.obtenerListaCompra();
                this.obtenerListaProd();
                this.obtenerListaProv();
                this.limpiar();
                this.idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
                break;
            case "agregar":
                pago = 0.0;
                if (validarLlneado(request)) {
                    this.obtenerValores(request);
                    if (this.buscar() != 1) {
                        objDetC = new DetCompra();
                        objDetC.setPrecioCompra(Double.parseDouble(request.getParameter("precioCompra")));
                        objDetC.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
                        objDetC.setPrecioVenta(Double.parseDouble(request.getParameter("precioVenta")));
                        objProd.setModificadoProd(true);
                        objDetC.setIdProd(objProd);
                        ltsDetCompra.add(objDetC);
                    } else {
                        request.setAttribute("errorMsj", "El producto ya se encuentra en la lista.");
                    }
                } else {
                    request.setAttribute("errorMsj", "Ingrese correctamente los campos obligatorios.");
                }
                for (int i = 0; i < ltsDetCompra.size(); i++) {
                    pago += ltsDetCompra.get(i).calcularSubTotal();
                }
                request.setAttribute("pago", pago);
                request.setAttribute("objComp", objComp);
                request.setAttribute("verModal", "VERMODAL");
                request.setAttribute("listaDetCompra", ltsDetCompra);
                url = "compra.jsp";
                objProd = null;
                break;
            case "editarCarrito":
                pago = 0.0;
                this.obtenerListaProd();
                for (int i = 0; i < ltsDetCompra.size(); i++) {
                    if (ltsDetCompra.get(i).getId() == Integer.parseInt(request.getParameter("idProdE"))) {
                        try {
                            if (idCompEdit != 0) {
                                ProductoBo.actualizarProducto(-ltsDetCompra.get(i).getCantidad(), ltsDetCompra.get(i).getIdProd().getId());
                                DetCompraBo.eliminar(ltsDetCompra.get(i));
                            }
                            objProd = ProductoBo.buscarIdProducto(ltsDetCompra.get(i).getIdProd().getId());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        ltsDetCompra.remove(i);
                    }
                }
                for (int i = 0; i < ltsDetCompra.size(); i++) {
                    pago += ltsDetCompra.get(i).calcularSubTotal();
                }
                request.setAttribute("pago", pago);
                request.setAttribute("objComp", objComp);
                request.setAttribute("verModal", "VERMODAL");
                request.setAttribute("listaDetCompra", ltsDetCompra);
                request.setAttribute("ProductoV", objProd);
                url = "compra.jsp";
                break;
            case "elimCarrito":
                pago = 0.0;
                for (int i = 0; i < ltsDetCompra.size(); i++) {
                    if (ltsDetCompra.get(i).getId() == Integer.parseInt(request.getParameter("idProdE"))) {
                        if (idCompEdit != 0) {
                            try {
                                ProductoBo.actualizarProducto(ltsDetCompra.get(i).getCantidad(), ltsDetCompra.get(i).getIdProd().getId());
                                DetCompraBo.eliminar(ltsDetCompra.get(i));
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        ltsDetCompra.remove(i);
                    }
                }
                for (int i = 0; i < ltsDetCompra.size(); i++) {
                    pago += ltsDetCompra.get(i).calcularSubTotal();
                }
                request.setAttribute("pago", pago);
                request.setAttribute("objComp", objComp);
                request.setAttribute("verModal", "VERMODAL");
                request.setAttribute("listaDetCompra", ltsDetCompra);
                url = "compra.jsp";
                break;
            case "limpiar":
                this.limpiar();
                this.obtenerListaCompra();
                url = "compra.jsp";
                break;
            case "subEditar":
                pago = 0.0;
                this.cargarDatosCompra(request);
                for (int i = 0; i < ltsDetCompra.size(); i++) {
                    pago += ltsDetCompra.get(i).calcularSubTotal();
                }
                request.setAttribute("pago", pago);
                request.setAttribute("objComp", objComp);
                request.setAttribute("verModal", "VERMODAL");
                request.setAttribute("listaDetCompra", ltsDetCompra);
                url = "compra.jsp";
                break;
            case "eliminar":
                this.elminarCompra(request);
                this.obtenerListaCompra();
                this.obtenerListaProd();
                this.limpiar();
                url = "compra.jsp";
                break;
            case "guardar":
                this.obtenerValores(request);
                if (idCompEdit == 0) {
                    this.guardarCompra(request);
                } else {
                    this.editarCompra(request);
                }
                this.limpiar();
                this.obtenerListaCompra();
                this.obtenerListaProd();
                url = "compra.jsp";
                break;
            default:
                url = "compra.jsp";
        }

        request.setAttribute("listaCompra", ltsCompra);
        request.setAttribute("listaProveedor", ltsProveedor);
        request.setAttribute("listaProducto", ltsProducto);

        request.getRequestDispatcher(url).forward(request, response);
    }

    private void obtenerListaCompra() {
        ltsCompra = new ArrayList();
        try {
            ltsCompra = CompraBo.obtenerListaCompra();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void obtenerListaProd() {
        ltsProducto = new ArrayList();
        try {
            ltsProducto = ProductoBo.obtenerListaProductoCombo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void obtenerListaProv() {
        ltsProveedor = new ArrayList();
        try {
            ltsProveedor = ProveedorBo.obtenerListaProveedorCombo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void limpiar() {
        fecha = "";
        pago = 0.0;
        idCompEdit = 0;
        objProd = null;
        objComp = null;
        objDetC = null;
        ltsDetCompra = new ArrayList<>();
    }

    private void obtenerValores(HttpServletRequest request) {
        this.fecha = request.getParameter("fecha");

        Empleado objE = new Empleado();
        Proveedor objPr = new Proveedor();
        objE.setId(idEmpleado);
        try {
            objPr = ProveedorBo.buscarIdProveedor(Integer.parseInt(request.getParameter("proveedor")));
        } catch (Exception e) {
        }
        objComp = new Compra(0, 0.0, fecha, objPr, objE, "Si");
    }

    private int buscar() {
        int encontro = 0;
        for (int i = 0; i < ltsDetCompra.size(); i++) {
            if (objProd.getId() == ltsDetCompra.get(i).getIdProd().getId()) {
                encontro = 1;
            }
        }
        return encontro;
    }

    private boolean validarLlneado(HttpServletRequest request) {
        if (request.getParameter("precioCompra").trim().equals("") || request.getParameter("cantidad").trim().equals("")
                || Integer.parseInt(request.getParameter("producto")) == 0 || Integer.parseInt(request.getParameter("proveedor")) == 0
                || request.getParameter("precioVenta").trim().equals("")) {
            return false;
        } else {
            try {
                objProd = ProductoBo.buscarIdProducto(Integer.parseInt(request.getParameter("producto")));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return true;
        }
    }

    private void cargarDatosCompra(HttpServletRequest request) {
        try {
            idCompEdit = Integer.parseInt(request.getParameter("idC"));
            objComp = CompraBo.buscarIdCompra(idCompEdit);
            ltsDetCompra = DetCompraBo.obtenerListaDetCompra(objComp.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void elminarCompra(HttpServletRequest request) {
        idCompEdit = Integer.parseInt(request.getParameter("idC"));
        try {
            objComp = CompraBo.buscarIdCompra(idCompEdit);
            ltsDetCompra = DetCompraBo.obtenerListaDetCompra(objComp.getId());
            for (int i = 0; i < ltsDetCompra.size(); i++) {
                ProductoBo.actualizarProducto(-ltsDetCompra.get(i).getCantidad(), ltsDetCompra.get(i).getIdProd().getId());
                DetCompraBo.eliminar(ltsDetCompra.get(i));
            }
            if (CompraBo.eliminar(objComp)) {
                request.setAttribute("success", "Compra Eliminada Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Eliminar la Compra correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void editarCompra(HttpServletRequest request) {
        pago = 0.0;
        for (int i = 0; i < ltsDetCompra.size(); i++) {
            pago += ltsDetCompra.get(i).calcularSubTotal();
        }
        objComp.setPago(pago);
        objComp.setId(idCompEdit);
        try {
            if (CompraBo.modificar(objComp)) {
                //Guardamos Editar detalle Venta
                for (int i = 0; i < ltsDetCompra.size(); i++) {
                    if (ltsDetCompra.get(i).getIdProd().getModificadoProd()) {
                        objDetC = new DetCompra();
                        objDetC.setPrecioCompra(ltsDetCompra.get(i).getPrecioCompra());
                        objDetC.setCantidad(ltsDetCompra.get(i).getCantidad());
                        objDetC.setIdCompra(CompraBo.obtenerUltimoID());
                        objDetC.setIdProd(ltsDetCompra.get(i).getIdProd());
                        objDetC.setPrecioVenta(ltsDetCompra.get(i).getPrecioVenta());
                        DetCompraBo.grabar(objDetC);
                        ProductoBo.actualizarProducto(objDetC.getCantidad(), objDetC.getIdProd().getId());
                        ProductoBo.actualizarProductoPrecio(objDetC.getPrecioVenta(), objDetC.getIdProd().getId());
                    }
                }
                request.setAttribute("success", "Compra Modificada Correctamente.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            request.setAttribute("error", "No se pudo Modificar la Compra correctamente. Intentelo más tarde!!");
        }
    }

    private void guardarCompra(HttpServletRequest request) {
        pago = 0.0;
        for (int i = 0; i < ltsDetCompra.size(); i++) {
            pago += ltsDetCompra.get(i).calcularSubTotal();
        }
        objComp.setPago(pago);
        try {
            if (CompraBo.grabar(objComp)) {
                //Guardamos detalle Venta
                for (int i = 0; i < ltsDetCompra.size(); i++) {
                    objDetC = new DetCompra();
                    objDetC.setPrecioCompra(ltsDetCompra.get(i).getPrecioCompra());
                    objDetC.setCantidad(ltsDetCompra.get(i).getCantidad());
                    objDetC.setIdCompra(CompraBo.obtenerUltimoID());
                    objDetC.setIdProd(ltsDetCompra.get(i).getIdProd());
                    objDetC.setPrecioVenta(ltsDetCompra.get(i).getPrecioVenta());
                    DetCompraBo.grabar(objDetC);
                    ProductoBo.actualizarProducto(objDetC.getCantidad(), objDetC.getIdProd().getId());
                    ProductoBo.actualizarProductoPrecio(objDetC.getPrecioVenta(), objDetC.getIdProd().getId());
                }
                request.setAttribute("success", "Compra Guardada Correctamente.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            request.setAttribute("error", "No se pudo Guardar la Compra correctamente. Intentelo más tarde!!");
        }
    }
}
