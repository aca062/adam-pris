package controladores;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet{
    private static final long serialVersionUID = 1L;
    UsuarioDAO usuarioDAO;

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
            getServletContext().getRequestDispatcher("/ServletInicio?action=mostrar_inicio").forward(request, response);
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

    private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
