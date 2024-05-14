package Modelo.Negocio;

import Modelo.BBDD.ConexionImp;
import Modelo.BBDD.VentaDao;
import Modelo.BBDD.VentaDaoImp;
import Modelo.TicketImprimir;
import Modelo.Venta;
import java.sql.Connection;
import java.util.ArrayList;

public class VentaBo {

    public static boolean grabar(Object object) throws Exception {
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            VentaDao obj = new VentaDaoImp(con);
            obj.grabar(object);
            con.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public static boolean eliminar(Object object) throws Exception {
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            VentaDao obj = new VentaDaoImp(con);
            obj.eliminar(object);
            con.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public static boolean modificar(Object object) throws Exception {
        Connection con = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            VentaDao obj = new VentaDaoImp(con);
            obj.modificar(object);
            con.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public static ArrayList obtenerListaVenta() throws Exception {
        Connection con = null;
        ArrayList<Venta> ltsVenta = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            VentaDao obj = new VentaDaoImp(con);
            ltsVenta = obj.obtenerListaVenta();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return ltsVenta;
    }

    public static Venta buscarIdVenta(Object id) throws Exception {
        Connection con = null;
        Venta objV = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            VentaDao obj = new VentaDaoImp(con);
            objV = obj.buscarIdVenta(id);
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return objV;
    }

    public static String buscarNroTicket() throws Exception {
        Connection con = null;
        String nroTicket = "000001";
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            VentaDao obj = new VentaDaoImp(con);
            String nuevo = String.valueOf(obj.obtenerTicket() + 1);

            int recorrido = 6 - nuevo.length();
            nroTicket = "";
            for (int i = 0; i < recorrido; i++) {
                nroTicket += "0";
            }
            nroTicket += nuevo;
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return nroTicket;
    }

    public static int obtenerIdVentaTicket(Object ticket) throws Exception {
        Connection con = null;
        int id = 0;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            VentaDao obj = new VentaDaoImp(con);
            id = obj.obteneridVentaTicket(ticket);
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return id;
    }

    public static int cantVenta() throws Exception {
        Connection con = null;
        int cant = 0;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            VentaDao obj = new VentaDaoImp(con);
            cant = obj.cantVenta();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return cant;
    }

    public static ArrayList obtenerDashboar() throws Exception {
        Connection con = null;
        ArrayList<Venta> ltsVenta = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            VentaDao obj = new VentaDaoImp(con);
            ltsVenta = obj.obtenerDashboar();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return ltsVenta;
    }

    public static ArrayList obtenerDashboarBusca(String fechaInicio, String finFecha, int idEmpleado) throws Exception {
        Connection con = null;
        ArrayList<Venta> ltsVenta = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            VentaDao obj = new VentaDaoImp(con);
            ltsVenta = obj.obtenerDashboarBusca(fechaInicio, finFecha, idEmpleado);
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return ltsVenta;
    }

    public static ArrayList reporteDia(Object Fecha) throws Exception {
        Connection con = null;
        ArrayList<Venta> ltsVenta = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            VentaDao obj = new VentaDaoImp(con);
            ltsVenta = obj.reportedia(Fecha);
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return ltsVenta;
    }

    public static ArrayList reporteRango(Object FechaIni, Object FechaFin) throws Exception {
        Connection con = null;
        ArrayList<Venta> ltsVenta = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            VentaDao obj = new VentaDaoImp(con);
            ltsVenta = obj.reporteRango(FechaIni, FechaFin);
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return ltsVenta;
    }

    public static ArrayList reporteMensual(Object mes, Object anio) throws Exception {
        Connection con = null;
        ArrayList<Venta> ltsVenta = new ArrayList();
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            VentaDao obj = new VentaDaoImp(con);
            ltsVenta = obj.reporteMensual(mes, anio);
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return ltsVenta;
    }
    
    public static ArrayList generarTicketImprimir(Object nroTicket) throws Exception {
        Connection con = null;
        ArrayList<TicketImprimir> ltsImprimir = new ArrayList();
        TicketImprimir objVI = null;
        try {
            con = ConexionImp.getConexion();
            con.setAutoCommit(false);
            VentaDao obj = new VentaDaoImp(con);
            ltsImprimir = obj.generarTicketImprimir(nroTicket);
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
            throw e;
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return ltsImprimir;
    }
}
