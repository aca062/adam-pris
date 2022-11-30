package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Conexion;
import model.Proyecto;
import model.Usuario;

public class ProyectoDAO {
    public static boolean insertar(Proyecto proyecto, int id) throws SQLException {

        String query = "INSERT INTO `proyecto` (`nombre`) VALUES (?)";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setString(1, proyecto.getNombre());
        boolean filaAnadida = sentencia.executeUpdate() > 0;
        sentencia.close();

        Conexion.desconectarBD();

        return filaAnadida;
    }

    public static boolean borrar(Usuario usuario) throws SQLException {
        String query = "DELETE FROM usuario WHERE `id` = ? AND `login` = ? AND `password` = ?";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        //sentencia.setInt(1, obtenerPorLogin(usuario.getLogin()).getId());
        sentencia.setString(2, usuario.getLogin());
        sentencia.setString(3, usuario.getPassword());
        boolean filaBorrada = sentencia.executeUpdate() > 0;
        sentencia.close();

        Conexion.desconectarBD();

        return filaBorrada;
    }

}
