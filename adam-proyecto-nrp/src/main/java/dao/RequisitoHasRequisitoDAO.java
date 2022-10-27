package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Conexion;
import model.RequisitoHasRequisito;
import model.RequisitoHasRequisito.tipo;

public class RequisitoHasRequisitoDAO {
    public static boolean insertar(RequisitoHasRequisito relacion) throws SQLException {
        String query = "INSERT INTO `requisito_has_requisito` (`tipo`, `requisito_id`, `requisito_id1`) VALUES (?, ?, ?)";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setString(1, relacion.getTipo().toString());
        sentencia.setInt(2, relacion.getRequisito_id());
        sentencia.setInt(3, relacion.getRequisito_id1());
        boolean filaAnadida = sentencia.executeUpdate() > 0;
        sentencia.close();

        Conexion.desconectarBD();

        return filaAnadida;
    }

    public static boolean borrar(RequisitoHasRequisito relacion) throws SQLException {
        String query = "DELETE FROM requisito_has_requisito WHERE `requisito_id` = ? AND `requisito_id1` = ? AND `tipo` = ?";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setInt(1, relacion.getRequisito_id());
        sentencia.setInt(2, relacion.getRequisito_id1());
        sentencia.setString(3, relacion.getTipo().toString());
        boolean filaBorrada = sentencia.executeUpdate() > 0;
        sentencia.close();

        Conexion.desconectarBD();

        return filaBorrada;
    }

    public static boolean borrar(int id, int id1) throws SQLException {
        String query = "DELETE FROM requisito_has_requisito WHERE `requisito_id` = ? AND `requisito_id1` = ?";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setInt(1, id);
        sentencia.setInt(2, id1);
        boolean filaBorrada = sentencia.executeUpdate() > 0;
        sentencia.close();

        Conexion.desconectarBD();

        return filaBorrada;
    }

    public static boolean actualizar(RequisitoHasRequisito relacion) throws SQLException {
        String query = "UPDATE `requisito_has_requisito` SET `tipo` = ? WHERE `requisito_id` = ? AND `requisito_id1` = ?";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setString(1, relacion.getTipo().toString());
        sentencia.setInt(2, relacion.getRequisito_id());
        sentencia.setInt(3, relacion.getRequisito_id1());
        boolean filaActualizada = sentencia.executeUpdate() > 0;
        sentencia.close();

        Conexion.desconectarBD();

        return filaActualizada;
    }

    public static RequisitoHasRequisito obtenerID(int id, int id1) throws SQLException {
        RequisitoHasRequisito relacion = null;
        String query = "SELECT * FROM `requisito_has_requisito` WHERE `requisito_id` = ? AND `requisito_id1` = ?";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setInt(1, id);
        sentencia.setInt(2, id1);

        ResultSet resultado = sentencia.executeQuery();

        if (resultado.next()) {
            relacion = new RequisitoHasRequisito(tipo.valueOf(resultado.getString("tipo")), resultado.getInt("requisito_id"), resultado.getInt("requisito_id1"));
        }

        sentencia.close();

        Conexion.desconectarBD();

        return relacion;
    }

    public static List<RequisitoHasRequisito> listar() throws SQLException {
        List<RequisitoHasRequisito> listaRequisitoHasRequisito = new ArrayList<RequisitoHasRequisito>();
        String query = "SELECT * FROM `requisito_has_requisito`";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);

        ResultSet resultado = sentencia.executeQuery();

        while (resultado.next()) {
            String tipoResultado = resultado.getString("tipo");
            int id = resultado.getInt("requisito_id");
            int id1 = resultado.getInt("requisito_id1");

            RequisitoHasRequisito relacion = new RequisitoHasRequisito(tipo.valueOf(tipoResultado), id, id1);
            listaRequisitoHasRequisito.add(relacion);
        }

        sentencia.close();

        Conexion.desconectarBD();

        return listaRequisitoHasRequisito;
    }
}
