package main;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    static private java.sql.Connection conexion;
    static private Statement sentencia;
    static String host = "localhost";
    static String bd = "mydb";
    static String user = "root";
    static String password = "";

    private static boolean registrarDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver no encontrado");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean conectarBD() {
        if (registrarDriver()) {
            try {
                final String urlBD = "jdbc:mysql://" + host + "/" + bd
                        + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                conexion = DriverManager.getConnection(urlBD, user, password);
                sentencia = conexion.createStatement(1005, 1008);
                return true;
            } catch (SQLException e) {
                System.out.println("Error SQL");
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
