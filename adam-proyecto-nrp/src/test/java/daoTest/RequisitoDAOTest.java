package daoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.RequisitoDAO;
import model.Requisito;

class RequisitoDAOTest {

    @Test
    void testInsertar() {
        try {

            Requisito requisito = new Requisito(1, "PruebaTestBBDD");

            RequisitoDAO.insertar(requisito);

            Requisito requisito2 = RequisitoDAO.obtenerPorNombre("PruebaTestBBDD");

            RequisitoDAO.borrar(requisito);

            assertEquals(requisito, requisito2);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Excepción SQL");
        }
    }

    @Test
    void testBorrar() {
        try {

            Requisito requisito = new Requisito(1, "PruebaTestBBDD");

            RequisitoDAO.insertar(requisito);

            if (RequisitoDAO.obtenerPorNombre("PruebaTestBBDD") == null) {
                fail("No se ha insertado el requisito");
            }

            RequisitoDAO.borrar(requisito);

            assertTrue(RequisitoDAO.obtenerPorNombre("PruebaTestBBDD") == null);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Excepción SQL");
        }
    }

    @Test
    void testBorrarId() {
        try {

            Requisito requisito = new Requisito(1, "PruebaTestBBDD");

            RequisitoDAO.insertar(requisito);

            if (RequisitoDAO.obtenerPorNombre("PruebaTestBBDD") == null) {
                fail("No se ha insertado el requisito");
            }

            RequisitoDAO.borrar(RequisitoDAO.obtenerPorNombre(requisito.getNombre()));

            assertTrue(RequisitoDAO.obtenerPorNombre("PruebaTestBBDD") == null);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Excepción SQL");
        }
    }

    @Test
    void testActualizar() {
        try {

            Requisito requisito = new Requisito(1, "PruebaTestBBDD");

            RequisitoDAO.insertar(requisito);

            Requisito requisito2 = RequisitoDAO.obtenerPorNombre("PruebaTestBBDD");

            int id = requisito2.getId();

            requisito2.setNombre("PruebaActualizadaTestBBDD");

            requisito2.setEsfuerzo(2);

            RequisitoDAO.actualizar(requisito2);

            Requisito requisito3 = RequisitoDAO.obtenerPorID(id);

            RequisitoDAO.borrar(requisito2);

            assertEquals(requisito2, requisito3);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Excepción SQL");
        }
    }

    @Test
    void testBuscarNombre() {
        try {

            Requisito requisito = new Requisito(1, "PruebaTestBBDD");

            RequisitoDAO.insertar(requisito);

            Requisito requisito2 = RequisitoDAO.obtenerPorNombre("PruebaTestBBDD");

            RequisitoDAO.borrar(requisito2);

            assertEquals(requisito2, requisito);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Excepción SQL");
        }
    }

    @Test
    void testBuscarId() {
        try {

            Requisito requisito = new Requisito(1, "PruebaTestBBDD");

            RequisitoDAO.insertar(requisito);

            Requisito requisito2 = RequisitoDAO.obtenerPorNombre("PruebaTestBBDD");

            int id = requisito2.getId();

            Requisito requisito3 = RequisitoDAO.obtenerPorID(id);

            RequisitoDAO.borrar(requisito2);

            assertEquals(requisito, requisito3);

        } catch (SQLException e) {
            e.printStackTrace();
            fail("Excepción SQL");
        }
    }

}
