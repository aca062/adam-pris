package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Conexion;
import model.Requisito;

public class RequisitoDAO {
    public static boolean insertar(Requisito requisito) throws SQLException {
        String query = "INSERT INTO `requisito` (`id`, `esfuerzo`, `nombre`) VALUES (?, ?, ?)";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setString(1, null);
        sentencia.setInt(2, requisito.getEsfuerzo());
        sentencia.setString(3, requisito.getNombre());
        boolean filaAnadida = sentencia.executeUpdate() > 0;
        sentencia.close();

        Conexion.desconectarBD();

        return filaAnadida;
    }

    public static boolean borrar(Requisito requisito) throws SQLException {
        String query = "DELETE FROM requisito WHERE `id` = ? AND `esfuerzo` = ? AND `nombre` = ?";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setInt(1, requisito.getId());
        sentencia.setInt(2, requisito.getEsfuerzo());
        sentencia.setString(3, requisito.getNombre());
        boolean filaBorrada = sentencia.executeUpdate() > 0;
        sentencia.close();

        Conexion.desconectarBD();

        return filaBorrada;
    }

    public static boolean borrar(int id) throws SQLException {
        String query = "DELETE FROM requisito WHERE `requisito`.`id` = ?";

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

    public static Requisito obtenerID(int id) throws SQLException {
        Requisito requisito = null;
        String query = "SELECT * FROM `requisito` WHERE `id` = ?";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setInt(1, id);

        ResultSet resultado = sentencia.executeQuery();

        if (resultado.next()) {
            requisito = new Requisito(resultado.getInt("id"), resultado.getInt("esfuerzo"), resultado.getString("nombre"));
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

            Requisito requisito = new Requisito(id, esfuerzo, nombre);
            listaRequisito.add(requisito);
        }

        sentencia.close();

        Conexion.desconectarBD();

        return listaRequisito;
    }

    public static void main(String[] args) throws SQLException {
        RequisitoDAO.borrar(new Requisito(4, 2, "req2"));
        List<Requisito> array = RequisitoDAO.listar();
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i).getId());
        }
    }

}
