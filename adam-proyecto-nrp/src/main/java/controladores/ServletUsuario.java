package controladores;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import model.Usuario;

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet{
    private static final long serialVersionUID = 1L;
    UsuarioDAO usuarioDAO;
    public static boolean admin;
    public static int id;

    public ServletUsuario() {
        super();
    }

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
            case "iniciar_sesion":
            	iniciar_sesion(request, response);
            default:
                break;
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }


    }

    private void iniciar_sesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String login=request.getParameter("username");
        String pass=request.getParameter("password");
        boolean iniciar = UsuarioDAO.inicioSesion(login, pass);

        if (iniciar) {
        	Usuario usuario = UsuarioDAO.obtenerPorLogin(login);
        	admin = usuario.getAdmin();
        	id = usuario.getId();
        	
            getServletContext().getRequestDispatcher("/ServletProyecto?action=elegir_proyecto").forward(request, response);
        }else {
            request.setAttribute("error", "El nombre o contraseña son incorrectos");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
	}

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
