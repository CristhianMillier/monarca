package Modelo.BBDD;

import Modelo.Cargo;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CargoDao extends General{
    public Cargo buscarCargo(Object id) throws SQLException;
    public ArrayList obtenerListaCargo() throws SQLException;
    public ArrayList obtenerListaCargoCombo() throws SQLException;
}
