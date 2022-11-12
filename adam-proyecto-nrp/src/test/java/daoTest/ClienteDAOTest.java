package daoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.ClienteDAO;
import model.Cliente;

class ClienteDAOTest {

    @Test
    void testInsertar() {
        try {

            Cliente cliente = new Cliente(1, "PruebaTestBBDD");

            ClienteDAO.insertar(cliente);

            Cliente cliente2 = ClienteDAO.obtenerPorNombre("PruebaTestBBDD");

            ClienteDAO.borrar(cliente);

            assertEquals(cliente, cliente2);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Excepción SQL");
        }
    }

    @Test
    void testBorrar() {
        try {

            Cliente cliente = new Cliente(1, "PruebaTestBBDD");

            ClienteDAO.insertar(cliente);

            if (ClienteDAO.obtenerPorNombre("PruebaTestBBDD") == null) {
                fail("No se ha insertado el cliente");
            }

            ClienteDAO.borrar(cliente);

            assertTrue(ClienteDAO.obtenerPorNombre("PruebaTestBBDD") == null);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Excepción SQL");
        }
    }

    @Test
    void testBorrarId() {
        try {

            Cliente cliente = new Cliente(1, "PruebaTestBBDD");

            ClienteDAO.insertar(cliente);

            if (ClienteDAO.obtenerPorNombre("PruebaTestBBDD") == null) {
                fail("No se ha insertado el cliente");
            }

            ClienteDAO.borrar(ClienteDAO.obtenerPorNombre(cliente.getNombre()));

            assertTrue(ClienteDAO.obtenerPorNombre("PruebaTestBBDD") == null);

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

    @Test
    void testBuscarNombre() {
        try {

            Cliente cliente = new Cliente(1, "PruebaTestBBDD");

            ClienteDAO.insertar(cliente);

            Cliente cliente2 = ClienteDAO.obtenerPorNombre("PruebaTestBBDD");

            ClienteDAO.borrar(cliente2);

            assertEquals(cliente2, cliente);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Excepción SQL");
        }
    }

    @Test
    void testBuscarId() {
        try {

            Cliente cliente = new Cliente(1, "PruebaTestBBDD");

            ClienteDAO.insertar(cliente);

            Cliente cliente2 = ClienteDAO.obtenerPorNombre("PruebaTestBBDD");

            int id = cliente2.getId();

            Cliente cliente3 = ClienteDAO.obtenerPorID(id);

            ClienteDAO.borrar(cliente2);

            assertEquals(cliente, cliente3);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Excepción SQL");
        }
    }
}
