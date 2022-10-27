package dao;

import java.sql.SQLException;
import java.util.List;

import model.Usuario;

public class ClienteHasRequisitoDAO {



    public static void main (String[]args) throws SQLException {
        UsuarioDAO.actualizar(new Usuario(3,"j2uan","juan3"));
        List<Usuario> array = UsuarioDAO.listar();
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i).getLogin());
            System.out.println(array.get(i).getPassword());
        }
    }

}
