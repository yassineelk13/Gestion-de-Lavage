package app.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;

import app.Dao.EmployeDb;
import app.models.Employe;


@WebServlet("/")
public class Employeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeDb employeDAO;
	
	public void init() {
		employeDAO = new EmployeDb();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertEmploye(request, response);
				break;
			case "/delete":
				deleteEmploye(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateEmploye(request, response);
				break;
	
			default:
				listEmploye(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listEmploye(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Employe> listemploye = employeDAO.selectAllEmployes();
		request.setAttribute("listEmploye", listemploye);
		RequestDispatcher dispatcher = request.getRequestDispatcher("listEmploye.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("addEmploye.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Employe existingEmploye = employeDAO.selectEmploye(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("addEmploye.jsp");
		request.setAttribute("employe", existingEmploye);
		dispatcher.forward(request, response);

	}

	private void insertEmploye(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String fname = request.getParameter("fname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		double salary = Double.parseDouble(request.getParameter("salary"));
		Employe newEmploye = new Employe(fname, username, password,salary);
		employeDAO.insertEmploye(newEmploye);
		response.sendRedirect("list");
	}

	private void updateEmploye(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String fname = request.getParameter("fname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		double salary = Double.parseDouble(request.getParameter("salary"));
		Employe newEmploye = new Employe(id,fname, username, password,salary);
		employeDAO.updateEmploye(newEmploye);
		response.sendRedirect("list");
	}

	private void deleteEmploye(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		employeDAO.deleteEmploye(id);
		response.sendRedirect("list");

	}
	
	

}
