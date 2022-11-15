package controladores;

import java.io.IOException;
import java.io.PrintWriter;
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

    public ServletUsuario() {
        super();
    }

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Hola Servlet..");
        String action = request.getParameter("action");
        System.out.println(action);
        try {
            switch (action) {
            case "inicio_sesion":
                inicio_sesion(request, response);
                break;
            default:
                break;
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }


    }

    private void inicio_sesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        String login=request.getParameter("username");
        String pass=request.getParameter("password");
        PrintWriter out = response.getWriter();

        if (UsuarioDAO.inicioSesion(login, pass)) {
            RequestDispatcher dispatcher= request.getRequestDispatcher("/vistas/index.jsp");
            dispatcher.forward(request, response);
        }else {
            out.print("Sorry username or password error");
            RequestDispatcher rd=request.getRequestDispatcher("index.html");
            rd.include(request,response);
        }


    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hola Servlet..");
        doGet(request, response);
    }

    private void index (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
