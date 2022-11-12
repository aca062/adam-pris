package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Conexion;

public class ClienteDAO {

    public static boolean insertar(Cliente cliente) throws SQLException {

        if (obtenerPorNombre(cliente.getNombre()) != null) {
            return false;
        }

        String query = "INSERT INTO `cliente` (`id`, `prioridad`, `nombre`, `usuario_id`) VALUES (?, ?, ?, ?)";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setString(1, null);
        sentencia.setInt(2, cliente.getPrioridad());
        sentencia.setString(3, cliente.getNombre());
        if (cliente.getUsuario_id() == -1 ) {
            sentencia.setNull(4, Types.INTEGER);
        } else {
            sentencia.setInt(4, cliente.getUsuario_id());
        }
        boolean filaAnadida = sentencia.executeUpdate() > 0;
        sentencia.close();

        Conexion.desconectarBD();

        return filaAnadida;
    }

    public static boolean borrar(Cliente cliente) throws SQLException {
        String query = "DELETE FROM cliente WHERE `id` = ? AND `prioridad` = ? AND `nombre` = ?";

        ClienteHasRequisitoDAO.borrarRelacionesCliente(cliente.getId());

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setInt(1, obtenerPorNombre(cliente.getNombre()).getId());
        sentencia.setInt(2, cliente.getPrioridad());
        sentencia.setString(3, cliente.getNombre());
        boolean filaBorrada = sentencia.executeUpdate() > 0;
        sentencia.close();

        Conexion.desconectarBD();

        return filaBorrada;
    }

    public static boolean borrar(int id) throws SQLException {
        String query = "DELETE FROM cliente WHERE `cliente`.`id` = ?";

        ClienteHasRequisitoDAO.borrarRelacionesCliente(id);

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setInt(1, id);
        boolean filaBorrada = sentencia.executeUpdate() > 0;
        sentencia.close();

        Conexion.desconectarBD();

        return filaBorrada;
    }

    public static boolean actualizar(Cliente cliente) throws SQLException {
        String query = "UPDATE `cliente` SET `prioridad` = ?, `nombre` = ? WHERE `cliente`.`id` = ?";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setInt(1, cliente.getPrioridad());
        sentencia.setString(2, cliente.getNombre());
        sentencia.setInt(3, cliente.getId());
        boolean filaActualizada = sentencia.executeUpdate() > 0;
        sentencia.close();

        Conexion.desconectarBD();

        return filaActualizada;
    }

    public static Cliente obtenerPorID(int id) throws SQLException {
        Cliente cliente = null;
        String query = "SELECT * FROM `cliente` WHERE `id` = ?";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setInt(1, id);

        ResultSet resultado = sentencia.executeQuery();

        if (resultado.next()) {
            cliente = new Cliente(resultado.getInt("id"), resultado.getInt("prioridad"), resultado.getString("nombre"), resultado.getInt("usuario_id") != 0 ? resultado.getInt("usuario_id") : -1);
        } else {
            return null;
        }

        sentencia.close();

        Conexion.desconectarBD();

        return cliente;
    }

    public static Cliente obtenerPorNombre(String nombre) throws SQLException {
        Cliente cliente = null;
        String query = "SELECT * FROM `cliente` WHERE `nombre` = ?";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);
        sentencia.setString(1, nombre);

        ResultSet resultado = sentencia.executeQuery();

        if (resultado.next()) {
            cliente = new Cliente(resultado.getInt("id"), resultado.getInt("prioridad"), resultado.getString("nombre"), resultado.getInt("usuario_id") != 0 ? resultado.getInt("usuario_id") : -1);
        } else {
            return null;
        }

        sentencia.close();

        Conexion.desconectarBD();

        return cliente;
    }

    public static List<Cliente> listar() throws SQLException {
        List<Cliente> listaCliente = new ArrayList<Cliente>();
        String query = "SELECT * FROM `cliente`";

        Conexion.conectarBD();

        PreparedStatement sentencia = Conexion.getConexion().prepareStatement(query);

        ResultSet resultado = sentencia.executeQuery();

        while (resultado.next()) {
            int id = resultado.getInt("id");
            int prioridad = resultado.getInt("prioridad");
            String nombre = resultado.getString("nombre");
            int usuario_id = resultado.getInt("usuario_id") != 0 ? resultado.getInt("usuario_id") : -1;

            Cliente cliente = new Cliente(id, prioridad, nombre, usuario_id);
            listaCliente.add(cliente);
        }

        sentencia.close();

        Conexion.desconectarBD();

        return listaCliente;
    }
}
