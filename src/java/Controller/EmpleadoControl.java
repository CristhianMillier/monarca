package Controller;

import Modelo.Cargo;
import Modelo.CorreoAndEncriptar;
import Modelo.Empleado;
import Modelo.Login;
import Modelo.Negocio.CargoBo;
import Modelo.Negocio.EmpleadoBo;
import Modelo.Negocio.LoginBo;
import Modelo.Negocio.ProductoBo;
import Modelo.Producto;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Empleado")
public class EmpleadoControl extends HttpServlet {

    private Empleado objELogg = new Empleado();
    private Empleado objEmp = new Empleado();
    private CorreoAndEncriptar cae = new CorreoAndEncriptar();
    private String clave = "";

    private int idEmp = 0;
    private String nombre = "";
    private String apellido = "";
    private String telefono = "";
    private String direccion = "";
    private String dni = "";
    private int idCargo = 0;
    private String estado = "";
    private String correo = "";

    private ArrayList<Empleado> ltsEmpleado = new ArrayList();
    private ArrayList<Cargo> ltsCargo = new ArrayList();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "empleado.jsp";
        String accion = request.getParameter("accion");

        switch (accion) {
            case "ingresar":
                String user = caracterEspecial(request.getParameter("txtuser"));
                String pass = caracterEspecial(request.getParameter("txtpass"));
                System.out.println(cae.encriptar(pass));
                if (!(user.trim().equals("") && pass.trim().equals(""))) {
                    if (pass.length() >= 8) {
                        try {
                            System.out.println(cae.encriptar(pass));
                            int idEmp = LoginBo.Logeo(user, cae.encriptar(pass));
                            if (idEmp > 0) {
                                objELogg = EmpleadoBo.buscarIdEmpleado(idEmp);
                                request.setAttribute("objEmp", objELogg);
                                this.alerta(request);
                                url = "principal.jsp";
                            } else {
                                request.setAttribute("mensaje", "Usuario o contraseña incorrecto.");
                                url = "index.jsp";
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            request.setAttribute("mensaje", "No se pudo conectar con el Servidor.");
                            url = "index.jsp";
                        }
                    } else {
                        request.setAttribute("mensaje", "La contraseña es muy corta.");
                        url = "index.jsp";
                    }
                } else {
                    request.setAttribute("mensaje", "Usted no a ingresado las credenciales correctamente.");
                    url = "index.jsp";
                }
                break;
            case "listar":
                url = "empleado.jsp";
                this.obtenerListaCargo();
                this.obtenerListaEmpleado();
                break;
            case "subEditar":
                idEmp = Integer.parseInt(request.getParameter("idE"));
                for (Empleado obj : ltsEmpleado) {
                    if (obj.getId() == idEmp) {
                        this.subEditar(request, obj);
                    }
                }
                url = "empleado.jsp";
                break;
            case "eliminar":
                this.eliminar(Integer.parseInt(request.getParameter("idEmpleado")), request);
                this.obtenerListaEmpleado();
                url = "empleado.jsp";
                break;
            case "guardar":
                boolean check = this.validarLlenado(request);
                objEmp = new Empleado(0, this.nombre, this.apellido, this.telefono, this.direccion, this.dni,
                        new Cargo(this.idCargo, "", ""), this.estado, this.correo);
                if (check) {
                    if (this.idEmp == 0) {
                        if (this.validarDNI()) {
                            if (this.validarCorreo()) {
                                this.guardar(request, objEmp);
                            } else {
                                request.setAttribute("errorMsj", "El Correo ya esta siendo usado.");
                                request.setAttribute("objProd", objEmp);
                            }
                        } else {
                            request.setAttribute("errorMsj", "El DNI ya esta siendo usado.");
                            request.setAttribute("objProd", objEmp);
                        }
                    } else {
                        this.editar(request, objEmp);
                    }
                } else {
                    request.setAttribute("errorMsj", "Complete correctamente los campos.");
                    request.setAttribute("objProd", objEmp);
                }
                this.obtenerListaEmpleado();
                this.limpiar();
                url = "empleado.jsp";
                break;
            case "user":
                this.obtenerListaCargo();
                request.setAttribute("objEmp", objELogg);
                url = "usuario.jsp";
                break;
            case "cancelar":
                request.setAttribute("objEmp", objELogg);
                url = "principal.jsp";
                break;
            case "guardarUser":
                boolean estado = this.validarLlenadoUser(request);
                objEmp = new Empleado(objELogg.getId(), this.nombre, this.apellido, this.telefono, this.direccion, "",
                        new Cargo(0, "", ""), "", "");
                if (estado) {
                    this.guardarUser(request, objEmp);
                } else {
                    request.setAttribute("errorMsj", "Complete correctamente los campos.");
                    request.setAttribute("objProd", objEmp);
                }
                request.setAttribute("objEmp", objELogg);
                this.limpiar();
                url = "usuario.jsp";
                break;
            case "editarPassword":
                url = "password.jsp";
                break;
            case "claveSeguridad":
                String passwordUser = caracterEspecial(request.getParameter("passwordEmp"));
                if (!(passwordUser.trim().equals(""))) {
                    this.enviarCorreo();
                } else{
                    request.setAttribute("errorMsjUser", "Usted no ha ingresado la contraseña nueva!!");
                }
                String claveUser = "";
                if (request.getParameter("claveSer") != null) {
                    claveUser = caracterEspecial(request.getParameter("claveSer"));
                }

                request.setAttribute("passwordUser", passwordUser);
                request.setAttribute("claveUser", claveUser);
                url = "password.jsp";
                break;
            case "guardarPasswordUser":
                String passwordU = caracterEspecial(request.getParameter("passwordEmp"));
                String claveU = caracterEspecial(request.getParameter("claveSer"));
                if (passwordU.length() >= 8) {
                    if (claveU.trim().equals(clave)) {
                        try {
                            passwordU = cae.encriptar(passwordU);
                            LoginBo.modificarLoginPassword(passwordU, objELogg.getId());
                            clave = "";
                            url = "index.jsp";
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        request.setAttribute("passwordUser", passwordU);
                        request.setAttribute("claveUser", claveU);
                        request.setAttribute("errorMsjUser", "La clave de seguridad no coincide. Verifique su Gmail!!!");
                        url = "password.jsp";
                    }
                } else {
                    request.setAttribute("passwordUser", passwordU);
                    request.setAttribute("claveUser", claveU);
                    request.setAttribute("errorMsjUser", "La contraseña no contiene un mínimo de 8 caracteres.");
                    url = "password.jsp";
                }
                break;
            case "atrasUser":
                request.setAttribute("objEmp", objELogg);
                clave = "";
                url = "usuario.jsp";
                break;
            case "descargar":
                this.alerta(request);
                url = "descargarAlerta.jsp";
                break;
            default:
                url = "empleado.jsp";
        }

        request.setAttribute("empSeccion", objELogg.getId());
        request.setAttribute("listaCargo", ltsCargo);
        request.setAttribute("listaEmpleado", ltsEmpleado);

        request.getRequestDispatcher(url).forward(request, response);
    }

    private void obtenerListaEmpleado() {
        ltsEmpleado = new ArrayList();
        try {
            ltsEmpleado = EmpleadoBo.obtenerListaEmpleado();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void obtenerListaCargo() {
        ltsCargo = new ArrayList();
        try {
            ltsCargo = CargoBo.obtenerListaCargoCombo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminar(int idEliminar, HttpServletRequest request) {
        try {
            Empleado obj = new Empleado();
            obj.setId(idEliminar);
            if (EmpleadoBo.eliminar(obj)) {
                LoginBo.eliminarLogin(idEliminar);
                request.setAttribute("success", "Empleado Eliminado Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Eliminar el Empleado correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void limpiar() {
        idEmp = 0;
        nombre = "";
        apellido = "";
        telefono = "";
        direccion = "";
        dni = "";
        idCargo = 0;
        estado = "";
        correo = "";
    }

    private boolean validarLlenado(HttpServletRequest request) {
        this.nombre = caracterEspecial(request.getParameter("nombreEmp"));
        this.apellido = caracterEspecial(request.getParameter("apellidoEmp"));
        this.telefono = caracterEspecial(request.getParameter("telefonoEmp"));
        this.direccion = caracterEspecial(request.getParameter("direccionEmp"));
        this.dni = caracterEspecial(request.getParameter("dniEmp"));
        this.correo = caracterEspecial(request.getParameter("correoEmp"));
        this.idCargo = Integer.parseInt(request.getParameter("cargo"));
        this.estado = caracterEspecial(request.getParameter("estado"));

        if (this.nombre.trim().equals("") || this.apellido.trim().equals("") || this.telefono.trim().equals("")
                || this.direccion.trim().equals("") || this.dni.trim().equals("") || this.correo.trim().equals("")
                || this.idCargo == 0 || this.estado.equals("0") || this.telefono.length() < 9 || this.dni.length() < 8) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validarLlenadoUser(HttpServletRequest request) {
        this.nombre = caracterEspecial(request.getParameter("nombreEmp"));
        this.apellido = caracterEspecial(request.getParameter("apellidoEmp"));
        this.telefono = caracterEspecial(request.getParameter("telefonoEmp"));
        this.direccion = caracterEspecial(request.getParameter("direccionEmp"));

        if (this.nombre.trim().equals("") || this.apellido.trim().equals("") || this.telefono.trim().equals("")
                || this.direccion.trim().equals("") || this.telefono.length() < 9) {
            return false;
        } else {
            return true;
        }
    }

    private void guardar(HttpServletRequest request, Empleado objEmp) {
        try {
            if (EmpleadoBo.grabar(objEmp)) {
                String password = cae.GenerarClave(10);
                String user = caracterEspecial(request.getParameter("userEmp"));

                cae.createEmail(objEmp.getCorreo(), "Contraseña para Licorería Monarca", "<h3>Su cuenta fue creada correctamente</h3></br><p>Su contraseña de acceso es: " + password + "</p>");
                cae.sendEmail();

                Login objLog = new Login(0, user, cae.encriptar(password), EmpleadoBo.obtenerUltimoId(objEmp.getDni()), objEmp.getEstado());
                LoginBo.grabarLogin(objLog);

                request.setAttribute("success", "Empleado Guardado Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Guardar el Empleado correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void guardarUser(HttpServletRequest request, Empleado objEmp) {
        try {
            if (EmpleadoBo.modificarUser(objEmp)) {
                String user = caracterEspecial(request.getParameter("userEmp"));

                Login objLog = new Login(0, user, "", objELogg.getId(), "Si");
                LoginBo.modificarLogin(objLog);
                objELogg = EmpleadoBo.buscarIdEmpleado(objELogg.getId());

                request.setAttribute("success", "Se modifico Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Modificar el Empleado correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void editar(HttpServletRequest request, Empleado objEmp) {
        objEmp.setId(this.idEmp);
        try {
            if (EmpleadoBo.modificar(objEmp)) {
                String user = caracterEspecial(request.getParameter("userEmp"));
                String cambiarPassword = request.getParameter("cambiarEmp");

                if (cambiarPassword != null) {
                    String password = cae.GenerarClave(10);
                    cae.createEmail(objEmp.getCorreo(), "Cambio de contraseña para Licorería Monarca", "<h3>Se cambio su contraseña</h3></br><p>Su contraseña nueva es: " + password + "</p>");
                    cae.sendEmail();
                    LoginBo.modificarLoginPassword(cae.encriptar(password), objEmp.getId());
                }

                Login objLog = new Login(0, user, "", objEmp.getId(), objEmp.getEstado());
                LoginBo.modificarLogin(objLog);

                request.setAttribute("success", "Empleado Modificado Correctamente.");
            } else {
                request.setAttribute("error", "No se pudo Modificar el Empleado correctamente. Intentelo más tarde!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void subEditar(HttpServletRequest request, Empleado obj) {
        try {
            obj.setUser(LoginBo.buscarEmpleado(obj.getId()));
            request.setAttribute("objEmp", obj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String caracterEspecial(String parameter) {
        byte[] bytes = parameter.getBytes(StandardCharsets.ISO_8859_1);
        parameter = new String(bytes, StandardCharsets.UTF_8);
        return parameter;
    }

    private boolean validarDNI() {
        boolean et = true;
        for (Empleado obj : ltsEmpleado) {
            if (obj.getDni().equals(this.dni)) {
                et = false;
            }
        }
        return et;
    }

    private boolean validarCorreo() {
        boolean et = true;
        for (Empleado obj : ltsEmpleado) {
            if (obj.getCorreo().equals(this.correo)) {
                et = false;
            }
        }
        return et;
    }

    private void alerta(HttpServletRequest request) {
        ArrayList<Producto> ltsProductoStock = new ArrayList();
        ArrayList<Producto> ltsProductoCaduca = new ArrayList();
        try {
            ltsProductoCaduca = ProductoBo.listaCaducidad();
            ltsProductoStock = ProductoBo.listaStockMinimo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (ltsProductoCaduca.size() > 0) {
            request.setAttribute("listaProdCaduc", ltsProductoCaduca);
            request.setAttribute("existeCaduc", "true");
        }
        if (ltsProductoStock.size() > 0) {
            request.setAttribute("listaProdAgotarse", ltsProductoStock);
            request.setAttribute("existeAgot", "true");
        }
    }

    private void enviarCorreo() {
        try {
            clave = cae.GenerarClave(5);
            cae.createEmail(objELogg.getCorreo(), "Clave de Seguridad - Licorería Monarca", "<h3>Clave de seguridad para el cambio de Contraseña</h3></br><p>Su clave de seguridad es: " + clave + "</p>");
            cae.sendEmail();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
