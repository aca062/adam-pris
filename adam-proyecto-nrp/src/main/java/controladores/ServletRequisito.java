package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDAO;
import dao.ClienteHasRequisitoDAO;
import dao.RequisitoDAO;
import model.Cliente;
import model.ClienteHasRequisito;
import model.Requisito;

@WebServlet("/ServletRequisito")
public class ServletRequisito extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletRequisito() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			switch (action) {
			case "mostrar_crear_requisito":
				mostrar_crear_requisito(request, response);
				break;
			case "crear_requisito":
				crear_requisito(request, response);
				break;
			default:
				break;
			}
		} catch (SQLException e) {
			e.getStackTrace();
		}

	}

	private void mostrar_crear_requisito(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		request.setAttribute("listaClientes", ClienteDAO.listar());
		request.setAttribute("listaRequisitos", RequisitoDAO.listar());
		getServletContext().getRequestDispatcher("/crearRequisito.jsp").forward(request, response);
	}

	private void crear_requisito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String nombre = request.getParameter("nombre");
		String esfuerzo = request.getParameter("esfuerzo");

		boolean error = false;

		Enumeration<String> parametros = request.getParameterNames();

		TreeMap<Integer, Integer> parametrosValor = new TreeMap<Integer, Integer>();

		while (parametros.hasMoreElements() && !error) {
			String param = parametros.nextElement();
			if (param.contains("valor")) {
				if (!request.getParameter(param).matches("-?(0|[1-9]\\d*)")) {
					request.setAttribute("errorCliente", "Solo se pueden introducir números enteros en el valor");
					getServletContext().getRequestDispatcher("/ServletRequisito?action=mostrar_crear_requisito").forward(request, response);
					error = true;
				} else {
					parametrosValor.put(Integer.parseInt(param.replace("valor", "")),
							Integer.parseInt(request.getParameter(param)));
				}
			}
		}

		if (!error) {
			if (nombre == null || nombre.isEmpty()) {
				if (esfuerzo == null || esfuerzo.isEmpty()) {
					request.setAttribute("error", "No se ha introducido el nombre ni el esfuerzo del requisito");
					getServletContext().getRequestDispatcher("/ServletRequisito?action=mostrar_crear_requisito").forward(request, response);
				}
				request.setAttribute("error", "No se ha introducido el nombre del requisito");
				getServletContext().getRequestDispatcher("/ServletRequisito?action=mostrar_crear_requisito").forward(request, response);
			} else if (esfuerzo == null || esfuerzo.isEmpty()) {
				request.setAttribute("error", "No se ha introducido el esfuerzo del requisito");
				getServletContext().getRequestDispatcher("/ServletRequisito?action=mostrar_crear_requisito").forward(request, response);
			} else {
				if (!esfuerzo.matches("-?(0|[1-9]\\d*)")) {
					request.setAttribute("error", "Solo se pueden introducir números enteros en el esfuerzo");
					getServletContext().getRequestDispatcher("/ServletRequisito?action=mostrar_crear_requisito").forward(request, response);
				} else {
					Requisito requisito = new Requisito(Integer.parseInt(esfuerzo), nombre.trim());
					boolean insertar = RequisitoDAO.insertar(requisito);

					if (insertar) {
						int idRequisito = RequisitoDAO.obtenerPorNombre(requisito.getNombre()).getId();
						for (Entry<Integer, Integer> entry : parametrosValor.entrySet()) {
							ClienteHasRequisito chr = new ClienteHasRequisito(entry.getValue().intValue(),
									entry.getKey().intValue(), idRequisito);
							System.out.println(ClienteHasRequisitoDAO.insertar(chr));
						}
						response.setContentType("text/html");
						PrintWriter pw = response.getWriter();
						pw.println("<script type=\"text/javascript\">");
						pw.println("alert('El requisito se ha creado correctamente');");
						pw.println("</script>");
						RequestDispatcher rd = request.getRequestDispatcher("/ServletInicio?action=mostrar_inicio");
						rd.include(request, response);
					} else {
						request.setAttribute("error", "El nombre del requisito ya existe");
						getServletContext().getRequestDispatcher("/ServletRequisito?action=mostrar_crear_requisito").forward(request, response);
					}
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
