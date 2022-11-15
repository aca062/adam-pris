package controladores;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletRequisito")
public class ServletRequisito extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public ServletRequisito() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Hola Servlet..");
        String action = request.getParameter("action");
        System.out.println(action);
        try {
            switch (action) {
            case "index":
                index(request, response);
                break;
            default:
                break;
            }
        } catch (SQLException e) {
            e.getStackTrace();
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
        //mostrar(request, response);
        RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
