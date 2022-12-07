package controladores;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDAO;
import dao.RequisitoDAO;
import mochila.MochilaNRP;

@WebServlet("/ServletMochila")
public class ServletMochila extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public ServletMochila() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
            case "mostrar_solucion_optima":
            	mostrar_solucion_optima(request, response);
                break;
            case "mostrar_solucion_manual":
            	mostrar_solucion_manual(request, response);
                break;
            case "solucion_optima":
            	solucion_optima(request, response);
                break;
            default:
                break;
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }


    }

	private void solucion_optima(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		MochilaNRP mochila = new MochilaNRP(Integer.parseInt(request.getParameter("esfuerzo")));
		request.setAttribute("solucion", mochila.solucionAutomatica());
		getServletContext().getRequestDispatcher("/solucionAutomatica-sol.jsp").forward(request, response);
	}

	private void mostrar_solucion_manual(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		request.setAttribute("requisitos", RequisitoDAO.listar());
		getServletContext().getRequestDispatcher("/solucionManual.jsp").forward(request, response);
	}

	private void mostrar_solucion_optima(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		getServletContext().getRequestDispatcher("/solucionAutomatica.jsp").forward(request, response);
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
