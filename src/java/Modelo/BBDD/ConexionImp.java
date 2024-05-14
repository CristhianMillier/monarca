package Modelo.BBDD;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionImp {

    public static Connection getConexion() throws Exception {
        Connection Con = null;
        String url = "jdbc:mysql://localhost:3306/licoreriamonarca?useSSL=false";
        String user = "root";
        String pwd = "1234";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Con = DriverManager.getConnection(url, user, pwd);
        return Con;
    }
}
