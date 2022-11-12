package daoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.ClienteDAO;
import model.Cliente;

class ClienteDAOTest {

    @Test
    void testInsertar() {
        try {
            int numInicial = ClienteDAO.listar().size();

            Cliente cliente = new Cliente(1, "PruebaTestBBDD");

            ClienteDAO.insertar(cliente);

            int numFinal = ClienteDAO.listar().size();

            ClienteDAO.borrar(cliente);

            assertEquals(numInicial, numFinal - 1);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Excepción SQL");
        }
    }

    @Test
    void testBorrar() {
        try {
            int numInicial = ClienteDAO.listar().size();

            Cliente cliente = new Cliente(1, "PruebaTestBBDD");

            ClienteDAO.insertar(cliente);

            ClienteDAO.borrar(cliente);

            int numFinal = ClienteDAO.listar().size();

            assertEquals(numInicial, numFinal);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Excepción SQL");
        }
    }

    @Test
    void testBorrarId() {
        try {
            int numInicial = ClienteDAO.listar().size();

            Cliente cliente = new Cliente(1, "PruebaTestBBDD");

            ClienteDAO.insertar(cliente);

            ClienteDAO.borrar(ClienteDAO.obtenerPorNombre(cliente.getNombre()));

            int numFinal = ClienteDAO.listar().size();

            assertEquals(numInicial, numFinal);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Excepción SQL");
        }
    }

    @Test
    void testActualizar() {
        try {

            Cliente cliente = new Cliente(1, "PruebaTestBBDD");

            ClienteDAO.insertar(cliente);

            Cliente cliente2 = ClienteDAO.obtenerPorNombre("PruebaTestBBDD");

            int id = cliente2.getId();

            cliente2.setNombre("PruebaActualizadaTestBBDD");

            cliente2.setPrioridad(2);

            ClienteDAO.actualizar(cliente2);

            Cliente cliente3 = ClienteDAO.obtenerPorID(id);

            ClienteDAO.borrar(cliente2);

            assertEquals(cliente2, cliente3);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Excepción SQL");
        }
    }
}
