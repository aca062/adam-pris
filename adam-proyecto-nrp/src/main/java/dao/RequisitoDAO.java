package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Conexion;
import model.Requisito;

public class RequisitoDAO {
    public static boolean insertar(Requisito requisito) throws SQLException {

        if (obtenerPorNombre(requisito.getNombre()) != null) {
            return false;
        }

        String query = "INSERT INTO `requisito` (`id`, `esfuerzo`, `nombre`, `usuario_id`) VALUES (?, ?, ?,?)";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setString(1, null);
        sentencia.setInt(2, requisito.getEsfuerzo());
        sentencia.setString(3, requisito.getNombre());
        if (requisito.getUsuario_id() == -1 ) {
            sentencia.setNull(4, Types.INTEGER);
        } else {
            sentencia.setInt(4, requisito.getUsuario_id());
        }
        boolean filaAnadida = sentencia.executeUpdate() > 0;
        sentencia.close();

        Conexion.desconectarBD();

        return filaAnadida;
    }

    public static boolean borrar(Requisito requisito) throws SQLException {
        String query = "DELETE FROM requisito WHERE `id` = ? AND `esfuerzo` = ? AND `nombre` = ?";

        ClienteHasRequisitoDAO.borrarRelacionesCliente(requisito.getId());
        RequisitoHasRequisitoDAO.borrarRelacionesRequisito(requisito.getId());

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setInt(1, obtenerPorNombre(requisito.getNombre()).getId());
        sentencia.setInt(2, requisito.getEsfuerzo());
        sentencia.setString(3, requisito.getNombre());
        boolean filaBorrada = sentencia.executeUpdate() > 0;
        sentencia.close();

        Conexion.desconectarBD();

        return filaBorrada;
    }

    public static boolean borrar(int id) throws SQLException {
        String query = "DELETE FROM requisito WHERE `requisito`.`id` = ?";

        ClienteHasRequisitoDAO.borrarRelacionesCliente(id);
        RequisitoHasRequisitoDAO.borrarRelacionesRequisito(id);

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setInt(1, id);
        boolean filaBorrada = sentencia.executeUpdate() > 0;
        sentencia.close();

        Conexion.desconectarBD();

        return filaBorrada;
    }

    public static boolean actualizar(Requisito requisito) throws SQLException {
        String query = "UPDATE `requisito` SET `esfuerzo` = ?, `nombre` = ? WHERE `requisito`.`id` = ?";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setInt(1, requisito.getEsfuerzo());
        sentencia.setString(2, requisito.getNombre());
        sentencia.setInt(3, requisito.getId());
        boolean filaActualizada = sentencia.executeUpdate() > 0;
        sentencia.close();

        Conexion.desconectarBD();

        return filaActualizada;
    }

    public static Requisito obtenerPorID(int id) throws SQLException {
        Requisito requisito = null;
        String query = "SELECT * FROM `requisito` WHERE `id` = ?";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setInt(1, id);

        ResultSet resultado = sentencia.executeQuery();

        if (resultado.next()) {
            requisito = new Requisito(resultado.getInt("id"), resultado.getInt("esfuerzo"), resultado.getString("nombre"), resultado.getInt("usuario_id"));
        } else {
            return null;
        }

        sentencia.close();

        Conexion.desconectarBD();

        return requisito;
    }

    public static Requisito obtenerPorNombre(String nombre) throws SQLException {
        Requisito requisito = null;
        String query = "SELECT * FROM `requisito` WHERE `nombre` = ?";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setString(1, nombre);

        ResultSet resultado = sentencia.executeQuery();

        if (resultado.next()) {
            requisito = new Requisito(resultado.getInt("id"), resultado.getInt("esfuerzo"), resultado.getString("nombre"), resultado.getInt("usuario_id"));
        } else {
            return null;
        }

        sentencia.close();

        Conexion.desconectarBD();

        return requisito;
    }

    public static List<Requisito> listar() throws SQLException {
        List<Requisito> listaRequisito = new ArrayList<Requisito>();
        String query = "SELECT * FROM `requisito`";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);

        ResultSet resultado = sentencia.executeQuery();

        while (resultado.next()) {
            int id = resultado.getInt("id");
            int esfuerzo = resultado.getInt("esfuerzo");
            String nombre = resultado.getString("nombre");
            int usuario_id = resultado.getInt("usuario_id");

            Requisito requisito = new Requisito(id, esfuerzo, nombre, usuario_id);
            listaRequisito.add(requisito);
        }

        sentencia.close();

        Conexion.desconectarBD();

        return listaRequisito;
    }
}
